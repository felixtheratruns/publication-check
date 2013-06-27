package check;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JTextField;

import org.json.simple.JSONObject;


public class Singleton {
	public static List<String> titles =null;
	public static Charset ENCODING = null;
	public static JTextField uploaded_pub_list = null;
	public static List<String> lines = null;
	public static ReadWriteTextFileJDK7 text;
	
	public static ComboBox combo_box =null;
	public static LTextPair remove = null;
	public static LTextPair matches = null;
	public static BTextPair path_in = null;
	public static LTextPair remove_name = null;
	public static JTextField area_text = null;
	public static BTextPair path_out = null;
	public static BTextPair path_pub = null;
	public static LTextPair split_by = null;
	public static BTextPairSettings settings_location = null;
	public static JSONObject json_object = null;
	public static GUI gui = null;
	
	
	public static String general_settings_key = null;
	public static String settings_key = null;
	
	
	public static BTextPair xml_path = null;
	public static HashMap<String,HashMap<String,String>> global_settings = null;

		
	public static ArrayList<String> setting_names = new ArrayList<String>();
	public static ArrayList<String> setting_keys =new ArrayList<String>();
	
	
	
	//public static String[] setting_keys = 	{contains.getName(), matches.getName(), path_out.getName(), path_pub.getName(), path_in.getName(), contains_name.getName()};
	public static void setSettings(String name){
		System.out.println("string name:" + name);
		
		JSONObject json_set = (JSONObject)json_object.get("Settings"); 
		System.out.println("json set...." + json_set);
		JSONObject cvs = (JSONObject)json_set.get("CVs");		
		System.out.println("cvs...." + cvs);

		Set names = cvs.keySet();
		
		JSONObject json_name = (JSONObject)cvs.get(name);
		//json.get(key)
		
		
		
		Singleton.combo_box = new ComboBox(names, name);

		remove_name.setText(json_name.get(remove_name.getId()).toString());
		remove.setText(json_name.get(remove.getId()).toString());		
		matches.setText(json_name.get(matches.getId()).toString());
		path_in.setText(json_name.get(path_in.getId()).toString());
		path_out.setText(json_name.get(path_out.getId()).toString());
		path_pub.setText(json_name.get(path_pub.getId()).toString());
			
		setting_names.addAll(json_set.keySet());
		setting_keys.addAll(json_name.keySet());
		
	///	setGeneralSettings(general_settings_key);
		
		
		System.out.println("l: " + json_name.get(remove.getId()));
		System.out.println("l: " + json_name.get(matches.getId()));
		System.out.println("l: " + json_name.get(path_out.getId()));
		System.out.println("l: " + json_name.get(path_pub.getId()));
		System.out.println("l: " + json_name.get(path_in.getId()));
		System.out.println("l: " + json_name.get(remove_name.getId()));
	}
	
	public static void setGeneralSettings(String str){
		JSONObject json = (JSONObject) json_object.get(settings_key);	
		System.out.println("json string:" + json.toString());
		JSONObject json2 = (JSONObject) json.get(general_settings_key);
		System.out.println("json string:" +json2.toString());

		String r = xml_path.getId();
	//	String r = json2.get( xml_path.getId()).toString();
		String s = json2.get( xml_path.getId()).toString();
		
		xml_path.setText(s);

		
	//	xml_path.setText( json2.get(xml_path.getId()).toString() );
	}
}

