 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class LoadSettings {
	HashMap<String, HashMap<String, String>> fprop = new HashMap<String, HashMap<String, String>>();


    public LoadSettings() {
 
		JSONParser parser = new JSONParser();
		HashMap<String, JSONObject> prop = null;			

		try {
			Object obj = null;
			try {
				obj = parser.parse(new FileReader("paper_finder_settings"));
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject jo = getSettingsObject(obj);
			prop = getPropertiesFromObj(jo, Singleton.setting_names);			
			HashMap<String, String> derp = new HashMap<String, String>();
			for (String key : prop.keySet()) {
				derp = getPropertiesFromObjInStrings(prop.get(key), Singleton.setting_keys);
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