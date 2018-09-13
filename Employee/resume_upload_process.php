<?php
$target_dir = "Uploads/Resume/";
$uploadOk = 1;
$target_file = $target_dir . basename($_FILES["filetoupload"]["name"]);
$resumeFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));


// Check if file is exist
//if(!$_FILES["filetoupload"]["tmp_name"]) {
//    echo "Please pick a document!";
//    $uploadOk = 0;
//} else {
//     $uploadOk = 1;
//}


//Check whether file is already exist in the folder
if (file_exists($target_file)) {
    echo "Sorry, file already exists.";
    
    $uploadOk = 0;
}
//Check and allow specified file format
if($resumeFileType != "doc" && $resumeFileType != "pdf" ) {
   echo "Sorry, only Doc & Pdf files are allowed to be uploaded.";
   $uploadOk = 0;
}

// Check if $uploadOk is set to 0 by an error
if ($uploadOk == 0) {
    echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
} else {
    if (move_uploaded_file($_FILES["filetoupload"]["tmp_name"], $target_file)) {
        echo "The file ". basename( $_FILES["filetoupload"]["name"]). " has been uploaded.";
    } else {
        echo "Sorry, there was an error uploading your file.";
    }
}

?>