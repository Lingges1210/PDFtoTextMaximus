import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFtoTXT {
    public static void main(String[] args) throws IOException {

        //Loading an existing document
        File pdfFile = new File("C:\\Users\\shivar\\OneDrive\\Desktop\\CAT201-EasyConvert-main\\site\\uploads");
        PDDocument document = PDDocument.load(pdfFile);

        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();

        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);

        File txtFile = new File("");
        FileWriter fw = new FileWriter(txtFile);

        fw.write(text);
        fw.close();
        document.close();
    }
}
