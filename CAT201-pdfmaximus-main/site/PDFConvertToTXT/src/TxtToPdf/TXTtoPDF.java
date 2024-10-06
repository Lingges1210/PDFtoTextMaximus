package TxtToPdf;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class TXTtoPDF {

    public static void main(String[] args) throws IOException {
        // Change the file paths as needed
        String txtFilePath1 = "C:\\Users\\shivar\\OneDrive\\Documents\\CAT201-PDFMAXIMUS-main\\CAT201-PDFMAXIMUS-main\\site\\uploads";
        String pdfOutputPath1 = "C:\\Users\\shivar\\OneDrive\\Documents\\CAT201-PDFMAXIMUS-main\\CAT201-PDFMAXIMUS-main\\site\\output";

        // Perform TXT to PDF conversion
        convertTxtToPdf(txtFilePath1, pdfOutputPath1);
    }

    private static void convertTxtToPdf(String txtFilePath, String pdfOutputPath) throws IOException {
        // Read the text content from the TXT file
        String textContent = readFileContent(txtFilePath);
    
        // Create a new PDDocument object
        try (PDDocument document = new PDDocument()) {
            // Add a new page to the document
            PDPage page = new PDPage();
            document.addPage(page);
    
            // Create a PDPageContentStream object to write to the page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Set the font size and color for the text
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.setNonStrokingColor(Color.BLACK);
    
                float margin = 24;
                float yStart = page.getMediaBox().getHeight() - margin;
                float yPosition = yStart;
                float lineHeight = 12;
    
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
    
                String[] lines = textContent.split("\n");
                for (String line : lines) {
                    contentStream.showText(line);
                    yPosition -= lineHeight;
                    contentStream.newLineAtOffset(0, -lineHeight);
                }
    
                contentStream.endText();
            }
    
            // Save the document to the specified PDF file
            document.save(pdfOutputPath);
    
            System.out.println("TXT file '" + txtFilePath + "' converted to PDF successfully!");
            System.out.println("");
        }
    }
    
    private static String readFileContent(String filePath) throws IOException {
        // Read the content of the given file
        File file = new File(filePath);
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
