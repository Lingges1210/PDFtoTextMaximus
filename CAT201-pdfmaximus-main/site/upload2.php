<?php
if(isset($_POST['submit'])) {
    $file = $_FILES['fileToUpload'];

    $fileName = $_FILES['fileToUpload']['name'];
    $fileTmpName = $_FILES['fileToUpload']['tmp_name'];
    $fileSize = $_FILES['fileToUpload']['size'];
    $fileType = $_FILES['fileToUpload']['type'];
    $fileError = $_FILES['fileToUpload']['error'];

    $fileExt = explode('.', $fileName);
    $fileActualExt = strtolower(end($fileExt));

    $allowed = array('txt');

    if (in_array($fileActualExt, $allowed)) {
        if ($fileError === 0) {
            if ($fileSize < 500000) {
                $fileDestination = 'output/'.$fileName;
                move_uploaded_file($fileTmpName, $fileDestination);

                // Call the Java program to convert TXT to PDF
                exec('java -cp . TXTToPDF.java');

                header("location: convert.html");
            }
            else {
                echo "<script> alert('File size is big. Please upload file size below 500kb.');window.location='index.html' </script>";
            }
        }
        else {
            echo "<script> alert('Cannot upload file. Please try again.');window.location='index.html'</script>";
        }
    }
    else {
        echo "<script> alert('File type is not allowed. Please upload TXT file only.');window.location='index.html'</script>";
    }
}
?>
