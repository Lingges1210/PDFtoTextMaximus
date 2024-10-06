<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST' && file_exists('uploads/test.pdf')) {
    if (isset($_POST['submit'])) {
        //chdir('PDFConvertToTXT');
        exec('javac -cp .;fontbox-2.0.24.jar;pdfbox-2.0.24.jar;pdfbox-app-2.0.24.jar;pdfbox-tools-2.0.24.jar;preflight-2.0.24.jar;preflight-app-2.0.24.jar;xmpbox-2.0.24.jar PDFtoTXT.java 2>&1');
        exec('java -cp .;fontbox-2.0.24.jar;pdfbox-2.0.24.jar;pdfbox-app-2.0.24.jar;pdfbox-tools-2.0.24.jar;preflight-2.0.24.jar;preflight-app-2.0.24.jar;xmpbox-2.0.24.jar PDFtoTXT 2>&1');
        header("location: download.html");
    }
}
