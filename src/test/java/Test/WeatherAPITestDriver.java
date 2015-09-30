/** 1) This class contains the test method that drives the execution by receiving the input parameters from Data Provider. 
 * This test basically calls different functions - First receives the https GET response from server and pass it to the 
 * Json parser to retrieve the actual values from the response.
 */

package Test;

import org.apache.commons.logging.Log;
import org.testng.annotations.Test;
import Utility.LogUtil;

public class WeatherAPITestDriver extends TestCommon {
	public static Log logger = LogUtil.getLog(WeatherAPITestDriver.class); 
	
	/** This method receives the entry point url from the data provider. Get the JSON response and 
	 * validates the JSON values specified in the data sheet.
	 * @param queryUrl
	 * @param testCaseIdentifier
	 */
	
  @Test(dataProvider="provideDataToTest")
  public void ValidateAPIResponses(String queryUrl, String testCaseIdentifier )  {
	  	
	  	logger.info("******Starting verification of "+testCaseIdentifier+"******");
		String jSonResponse = resObj.getJsonResponse(queryUrl);
		ValidateResponse(jSonResponse, testCaseIdentifier);
		logger.info("******Completed verification of "+testCaseIdentifier+"******");
  }
  
  
  

  
}
