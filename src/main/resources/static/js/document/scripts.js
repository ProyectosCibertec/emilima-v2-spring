function getDocuments() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/document/` });

    return out;
}

function getDocument(id) {
    let out = $.ajax({ method: "GET", url: `${URL}/api/document/${id}` });

    return out;
}

function getDocumentTypes() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/document-type/` });

    return out;
}

function getDocumentSeries() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/documental-serie/` });

    return out;
}

function getDocumentRequests() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/document-request/` });

    return out;
}

function postDocument(formData) {
    let out = $.ajax({
        method: "POST",
        url: `${URL}/api/document/`,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        //xhrFields: {responseType: 'json'},
        success: function () {
            showSuccessAlert(`Registro / Actualización exitosa!`);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showDangerAlert(`Hubo un error al registrar, mensaje: ${textStatus}`)
        }
    });

    return out;
}

function deleteDocument(id) {
    let out = $.ajax({ method: "DELETE", url: `${URL}/api/document/${id}` });

    return out;
}

function addDocumentIdToUrl(id) {
    history.pushState({}, '', location.pathname.concat(id));
}

function removeDocumentIdToUrl() {
    history.pushState({}, '', location.pathname.substring(0, location.pathname.lastIndexOf("/") + 1));
}

async function saveDocument() {
    let serialNumber = location.pathname.substring(location.pathname.lastIndexOf("/") + 1, location.pathname.length);
    let formData = new FormData();

    formData.append("serialNumber", serialNumber == '' ? null : serialNumber);
    formData.append("name", $("#document-name").val());
    formData.append("description", $("#document-description").val());
    formData.append("uploadDate", $("#document-upload-date").val());
    formData.append("creationDate", $("#document-creation-date").val());
    formData.append("file", $("#document-file")[0].files[0]);
    formData.append("documentType", $("#document-type").val());
    formData.append("documentalSerie", $("#document-serie").val());
    formData.append("documentRequest", $("#document-request").val());

    let out = await postDocument(formData);

    fillDocumentsTable();
}

async function downloadDocument(id) {
	let filename;

	let result = await $.ajax({
		url: `${URL}/file/download/${id}`, method: "GET", xhrFields: { responseType: 'arraybuffer' }, statusCode: {
			200: function(data, status, xhr) {
				filename = xhr.getResponseHeader('Content-Disposition');
			}
		}
	});

	return {
		result: result,
		filename: filename
	};
}

async function downloadDocumentOperation(id) {
	let file = await downloadDocument(id);
	let link = document.createElement('a');
	let blob = new Blob([file.result], { type: "application/pdf" });
	let reader = new FileReader();

	reader.readAsDataURL(blob);
	reader.onload = function() {
		link.href = reader.result;
		link.download = file.filename.split('=')[1];
		link.click();
		link.remove();
	}
}

function mapDocumentInGridItem(document) {
    return `
                        <div class="col-lg-6 documents-list__item">
							<div class="bg-white card shadow-sm">
								<div class="card-body">
									<div class="row align-items-center justify-content-center">
										<div class="col-md-2">
											<img src="${URL}/public/img/icono-pdf.png" width="100%">
										</div>
										<div class="col-md-7">
											<h2 class="card-title h4 documents-list__item__title fw-bold">${document.name}</h2>
											<p class="card-text fw-light fst-italic">${document.description}</p>
										</div>
										<div class="col-md-3">
											<div class="row align-items-center">
												<div class="col flex-column">
													<i class="bi bi-pencil-fill documents-list__item__edit-button" style="font-size: 1.5rem;" data-bs-toggle="modal" data-bs-target="#documentModal" onclick="editDocumentFromList('${document.serialNumber}')"></i>
													<i class="bi bi-x documents-list__item__delete-button" style="font-size: 1.5rem;" onclick='deleteDocumentFromList(${document.serialNumber})'></i>
												</div>
												<div class="col">
													<i class="bi bi-download documents-list__item__download-button" style="font-size: 2.5rem;" onclick="downloadDocumentOperation('${document.file.id}')"></i>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
    `;
}

function mapDocumentTypeInCombobox(documentType) {
    return `<option value=${documentType.id}>${documentType.name}</option>`;
}

function mapDocumentSerieInCombobox(documentSerie) {
    return `<option value=${documentSerie.code}>${documentSerie.name}</option>`;
}

function mapDocumentRequestInCombobox(documentRequest) {
    return `<option value=${documentRequest.id}>${documentRequest.name}</option>`;
}

async function filldocumentForm(serialNumber) {
    let document = await getDocument(serialNumber);
    let uploadDate = dateFormat(document.uploadDate, "yyyy-MM-dd");
    let creationDate = dateFormat(document.creationDate, "yyyy-MM-dd");

    $("#document-name").val(document.name);
    $("#document-description").val(document.description);
    $("#document-upload-date").val(uploadDate);
    $("#document-creation-date").val(creationDate);
    $("#document-type").val(document.documentType.id);
    $("#document-serie").val(document.documentSerie.code);
    $("#document-request").val(document.documentRequest.id);
}

async function editDocumentFromList(id) {
    addDocumentIdToUrl(id);
    filldocumentForm(id);
}

async function deleteDocumentFromList(id) {
    let out = await deleteDocument(id);

    fillDocumentsTable();
}

async function fillDocumentsTable() {
    let documentListGrid = $("#documents-list-grid");
    let documents = await getDocuments();

    documentListGrid.empty();

    documents.forEach(document => {
        documentListGrid.append(mapDocumentInGridItem(document));
    })
}

async function fillDocumentTypesCombobox() {
    const documentTypesCombobox = $("#document-type");

    let documentTypes = await getDocumentTypes();

    documentTypes.forEach(documentType => {
        documentTypesCombobox.append(mapDocumentTypeInCombobox(documentType));
    });
}

async function fillDocumentSeriesCombobox() {
    const documentSeriesCombobox = $("#document-serie");

    let documentSeries = await getDocumentSeries();

    documentSeries.forEach(documentSerie => {
        documentSeriesCombobox.append(mapDocumentSerieInCombobox(documentSerie));
    });
}

async function fillDocumentRequestsCombobox() {
    const documentRequestsCombobox = $("#document-request");

    let documentRequests = await getDocumentRequests();

    documentRequests.forEach(documentRequest => {
        documentRequestsCombobox.append(mapDocumentRequestInCombobox(documentRequest));
    });
}

function emptyDocumentForm() {
    $("#document-name").val(null);
    $("#document-description").val(null);
    $("#document-upload-date").val(null);
    $("#document-creation-date").val(null);
    $("#document-file").val(null);
    $("#document-type").val(null);
    $("#document-serie").val(null);
    $("#document-request").val(null);

}

function closeDocumentModal() {
    $("#documentModal").modal("hide");
}

function searchDocumentsInView() {
	$(".documents-list__item").filter(
		function() {
			$(this).toggle(
				$(this).children("div").children("div").children("div").children("div.col-md-7").children("h2.documents-list__item__title").text()
					.includes($("#search-document-name").val()));
		}
	)
}

function openRegisterDocumentModal() {
	let currentDate = dateFormat(new Date(), "yyyy-MM-dd");

	$("#document-upload-date").val(currentDate);
}

function index() {
    let saveButton = $("#save-button");
    let documentForm = $("#documentForm");

    fillDocumentsTable();
    fillDocumentTypesCombobox();
    fillDocumentSeriesCombobox();
    fillDocumentRequestsCombobox();

    /*documentForm.validate({
        messages: {
            "document-name": {
                required: "Por favor, pon un nombre de usuario",
                minlength: "Digita como mínimo 3 caracteres",
                maxlength: "Digita como máximo 30 caracteres"
            },
            "document-password": {
                required: "Por favor, pon una contraseña"
            },
            "document-email": {
                required: "Por favor, pon un email",
                min: "La cantidad mínima es 1",
                max: "La cantidad máxima es 10"
            },
            "document-role": {
                required: "Por favor, elige un rol"
            },
            "document-position": {
                required: "Por favor, pon una posición"
            }
        }
    });*/

    $("#search-document-name").keyup(function () {
        searchDocumentsInView();
    });

    saveButton.on("click", e => {
        if (documentForm.valid()) {
            saveDocument();
            emptyDocumentForm();
            closeDocumentModal();
        }
    });

    $("#documentModal").on("show.bs.modal", (e) => {
        emptyDocumentForm();
    })

    $("#documentModal").on("hidden.bs.modal", (e) => {
        removeDocumentIdToUrl();
    })
}