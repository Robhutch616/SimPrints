import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Helper class to read in files and provide file letter by letter
 */

public class MyFileReader {
    protected File file = null;
    protected int lineIndex = 0;
    protected String line = "";

    private BufferedReader br = null;

    static boolean hasPdfExtension(String absolutePath) {
        if (absolutePath.length() <= 4) {
            return false;
        }
        String lastFour = absolutePath.substring(absolutePath.length() - 4, absolutePath.length());
        return lastFour.equals(".pdf");
    }

    public MyFileReader() {
    }

    /**
     * Read in file if it exists
     *
     * @param absolutePath = absoluted path of file to read in
     * @return boolean result of success of file reading
     */
    public boolean openFile(String absolutePath) {
        this.file = new File(absolutePath);
        try {
            br = new BufferedReader(new java.io.FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.print("File does not exist at path " + absolutePath);
            return false;
        }
        return true;
    }


    /**
     * Method to get next character from file. Once the end of file is reached null is returned
     *
     * @return Next Character in the file that was opened with openFile
     * @throws UninitializedException - throws if file was not opened successfully
     * @throws IOException            - throws is there is a problem reading the line in the file
     */
    public Character nextCharacter() throws UninitializedException, IOException {
        if (!isInitialized()) {
            throw new UninitializedException("FileReader is not initialised, have you opened the file successfully?");
        }
        if (!moveToNextCharacter()) {
            return null;
        }
        return Character.toLowerCase(line.charAt(lineIndex));
    }

    protected boolean isInitialized(){
        return br != null;
    }

    protected void moveToNextLine() throws IOException {
        Main.Log("Reading new line");
        line = br.readLine();
        lineIndex = 0;
    }

    protected boolean moveToNextCharacter() throws IOException {
        lineIndex++;
        if (lineIndex >= line.length()) {
            moveToNextLine();
            while (line != null && line.length() == 0) {
                // Accounting for empty lines
                moveToNextLine();
            }
        }
        if (isEndOfFile()) {
            return false;
        }
        return true;

    }

    protected boolean isEndOfFile() {
        if (line == null) {
            Main.Log("End of the file has been reached");
            return true;
        }
        return false;
    }

    protected void findNextLineWithText() throws IOException {
        if (isEndOfFile()) {
            return;
        }
        while (line != null && line.length() == 0) {
            if (lineIndex >= line.length()) {
                System.out.print("Reading new line");
                line = br.readLine();
                lineIndex = 0;
            }
        }
    }

    private void getNextLine() {

    }

}