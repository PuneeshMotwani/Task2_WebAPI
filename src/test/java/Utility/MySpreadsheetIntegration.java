package Utility;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

 
public class MySpreadsheetIntegration {
	
	public String csvFile = System.getProperty("user.dir")+"//Files//TestData.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	
	
/** This method returns the entry point Url from sheet associated to searchKey passed
 * 
 * @param searchKey
 * @return Entry Point Urls
 */
	
  public String getUrlToTest(String searchKey) {
	  String searchValue="";
	  try {
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {				
		        // use comma as separator
			String[] dataArray = line.split(cvsSplitBy);
			if(dataArray[1].equalsIgnoreCase(searchKey)){
				searchValue = dataArray[2];
				break;
			}
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
	finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	return searchValue;
  }
 
  
  /** This method retruns the last row number in the spreadsheet
   * 
   * @param searchKey
   * @param searchIdentifierColNum
   * @param seachValueColNum
   * @return Last Row Number
   */
  
  public int getLastRowIndex(String searchKey) {
	  int LastRowNum=0, i=0;
	  try {
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {				
		        // use comma as separator
			String[] dataArray = line.split(cvsSplitBy);
			if(dataArray[1].equalsIgnoreCase(searchKey)){
				LastRowNum = i;
				break;
			}
			i++;
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
	finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	return LastRowNum;
  }
 
  
  /** This method returns the HashMap of expected Values to be tested from JSON
   * 
   * @param testCaseIdentifier
   * @return
   */
  public HashMap<String, String> getTestParametrsFromSheet(String testCaseIdentifier) {	
	  HashMap<String, String> ParamToTestMap = new HashMap<String, String>();
	  try {
		  br = new BufferedReader(new FileReader(csvFile));
		  while ((line = br.readLine()) != null) {	
		        // use comma as separator
			String[] dataArray = line.split(cvsSplitBy);
			if(dataArray[4].equalsIgnoreCase(testCaseIdentifier)){
				ParamToTestMap.put(dataArray[5], dataArray[6] );
			}
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 

	finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	  
	return ParamToTestMap;
  }
  
}