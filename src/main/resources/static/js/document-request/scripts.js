function getDocumentRequests() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/document-request/` });

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

function postDocumentRequest(documentRequest) {
    let out = $.ajax({
        method: "POST", url: `${URL}/api/document-request/`, data: JSON.stringify({
            id: documentRequest.id,
            name: documentRequest.name,
            description: documentRequest.description,
            creationDate: documentRequest.creationDate,
            requestState: {
                id: documentRequest.requestState.id,
                name: null
            },
            user: {
                username: documentRequest.user.username
            },
            organicUnit: {
                id: documentRequest.organicUnit.id,
                name: null
            }
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

function deleteDocumentRequest(id) {
    let out = $.ajax({ method: "DELETE", url: `${URL}/api/document-request/${id}` });

    return out;
}

function addDocumentRequestIdToUrl(id) {
    history.pushState({}, '', location.pathname.concat(id));
}

function removeDocumentRequestIdToUrl() {
    history.pushState({}, '', location.pathname.substring(0, location.pathname.lastIndexOf("/") + 1));
}

async function saveDocumentRequest() {
    let id = location.pathname.substring(location.pathname.lastIndexOf("/") + 1, location.pathname.length);

    let documentRequest = {
        id: id == '' ? null : id,
        name: $("#request-name").val(),
        description: $("#request-description").val(),
        creationDate: $("#request-creation-date").val(),
        requestState: {
            id: parseInt($("#request-state").val())
        },
        user: {
            username: $("#request-user").val()
        },
        organicUnit: {
            id: parseInt($("#request-organic-unit").val())
        }
    };

    let out = await postDocumentRequest(documentRequest);

    fillDocumentRequestsTable();
}

function mapUserInTableRow(documentRequest) {
    return `<tr class="document-requests-table__body__item">
                    <td scope="col">${documentRequest.name}</td>
                    <td scope="col">${documentRequest.description}</td>
                    <td scope="col">${documentRequest.creationDate}</td>
                    <td scope="col">${documentRequest.requestState.name}</td>
                    <td scope="col">${documentRequest.user.username}</td>
                    <td scope="col">${documentRequest.organicUnit.name}</td>
                    <td scope="col">
                        <div class="d-flex justify-content-center align-items-center gap-2">
                            <button type="button" class="btn btn-warning" data-bs-dismiss="modal" onclick="editDocumentRequestFromList('${documentRequest.id}')" data-bs-toggle="modal" data-bs-target="#documentRequestModal">Editar</button>
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="deleteDocumentRequestFromList('${documentRequest.id}')">Eliminar</button>
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

async function fillDocumentRequestForm(id) {
    let documentRequest = await getDocumentRequest(id);

    let creationDate = dateFormat(documentRequest.creationDate, "yyyy-MM-dd");

    $("#request-name").val(documentRequest.name);
    $("#request-description").val(documentRequest.description);
    $("#request-creation-date").val(creationDate);
    $("#request-state").val(documentRequest.requestState.id);
    $("#request-user").val(documentRequest.user.username);
    $("#request-organic-unit").val(documentRequest.organicUnit.id);
}

async function editDocumentRequestFromList(id) {
    addDocumentRequestIdToUrl(id);
    fillDocumentRequestForm(id);
}

async function deleteDocumentRequestFromList(id) {
    let out = await deleteDocumentRequest(id);

    fillDocumentRequestsTable();
}

async function fillDocumentRequestsTable() {
    const documentRequestsTableBody = $("#document-requests-table__body");

    documentRequestsTableBody.empty();

    let documentRequests = await getDocumentRequests();

    documentRequests.forEach(documentRequest => {
        documentRequestsTableBody.append(mapUserInTableRow(documentRequest));
    })
}

async function fillRequestStatesCombobox() {
    const requestStatesCombobox = $("#request-state");

    let requestStates = await getRequestStates();

    requestStates.forEach(requestState => {
        requestStatesCombobox.append(mapRequestStateInCombobox(requestState));
    });
}

async function fillOrganicUnitsCombobox() {
    const organicUnitsCombobox = $("#request-organic-unit");

    let organicUnits = await getOrganicUnits();

    organicUnits.forEach(organicUnit => {
        organicUnitsCombobox.append(mapOrganicUnitInCombobox(organicUnit));
    });
}

function emptyDocumentRequestForm() {
    let documentRequestForm = $("#documentRequestForm")[0];

    documentRequestForm.reset();
}

function closeDocumentRequestModal() {
    $("#documentRequestModal").modal("hide");
}

function searchRequestsInView() {
		$(".document-requests-table__body__item").filter(
    		function() {
    			$(this).toggle(
    				$(this).children("td").eq(0).text()
    					.includes($("#search-document-request-name").val()));
    		}
    	)
}

function openRegisterDocumentRequestModal() {
	let currentDate = dateFormat(new Date(), "yyyy-MM-dd");

	$("#request-creation-date").val(currentDate);
}

function index() {
    let saveButton = $("#save-button");
    let documentRequestForm = $("#documentRequestForm");

    fillDocumentRequestsTable();
    fillRequestStatesCombobox();
    fillOrganicUnitsCombobox();

    documentRequestForm.validate({
        messages: {
            "request-name": {
                required: "Por favor, pon un nombre de solicitud"
            },
            "request-description": {
                required: "Por favor, pon una descripción"
            },
            "request-creation-date": {

            },
            "request-state": {
                required: "Por favor, elige un estado"
            },
            "request-user": {

            },
            "request-organic-unit": {
                required: "Por favor, elige una unidad orgánica"
            }
        }
    });

	$("#search-document-request-name").keyup(function() {
		searchRequestsInView();
	});

    saveButton.on("click", e => {
        if (documentRequestForm.valid()) {
            saveDocumentRequest();
            emptyDocumentRequestForm();
            closeDocumentRequestModal();
        }
    });

    $("#documentRequestModal").on("show.bs.modal", (e) => {
        emptyDocumentRequestForm();
    });

    $("#documentRequestModal").on("hidden.bs.modal", (e) => {
        removeDocumentRequestIdToUrl();
    });
}