document.addEventListener('DOMContentLoaded', function () {
  // About Us Section Scroll Effect
  const aboutUsSection = document.getElementById('about-us-section');

  window.addEventListener('scroll', function () {
    const aboutUsSectionBottom = aboutUsSection.offsetTop + aboutUsSection.offsetHeight;

    if (window.pageYOffset > aboutUsSectionBottom - window.innerHeight) {
      aboutUsSection.style.opacity = 1;
    }
  });

  // File Input and Drag-and-Drop functionality
  const fileInput = document.getElementById('pdfFiles');
  const dropZone = document.getElementById('drop-zone');
  const progressBar = document.getElementById('progressBar');
  const downloadLink = document.getElementById('downloadLink');

  // Prevent default behavior to allow dropping files
  dropZone.addEventListener('dragover', function (e) {
    e.preventDefault();
    dropZone.classList.add('dragover');
  });

  dropZone.addEventListener('dragleave', function () {
    dropZone.classList.remove('dragover');
  });

  dropZone.addEventListener('drop', function (e) {
    e.preventDefault();
    dropZone.classList.remove('dragover');

    const droppedFiles = e.dataTransfer.files;

    if (droppedFiles.length > 0) {
      fileInput.files = droppedFiles;
      // You can also trigger your existing file handling logic here
      // For example: convertFile(fileInput.files);
    }
  });

  // Existing File Input Change Event
  fileInput.addEventListener('change', function () {
    const selectedFile = fileInput.files[0];

    if (selectedFile && selectedFile.type === 'application/pdf') {
      progressBar.style.display = 'block';
      downloadLink.style.display = 'none';

      const convertFile = () => {
        const formData = new FormData();
        formData.append('pdfFile', selectedFile);

        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'convert.php');
        xhr.upload.addEventListener('progress', function (event) {
          const progress = (event.loaded / event.total) * 100;
          progressBar.style.width = `${progress}%`;
        });

        xhr.onload = function () {
          if (xhr.status === 200) {
            const downloadURL = JSON.parse(xhr.responseText).downloadURL;
            downloadLink.href = downloadURL;
            downloadLink.style.display = 'block'; // Display the download link
          }
        };

        xhr.send(formData); // Send the form data for conversion
      };

      convertFile(); // Call the convertFile function when a valid file is selected
    }
  });
});
