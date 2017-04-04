import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ValueSetter {
	private String fileName;
	private String line;
	
	public ValueSetter(){
		fileName = "temp.txt";
		line = null;
	}
	public void readVals(){
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            FRCTrial.h_min = Integer.parseInt(bufferedReader.readLine().substring(7));
            FRCTrial.h_max = Integer.parseInt(bufferedReader.readLine().substring(7));
            FRCTrial.s_min = Integer.parseInt(bufferedReader.readLine().substring(7));
            FRCTrial.s_max = Integer.parseInt(bufferedReader.readLine().substring(7));
            FRCTrial.v_min = Integer.parseInt(bufferedReader.readLine().substring(7));
            FRCTrial.v_max = Integer.parseInt(bufferedReader.readLine().substring(7));
//            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//                line = line.substring(7);
//                
//            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}
	public void writeVals(){
		try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("H_MIN: " + FRCTrial.h_min);
            bufferedWriter.newLine();
            bufferedWriter.write("H_MAX: " + FRCTrial.h_max);
            bufferedWriter.newLine();
            bufferedWriter.write("S_MIN: " + FRCTrial.s_min);
            bufferedWriter.newLine();
            bufferedWriter.write("S_MAX: " + FRCTrial.s_max);
            bufferedWriter.newLine();
            bufferedWriter.write("V_MIN: " + FRCTrial.v_min);
            bufferedWriter.newLine();
            bufferedWriter.write("V_MAX: " + FRCTrial.v_max);
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
	
}
