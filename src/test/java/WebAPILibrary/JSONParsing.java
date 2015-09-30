/** we need to extract the values from the JSON responses received from above class. 
 * Methods in this class returns values for specified keys of Json.
 * 
 */

package WebAPILibrary;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONParsing {
	
		HashMap<String, String > jsonMap = new HashMap<String, String>();

	
	/** This method calls decodeJson() and get back all the key and values inside json in hashMap
	 * 
	 * @param jSonResponse
	 * @param key
	 * @return JSON Value for specified key
	 * @throws ParseException
	 */
	public  String getJsonData(String jSonResponse, String key) throws ParseException{
		JSONObject jsonObject =(JSONObject) new JSONParser().parse(jSonResponse);
		HashMap<String, String > map1 = decodeJSON(jsonObject);
		return map1.get(key);
	}
	
	
	/**
	 * This method helps decoding the json data
	 * @param jsonObject
	 * @return key-value pair HashMap
	 * @throws ParseException
	 */
	
	public  HashMap<String, String> decodeJSON(JSONObject jsonObject) {	
		Set<Object> set = jsonObject.keySet();
		Iterator<Object> iter = set.iterator();
		while(iter.hasNext()){
			Object obj = iter.next();
			if (jsonObject.get(obj) instanceof JSONArray){
				try {
					getArray(jsonObject.get(obj));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				if (jsonObject.get(obj) instanceof JSONObject){
					decodeJSON((JSONObject)jsonObject.get(obj));
				}else {
					jsonMap.put(obj.toString(),  jsonObject.get(obj).toString());
	                
	            }
			}
		}
		return jsonMap;
	}
	
	
	/**
	 * This method helps breaking down the JSON array into smaller JSON structure and returns them to decodeJson()
	 * @param object2
	 * @throws ParseException
	 */
	public  void getArray(Object object2) throws ParseException{
		JSONArray jsonArr = (JSONArray) object2;
		for (int k = 0; k < jsonArr.size(); k++){
			if(jsonArr.get(k) instanceof JSONObject){
				decodeJSON((JSONObject) jsonArr.get(k));
				
			}else {
	        }
		}
		
	}	
}
