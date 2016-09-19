import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCounter {
 
    public static void readFileAndPrintCounts(String crunchifyFile) throws FileNotFoundException {
 
        int crunchifyTotalWords = 0;
        int crunchifyTotalLines = 0;
 
        String crunchifyLine;
 
        try (BufferedReader crunchifyBuffer = new BufferedReader(new FileReader(crunchifyFile))) {
            // read each line one by one
            while ((crunchifyLine = crunchifyBuffer.readLine()) != null) {
                crunchifyTotalLines++;
 
                // ignore multiple white spaces
                String[] myWords = crunchifyLine.replaceAll("\\s+", " ").split(" ");
 
                crunchifyTotalWords += myWords.length;
                crunchifyLog(crunchifyTotalWords + ": " + crunchifyLine);
                crunchifyTotalWords = 0;
 
            }
        } 
        catch (IOException e) {
            crunchifyLog("Error reading '" + crunchifyFile + "' ...");    
        }
    }
 
    private static void crunchifyLog(String string) {
        System.out.println(string);
    }
 
    public static void main(String[] args) {
        try {
            String file = args[0];
            crunchifyLog("Opening file '" + file + "' ...");
            System.out.println("");
            readFileAndPrintCounts(file);
        } 
        catch (FileNotFoundException e) {
            crunchifyLog("Opening file '" + args[0] + "' ...");
            System.out.println("");
            crunchifyLog("Error reading '" + args[0] + "' ...");    
        }
    }
}
