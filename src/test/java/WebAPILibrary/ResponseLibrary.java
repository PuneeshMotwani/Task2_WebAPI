/** This class helps us to fetch the JSON response by using the HttpURLConnection class in java. 
 * Current, only the GET calls can be fetched but we can also code for other calls as well.
 * 
 */

package WebAPILibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;

import Utility.LogUtil;

public class ResponseLibrary {
	public static Log logger = LogUtil.getLog(ResponseLibrary.class); 

	/**
	 * This method returns the json response received by passing the http web call for GET request
	 * @param APIRequest
	 * @return JSON Response
	 */
	
	public String getJsonResponse(String APIRequest){
		String jsonResponse="";
		String output="";
		try {
				URL url = new URL(APIRequest);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					jsonResponse = output;
				}
				conn.disconnect();
			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }
		logger.info("JSON Response received for test is - "+jsonResponse);
		return jsonResponse;
	}
	
	
	
}
