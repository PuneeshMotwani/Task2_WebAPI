/** This class contains the common classes which can be used by all the test classes during future expansion. 
 * This contains the BeforeSuite method to initialize few objects. Also, This file contains the data provider and 
 * verification methods necessary to verify the responses with expected results.
 */

package Test;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import Utility.SoftAssertor;
import Utility.LogUtil;
import Utility.MySpreadsheetIntegration;
import WebAPILibrary.JSONParsing;
import WebAPILibrary.ResponseLibrary;

public class TestCommon extends TestBed{
	public static Log logger = LogUtil.getLog(TestCommon.class); 

	
	/** This method initialize the objects for all the common classes which are used during execution.
	 * 
	 */
	@BeforeSuite()
	public void beforeSuite() {
		 resObj = new ResponseLibrary();
		 jsonParseObj = new JSONParsing();
		 sheet = new MySpreadsheetIntegration();
	}
	
	/** Data provider for test method in class "WeatherAPITestDriver"
	 * 
	 * @return
	 */
	@DataProvider()
	public Object[][] provideDataToTest(){
		Object[][] Arr = new Object [sheet.getLastRowIndex("<Last>")][2];	
			for(int i=0; i<sheet.getLastRowIndex("<Last>"); i++){
				Arr[i][0]= sheet.getUrlToTest("Test"+(i+1));
				Arr[i][1]= "Test"+(i+1);
			}
		return Arr;
	}

	
	/** This method extracts the key and value from spreadsheet(VerifyData) for the expected result and validate
	 * them against the actual jsonResponse received from API.
	 * @param jSonResponse
	 * @param testCaseIdentifier
	 */
	
	public void ValidateResponse(String jSonResponse, String testCaseIdentifier){
		  HashMap<String, String> expectedDataMap = sheet.getTestParametrsFromSheet(testCaseIdentifier);
		  String actualValue="";
		  Iterator<String> iter = expectedDataMap.keySet().iterator(); 
		 
		 while(iter.hasNext()){
			 String paramKey = iter.next();
			 try{
			  actualValue = jsonParseObj.getJsonData(jSonResponse, paramKey);
			 }catch(ParseException e){
				 e.printStackTrace();
			 }	 
			Assertresoponse(actualValue, expectedDataMap.get(paramKey), paramKey);
		 }  
	  }
		
	
	/** This method apply assert to actual and expected values and print them to console. 
	 * 
	 * @param actualValue
	 * @param expectedValue
	 * @param paramToTest
	 */
	
	public void Assertresoponse(String actualValue, String expectedValue, String paramToTest){
		SoftAssertor.assertEquals(actualValue, expectedValue, "The values of key "+ paramToTest+" does not match."+" Actual - "
					+actualValue+ " Expected - "+expectedValue);
		logger.info("Completed verification of \""+paramToTest+"\"... Actual- "+actualValue+ " ...Expected- "+expectedValue);
	}
	
	
}
