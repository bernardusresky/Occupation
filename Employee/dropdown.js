function _(el){
	return document.getElementById(el);
}

function fileUpload(){
	var file = _("filetoupload").files[0];
	alert(file.name+" | "+file.size+" | "+file.type);
	var formdata = new FormData();
	formdata.append("filetoupload", file);
	var ajax = new XMLHttpRequest();
	ajax.upload.addEventListener("progress", progressHandler, false);
	ajax.addEventListener("load", completeHandler, false);
	ajax.addEventListener("error", errorHandler, false);
	ajax.addEventListener("abort", abortHandler, false);
	ajax.open("POST", "resume_upload_process.php");
	ajax.send(formdata);
}

function progressHandler(event){
	var percentage = (event.loaded / event.total) * 100;
    var pbart = document.getElementById("pbart");
    var pbar = document.getElementById("pbar");
    pbar.style.width = percentage+"%";
    pbart.innerHTML = percentage+"%";
}

function completeHandler(event){
	_("stats").innerHTML = event.target.responseText;
	_("progBar").value = 0;
}

function errorHandler(event){
	_("stats").innerHTML = "Upload Fail!!";
}

function abortHandler(event){
	_("stats").innerHTML = "Upload Canceled";
}

function drag_drop(event) {
    event.preventDefault();
    var file = event.dataTransfer.files[0];
    var formdata = new FormData();
	formdata.append("drop_box", file);
	var ajax = new XMLHttpRequest();
	ajax.upload.addEventListener("progress", progressHandler, false);
	ajax.addEventListener("load", completeHandler, false);
	ajax.addEventListener("error", errorHandler, false);
	ajax.addEventListener("abort", abortHandler, false);
	ajax.open("POST", "resume_upload_process..php");
	ajax.send(formData);
}
