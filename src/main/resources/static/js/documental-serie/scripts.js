function getDocumentalSeries() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/documental-serie/` });

    return out;
}

function getDocumentalSerie(id) {
    let out = $.ajax({ method: "GET", url: `${URL}/api/documental-serie/${id}` });

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
                    <td scope="col">${new Date(documentalSerie.elaborationDate)}</td>
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
    let documentalSerie = await getDocumentalSerie(id);

    let elaborationDate = dateFormat(documentalSerie.elaborationDate, "yyyy-MM-dd");

    $("#documental-serie-code").val(documentalSerie.code);
    $("#documental-serie-name").val(documentalSerie.name);
    $("#documental-serie-hierarchical-dependency").val(documentalSerie.hierarchicalDependency.id);
    $("#documental-serie-organic-unit").val(documentalSerie.organicUnit.id);
    $("#documental-serie-definition").val(documentalSerie.definition);
    $("#documental-serie-service-frequency").val(documentalSerie.serviceFrequency);
    $("#documental-serie-normative-scope").val(documentalSerie.normativeScope);
    $("#documental-serie-is-public").val(documentalSerie.isPublic == true ? "on" : "");
    $("#documental-serie-phisical-features").val(documentalSerie.phisicalFeatures);
    $("#documental-serie-value").val(documentalSerie.documentalSerieValue);
    $("#documental-serie-years-in-ma").val(documentalSerie.yearsInManagementArchive);
    $("#documental-serie-years-in-ca").val(documentalSerie.yearsInCentralArchive);
    $("#documental-serie-elaboration-date").val(elaborationDate);
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

    documentalSerieForm.validate({
        messages: {
            "documental-serie-code": {
                required: "Por favor, pon un código"
            },
            "documental-serie-name": {
                required: "Por favor, pon un nombre"
            },
            "documental-serie-hierarchical-dependency": {
                required: "Por favor, define una dependencia jerárquica"
            },
            "documental-serie-organic-unit": {
                required: "Por favor, define una unidad orgánica"
            },
            "documental-serie-definition": {
                required: "Por favor, pon una definición"
            },
            "documental-serie-normative-scope": {
                required: "Por favor, pon un alcance normativo"
            },
            "documental-serie-service-frequency": {
                required: "Por favor, pon una frecuencia de servicio"
            },
            "documental-serie-phisical-features": {
                required: "Por favor, pon las características físicas"
            },
            "documental-serie-value": {
                required: "Por favor, pon un valor",
                minlength: "El mínimo de caracteres permitidos es 1",
                maxlength: "El máximo de caracteres permitidos es 1"
            },
            "documental-serie-years-in-ma": {
                required: "Por favor, pon una cantidad de años"
            },
            "documental-serie-years-in-ca": {
                required: "Por favor, pon una cantidad de años"
            }
        }
    });

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