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

function dateFormat(inputDate, format) {
	const date = new Date(inputDate);

	const day = date.getDate();
	const month = date.getMonth() + 1;
	const year = date.getFullYear();

	format = format.replace("MM", month.toString().padStart(2, "0"));

	if (format.indexOf("yyyy") > -1) {
		format = format.replace("yyyy", year.toString());
	} else if (format.indexOf("yy") > -1) {
		format = format.replace("yy", year.toString().substr(2, 2));
	}

	format = format.replace("dd", day.toString().padStart(2, "0"));

	return format;
}

$(document).ready(function () {
    index();
});