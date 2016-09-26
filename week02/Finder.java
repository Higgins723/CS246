/*
Code by Joshua Higgins
*/

//imports for the file reading
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//imports for the map(aka a dictionary in python)
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

class Finder {

    public Finder() {

    }

    public static void main(String[] args) {
        //this is the topics file passed in from the user in the command line
        String topics_file = args[0];
        //this is the directory of the files to check passed in from the user
        String directory_files = args[1];

        //This creates the dictionary of keyword topics 
        if(args.length < 1) {
            System.out.println("Usage: java WordCounter <fileToRead>");
            System.exit(-1);
        }
        
        Topic counter = new Topic();
        
        try{
            //System.out.println("Opening file '" + topics_file + "'");
            if(!counter.readFile(topics_file)) {
                System.out.println("Error reading '" + topics_file + "'");
            }
        }
        catch(Exception e) {
            System.out.println("Error reading '" + topics_file + "'");
            System.out.println(e.getMessage());
        }
        System.out.println(counter.ReturnMap());
    }
}

class Topic {
    
    //this is the map that the topics get saved in
    Map<Object, ArrayList<Object>> TopicMap = new HashMap<Object, ArrayList<Object>>();

    public Topic() {

    }

    Map ReturnMap() {
        return TopicMap;
    } 

    public Boolean readFile(String filename) throws Exception{
        
        // Check if we can read the file
        if(!isGoodFile(filename)) {
            return false;
        }
            
        try(BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            
            String line;
            
            // readLine() returns null when the file ends.
            while((line = buffer.readLine()) != null) {
                countWords(line);
            }       
        } 
        catch (FileNotFoundException e) {
            Exception ex = new Exception("Exception while reading file", e);
            throw ex;
        }
        catch (IOException e) {
            Exception ex = new Exception("Exception while reading file", e);
            throw ex;
        }
        return true;
    }
    
    private Boolean isGoodFile(String filename) {
        File theFile = new File(filename);
        
        if(theFile.exists() && theFile.canRead()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    private Map countWords(String line) {
        String words = line;
        String key = words.split(":")[0];
        String value = words.split(":")[1];
        int value_words_len = value.split(",").length;
        ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<Object> key_value = new ArrayList<Object>();
        key_value.add(key); 
        for (int i=0; i<value_words_len; i++) {
            list.add(value.split(",")[i]);
        }
        TopicMap.put(key_value, list);
        return TopicMap;
    }
}    
