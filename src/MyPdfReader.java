import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

/**
 * File Reader used for pdfs - This has one minor drawback in that it requires reading in the full file at once.
 *  Which could be a problem for large files
 * gradle : // https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox
 * compile group: 'org.apache.pdfbox', name: 'pdfbox', version: '2.0.1'
 *
 * Or JARS:
 * https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox/2.0.1
 * https://mvnrepository.com/artifact/org.apache.pdfbox/fontbox/2.0.0
 * http://www.java2s.com/Code/Jar/c/Downloadcommonslogging1211jar.htm
 */
public class MyPdfReader extends MyFileReader {
    private String[] lines = null;
    private int lastLineIndex = 0;

    public MyPdfReader() {
        super();
    }

    @Override
    public boolean openFile(String absolutePath) {
        file = new File(absolutePath);
        if (!file.exists()) {
            System.out.print("File does not exist at path " + absolutePath);
            return false;
        }
        try (PDDocument document = PDDocument.load(file)) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                lines = pdfFileInText.split("\\r?\\n");
                return true;
            } else {
                Main.Log("Pdf file was encrypted");
                return false;
            }
        }catch(IOException e){
            Main.Log("Failed to read pdf file. IOException");
            return false;
        }
    }

    @Override
    protected boolean isInitialized() {
        return lines != null;
    }

    @Override
    protected void moveToNextLine() throws IOException {
        lastLineIndex++;
        lineIndex = 0;
        if(lastLineIndex < lines.length){
            line = lines[lastLineIndex];
        }else{
            line = null;
        }
    }

}
