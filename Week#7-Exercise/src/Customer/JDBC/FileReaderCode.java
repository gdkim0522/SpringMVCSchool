package Customer.JDBC;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileReaderCode {
		public final static String NEW_LINE= "\n";
	
	    public static String readCsvFile(String fileName) {

	        BufferedReader fileReader = null;
            
	        String Num_Line="";
	        String file_lines = "";
	        try {

	        	String line="";
	        	
	            //Create the file reader
	            fileReader = new BufferedReader(new FileReader(fileName));

	            while ((line = fileReader.readLine()) != null) {
	            	file_lines=file_lines.concat(line);
	                file_lines=file_lines.concat(NEW_LINE);
	                
	            }

	        } catch (Exception e) {
	            System.out.println("Error in CsvFileReader !!!");
	            e.printStackTrace();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	                System.out.println("Error while closing fileReader !!!");
	                e.printStackTrace();
	            }
	        }
	        
	        return file_lines;
	    }
	    
	    public static void WriteFileCSV(String f, String output) throws IOException {

	        String filePath = f;
	        File file = new File(filePath);

	        FileWriter fileWriter = new FileWriter(filePath);
	    	
            fileWriter.append(output);


	        fileWriter.close();
	    }
	}