 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class LoadSettings {
	HashMap<String, HashMap<String, String>> fprop = new HashMap<String, HashMap<String, String>>();
	ArrayList<String> setting_names = null;

	
	private Set getKeysFromJObj(JSONObject jo){
		return jo.keySet();
	}

    public LoadSettings() {
		JSONParser parser = new JSONParser();
		HashMap<String, JSONObject> prop = null;
		
		setting_names = new ArrayList<String>(); 

		try {
			Object obj = null;
			try {
				obj = parser.parse(new FileReader("paper_finder_settings"));
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject jo = getSettingsObject(obj);
			setting_names.addAll(getKeysFromJObj(jo));
			
			prop = getPropertiesFromObj(jo, setting_names);
			
			HashMap<String, String> derp = new HashMap<String, String>();
			JSONObject jsonObj = null;
			Set temp = null;
			for (String key : prop.keySet()) {
				jsonObj = getSettingsObject(obj);
				temp = getKeysFromJObj((JSONObject) jsonObj.get(key));
				ArrayList<String> setting_keys = new ArrayList<String>();
				setting_keys.addAll(temp);
				derp = getPropertiesFromObjInStrings(prop.get(key), setting_keys);
				fprop.put(key, derp);
			}
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public static ArrayList<String> listPropertiesInHashMap(HashMap<String, String> map){
    	ArrayList<String> al = new ArrayList<String>();
		for (String key : map.keySet()) {
			al.add(map.get(key));
		}
		return al;
    }

     public static JSONObject getSettingsObject(Object jo){		
   		JSONObject jsonObject = (JSONObject) jo;
		JSONObject jsons = (JSONObject) jsonObject.get("Settings");
		return jsons;
    }
     
     public static JSONObject getJsonObjectAtKey(String str, JSONObject jo){
    	 return (JSONObject) jo.get(str);
     }
     
     public static HashMap<String,JSONObject> getPropertiesFromObj(JSONObject jo, ArrayList<String> keys){
    	 HashMap<String, JSONObject> properties = new HashMap<String,JSONObject>();
    	 for( String str : keys ){
    		 properties.put(str , (JSONObject) jo.get(str));
    	 }
    	 return properties;
     }
     
     public static HashMap<String,String> getPropertiesFromObjInStrings(JSONObject jo, ArrayList<String> keys){
    	 HashMap<String,String> properties = new HashMap<String,String>();
    	 for( String str : keys ){
    		 properties.put(str, jo.get(str).toString());
    	 }
    	 return properties;
     }
}