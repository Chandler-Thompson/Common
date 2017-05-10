package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chandler
 */
public class TextFileManipulator {
    
    protected void createCSVFile(String filePath, ArrayList<String> csvFormat, ArrayList<ArrayList<String>> data){
        
        File f = new File(filePath);
        f.delete();
        
        String csvLine = "";
        
        if(csvFormat.size() != data.size()){
            System.err.println("|COMMON| [TextFileManipulator] (createCSVFile): CSV format size is not the same size as structure of 2D List containing data.");
            return;
        }
        
        System.out.println("|COMMON| [TextFileManipulator] (createCSVFile) <" + filePath + "> Saving file");
        
        //create first csv line that outlines what the following information is
        for(int i = 0; i < csvFormat.size(); i++){
            if(i != 0)
                csvLine += ",";
            
            csvLine += csvFormat.get(i);
        }
        
        try (PrintStream out = new PrintStream(f)) { //prints to file

            out.println(csvLine);//output main csv line

            //output the rest of the data
            for(int i = 0; i < data.get(0).size(); i++){
                for(int a = 0; a < data.size(); a++){//using first List because all lists should have the same number of entries (check for this is above)
                    if(a != 0)
                        out.print(",");
                        
                    out.print(data.get(a).get(i));
                }
                out.print("\n");//move down to next entry
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextFileManipulator.class.getName()).log(Level.SEVERE, null, ex);
        }//end try : catch
        
    }
    
    protected ArrayList<String> createBackup(String filePath) throws IOException{
        
        ArrayList<String> backup = new ArrayList<>();
        
        System.out.println("|COMMON| [TextFileManipulator] (createBackup) creating a backup...");
        
        int lines = readNumLines(filePath);
        
        FileReader fr = new FileReader(filePath);
        BufferedReader textReader = new BufferedReader(fr);
        
        for(int i = 0; i < lines; i++){
            backup.add(textReader.readLine());
            System.out.println("|COMMON| [TextFileManipulator] (createBackup) line " + i + ": " + backup.get(i));
        }//end for
        
        System.out.println("|COMMON| [TextFileManipulator] (createBackup) <" + filePath + "> backup created.");
        textReader.close();
        
        return backup;
        
    }//end ArrayList<String> createBackup
    
    protected int readNumLines(String filePath) throws IOException{
        
        FileReader file_to_read = new FileReader(filePath);
        int numOfLines;
        
        try (BufferedReader bf = new BufferedReader(file_to_read)) {
            numOfLines = 0;
            while( bf.readLine() != null ){
                numOfLines++;
            }//end while
            
        }//end try
        
        System.out.println("|COMMON| [TextFileManipulator] (readNumLines) <" + filePath + "> Number of lines in file: " + numOfLines);
        
        return numOfLines;
        
    }//end int readNumLines
    
}
