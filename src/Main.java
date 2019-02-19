import java.io.IOException;

/**
 * Challenge 1 : print histogram of letters used in a file.
 * <p>
 * For this challenge I will use Java as I believe the position at SimPrints is primarily a Java role
 *  NOTE : I have implemented some JARs see MyPdfReader & Histogram
 *
 *  USAGE : program [PATH_TO_FILE] [COUNT_SPECIAL_CHARACTERS]
 *  if there is no second argument then assume dont count specials;
 * */

public class Main {
    public static void Log(String log){
        System.out.print(log+"\n");
    }
    public static void main(String[] args) {
        Log("Simprints challenge 1");
        if(args.length == 0){
            Log("Requires file path for reading");
            return;
        }
        String path = args[0];
        boolean countSpecials = false;
        if(args.length > 1){
            countSpecials = (args[1].equals("1"));
        }
        Log("Attempting to read in file " + path);
        MyFileReader fileReader;
        if(MyFileReader.hasPdfExtension(path)){
            fileReader = new MyPdfReader();
        }else{
            fileReader = new MyFileReader();
        }

        Histogram histogram = new Histogram(countSpecials);
        fileReader.openFile(path);
        try {
            Character c;
            while ((c = fileReader.nextCharacter()) != null) {
                histogram.increment(c);
            }
        }catch (UninitializedException e){
            Log("Please initialise file reader first");
        }catch (IOException e){
            Log("Failed to read file at " + path);
        }
        Main.Log(histogram.print());

    }
}
