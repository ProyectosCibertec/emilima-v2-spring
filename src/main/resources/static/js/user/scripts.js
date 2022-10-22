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

function getUsers() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/user/` });

    return out;
}

function getUser(id) {
    let out = $.ajax({ method: "GET", url: `${URL}/api/user/${id}` });

    return out;
}

function getUserRoles() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/user-role/` });

    return out;
}

function getUserPositions() {
    let out = $.ajax({ method: "GET", url: `${URL}/api/user-position/` });

    return out;
}

function postUser(user) {
    let out = $.ajax({
        method: "POST", url: `${URL}/api/user/`, data: JSON.stringify({
            username: user.username,
            password: user.password,
            email: user.email,
            userRole: {
                id: user.userRole.id,
                name: null
            },
            userPosition: {
                id: user.userPosition.id,
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

function deleteUser(id) {
    let out = $.ajax({ method: "DELETE", url: `${URL}/api/user/${id}` });

    return out;
}

function addUserIdToUrl(id) {
    history.pushState({}, '', location.pathname.concat(id));
}

function removeUserIdToUrl() {
    history.pushState({}, '', location.pathname.substring(0, location.pathname.lastIndexOf("/") + 1));
}

async function saveUser() {
    let id = location.pathname.substring(location.pathname.lastIndexOf("/") + 1, location.pathname.length);

    let user = {
        username: id == '' ? $("#user-name").val() : id,
        password: $("#user-password").val(),
        email: $("#user-email").val(),
        userRole: {
            id: parseInt($("#user-role").val(), 10),
            name: null
        },
        userPosition: {
            id: parseInt($("#user-position").val(), 10),
            name: null
        }
    };

    let out = await postUser(user);

    fillUsersTable();
}

function mapUserInTableRow(user) {
    return `<tr>
                    <td scope="col">${user.username}</td>
                    <td scope="col">${user.email}</td>
                    <td scope="col">${user.userRole.name}</td>
                    <td scope="col">${user.userPosition.name}</td>
                    <td scope="col">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal" onclick="editUserFromList('${user.username}')" data-bs-toggle="modal" data-bs-target="#userModal">Editar</button>
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="deleteUserFromList('${user.username}')">Eliminar</button>
                    </td>
                </tr>
                `;
}

function mapUserRoleInCombobox(userRole) {
    return `<option value=${userRole.id}>${userRole.name}</option>`;
}

function mapUserPositionInCombobox(userPosition) {
    return `<option value=${userPosition.id}>${userPosition.name}</option>`;
}

async function fillUserForm(id) {
    let user = await getUser(id);

    $("#user-name").val(user.username);
    $("#user-password").val(user.password);
    $("#user-email").val(user.email);
    $("#user-role").val(user.userRole.id);
    $("#user-position").val(user.userPosition.id);
}

async function editUserFromList(id) {
    addUserIdToUrl(id);
    fillUserForm(id);
}

async function deleteUserFromList(id) {
    let out = await deleteUser(id);

    fillUsersTable();
}

async function fillUsersTable() {
    const usersTableBody = $("#users-table__body");

    usersTableBody.empty();

    let users = await getUsers();

    users.forEach(user => {
        usersTableBody.append(mapUserInTableRow(user));
    })
}

async function fillUserRolesCombobox() {
    const userRolesCombobox = $("#user-role");

    let userRoles = await getUserRoles();

    userRoles.forEach(userRole => {
        userRolesCombobox.append(mapUserRoleInCombobox(userRole));
    });
}

async function fillUserPositionsCombobox() {
    const userPositionsCombobox = $("#user-position");

    let userPositions = await getUserPositions();

    userPositions.forEach(userRole => {
        userPositionsCombobox.append(mapUserPositionInCombobox(userRole));
    });
}

function emptyUserForm() {
    $("#user-name").val(null);
    $("#user-password").val(null);
    $("#user-email").val(null);
    $("#user-role").val(null);
    $("#user-position").val(null);
}

function closeUserModal() {
    $("#userModal").modal("hide");
}

function index() {
    let saveButton = $("#save-button");
    let userForm = $("#userForm");

    fillUsersTable();
    fillUserRolesCombobox();
    fillUserPositionsCombobox();

    /*userForm.validate({
        messages: {
            username: {
                required: "Por favor, pon un nombre de usuario",
                minlength: "Digita como mínimo 3 caracteres",
                maxlength: "Digita como máximo 30 caracteres"
            },
            password: {
                required: "Por favor, pon una contraseña"
            },
            email: {
                required: "Por favor, pon un email",
                min: "La cantidad mínima es 1",
                max: "La cantidad máxima es 10"
            },
            "user-role": {
                required: "Por favor, elige un rol"
            },
        }
    });*/

    saveButton.on("click", e => {
        if (userForm.valid()) {
            saveUser();
            emptyUserForm();
            closeUserModal();
        }
    });

    $("#userModal").on("show.bs.modal", (e) => {
        emptyUserForm();
    })

    $("#userModal").on("hidden.bs.modal", (e) => {
        removeUserIdToUrl();
    })
}

$(document).ready(function () {
    index();
});