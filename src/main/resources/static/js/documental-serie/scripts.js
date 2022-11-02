const URL = location.origin;

function showDangerAlert(message) {
    const container = document.createElement("div");
    let alert = `
        <div class="alert alert-danger appears-in-top-center text-center shadow-sm" role="alert">
            ${message}
        </div>
	`
    container.innerHTML = alert;
    $('body').prepend(container);

    setTimeout(function () {
        container.remove();
    }, 3000);
}

function showSuccessAlert(message) {
    const container = document.createElement("div");
    let alert = `
        <div class="alert alert-success appears-in-top-center text-center shadow-sm" role="alert">
            ${message}
        </div>
	`
    container.innerHTML = alert;
    $('body').prepend(container);

    setTimeout(function () {
        container.remove();
    }, 3000);
}

function getDocumentalSeries() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/documental-serie/` });

    return out;
}

function getDocumentRequest(id) {
    let out = $.ajax({ method: "GET", url: `${URL}/api/document-request/${id}` });

    return out;
}

function getRequestStates() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/request-state/` });

    return out;
}

function getUser(username) {
    let out = $.ajax({ method: "GET", url: `${URL}/api/user/${username}` });

    return out;
}

function getOrganicUnits() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/organic-unit/` });

    return out;
}

function getHierarchicalDependencies() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/hierarchical-dependency/` });

    return out;
}

function postDocumentalSerie(bean) {
    let out = $.ajax({
        method: "POST", url: `${URL}/api/documental-serie/`, data: JSON.stringify({
            code: bean.code,
            name: bean.name,
            hierarchicalDependency: {
                id: bean.hierarchicalDependency.id
            },
            organicUnit: {
                id: bean.organicUnit.id
            },
            definition: bean.definition,
            serviceFrequency: bean.serviceFrequency,
            normativeScope: bean.normativeScope,
            isPublic: bean.isPublic,
            phisicalFeatures: bean.phisicalFeatures,
            documentalSerieValue: bean.documentalSerieValue,
            yearsInManagementArchive: bean.yearsInManagementArchive,
            yearsInCentralArchive: bean.yearsInCentralArchive,
            elaborationDate: bean.elaborationDate,
        }),
        contentType: "application/json",
        success: function () {
            showSuccessAlert(`Registro / Actualización exitosa!`);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showDangerAlert(`Hubo un error al registrar, mensaje: ${textStatus}`)
        }
    });

    return out;
}

function deleteDocumentalSerie(id) {
    let out = $.ajax({ method: "DELETE", url: `${URL}/api/documental-serie/${id}` });

    return out;
}

function addDocumentRequestIdToUrl(id) {
    history.pushState({}, '', location.pathname.concat(id));
}

function removeDocumentalSerieIdToUrl() {
    history.pushState({}, '', location.pathname.substring(0, location.pathname.lastIndexOf("/") + 1));
}

async function saveDocumentalSerieRequest() {
    let code = location.pathname.substring(location.pathname.lastIndexOf("/") + 1, location.pathname.length);

    let documentalSerie = {
        code: code == '' ? $("#documental-serie-code").val() : code,
        name: $("#documental-serie-name").val(),
        hierarchicalDependency: {
            id: parseInt($("#documental-serie-hierarchical-dependency").val(), 10)
        },
        organicUnit: {
            id: parseInt($("#documental-serie-organic-unit").val(), 10)
        },
        definition: $("#documental-serie-definition").val(),
        serviceFrequency: $("#documental-serie-service-frequency").val(),
        normativeScope: $("#documental-serie-normative-scope").val(),
        isPublic: $("#documental-serie-is-public").val() == "on" ? 1 : 0,
        phisicalFeatures: $("#documental-serie-phisical-features").val(),
        documentalSerieValue: $("#documental-serie-value").val(),
        yearsInManagementArchive: parseInt($("#documental-serie-years-in-ma").val(), 10),
        yearsInCentralArchive: parseInt($("#documental-serie-years-in-ca").val(), 10),
        elaborationDate: $("#documental-serie-elaboration-date").val(),
    };

    let out = await postDocumentalSerie(documentalSerie);

    fillDocumentalSeriesTable();
}

function mapUserInTableRow(documentalSerie) {
    return `<tr class="documental-series-table__body__item">
                    <td scope="col">${documentalSerie.code}</td>
                    <td scope="col">${documentalSerie.name}</td>
                    <td scope="col">${documentalSerie.hierarchicalDependency.name}</td>
                    <td scope="col">${documentalSerie.organicUnit.name}</td>
                    <td scope="col">${documentalSerie.definition}</td>
                    <td scope="col">${documentalSerie.serviceFrequency}</td>
                    <td scope="col">${documentalSerie.isPublic}</td>
                    <td scope="col">${documentalSerie.documentalSerieValue}</td>
                    <td scope="col">${documentalSerie.yearsInManagementArchive}</td>
                    <td scope="col">${documentalSerie.yearsInCentralArchive}</td>
                    <td scope="col">${documentalSerie.elaborationDate}</td>
                    <td scope="col">
                        <div class="d-flex justify-content-center align-items-center gap-2">
                            <button type="button" class="btn btn-warning" data-bs-dismiss="modal" onclick="editDocumentalSerieFromList('${documentalSerie.code}')" data-bs-toggle="modal" data-bs-target="#documentalSerieModal">Editar</button>
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="deleteDocumentalSerieFromList('${documentalSerie.code}')">Eliminar</button>
                        </div>
                    </td>
                </tr>
                `;
}

function mapRequestStateInCombobox(requestState) {
    return `<option value=${requestState.id}>${requestState.name}</option>`;
}

function mapOrganicUnitInCombobox(organicUnit) {
    return `<option value=${organicUnit.id}>${organicUnit.name}</option>`;
}

async function filldocumentalSerieForm(id) {
    let documentalSerie = await getDocumentRequest(id);

    let creationDate = documentalSerie.creationDate.split('T')[0];

    $("#request-name").val(documentalSerie.name);
    $("#request-description").val(documentalSerie.description);
    $("#request-creation-date").val(creationDate);
    $("#request-state").val(documentalSerie.requestState.id);
    $("#request-user").val(documentalSerie.user.username);
    $("#request-organic-unit").val(documentalSerie.organicUnit.id);
}

async function editDocumentalSerieFromList(id) {
    addDocumentRequestIdToUrl(id);
    filldocumentalSerieForm(id);
}

async function deleteDocumentalSerieFromList(id) {
    let out = await deleteDocumentalSerie(id);

    fillDocumentalSeriesTable();
}

async function fillDocumentalSeriesTable() {
    const documentalSeriesTableBody = $("#documental-series-table__body");

    documentalSeriesTableBody.empty();

    let documentalSeries = await getDocumentalSeries();

    documentalSeries.forEach(documentalSerie => {
        documentalSeriesTableBody.append(mapUserInTableRow(documentalSerie));
    })
}

async function fillHierarchicalDependenciesCombobox() {
    const hierarchicalDependenciesCombobox = $("#documental-serie-hierarchical-dependency");

    let hierarchicalDependencies = await getHierarchicalDependencies();

    hierarchicalDependencies.forEach(hierarchicalDependency => {
        hierarchicalDependenciesCombobox.append(mapRequestStateInCombobox(hierarchicalDependency));
    });
}

async function fillOrganicUnitsCombobox() {
    const organicUnitsCombobox = $("#documental-serie-organic-unit");

    let organicUnits = await getOrganicUnits();

    organicUnits.forEach(organicUnit => {
        organicUnitsCombobox.append(mapOrganicUnitInCombobox(organicUnit));
    });
}

function emptyDocumentalSerieForm() {
    let documentalSerieForm = $("#documentalSerieForm")[0];

    documentalSerieForm.reset();
}

function closeDocumentalSerieModal() {
    $("#documentalSerieModal").modal("hide");
}

function searchDocumentalSeriesInView() {
		$(".documental-series-table__body__item").filter(
    		function() {
    			$(this).toggle(
    				$(this).children("td").eq(0).text()
    					.includes($("#search-documental-serie-name").val()));
    		}
    	)
}

function index() {
    let saveButton = $("#save-button");
    let documentalSerieForm = $("#documentalSerieForm");

    fillDocumentalSeriesTable();
    fillHierarchicalDependenciesCombobox();
    fillOrganicUnitsCombobox();

    /*documentalSerieForm.validate({
        messages: {
            "user-name": {
                required: "Por favor, pon un nombre de usuario",
                minlength: "Digita como mínimo 3 caracteres",
                maxlength: "Digita como máximo 30 caracteres"
            },
            "user-password": {
                required: "Por favor, pon una contraseña"
            },
            "user-email": {
                required: "Por favor, pon un email",
                min: "La cantidad mínima es 1",
                max: "La cantidad máxima es 10"
            },
            "user-role": {
                required: "Por favor, elige un rol"
            },
            "user-position": {
                required: "Por favor, pon una posición"
            }
        }
    });*/

	$("#search-documental-serie-name").keyup(function() {
		searchDocumentalSeriesInView();
	});

    saveButton.on("click", e => {
        if (documentalSerieForm.valid()) {
            saveDocumentalSerieRequest();
            emptyDocumentalSerieForm();
            closeDocumentalSerieModal();
        }
    });

    $("#documentalSerieModal").on("show.bs.modal", (e) => {
        emptyDocumentalSerieForm();
    });

    $("#documentalSerieModal").on("hidden.bs.modal", (e) => {
        removeDocumentalSerieIdToUrl();
    });
}

$(document).ready(function () {
    index();
});