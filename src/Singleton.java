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
	//public static GUI gui = null;
	

	public static HashMap<String,HashMap<String,String>> global_settings = null;
	/*
	public static ArrayList<String> setting_names = new ArrayList<String>(Arrays.asList(new String[]
			{"Rai","Pan"}
	));
	public static ArrayList<String> additional_name_options =new ArrayList<String>(Arrays.asList(new String[] {"All"}));
	public static ArrayList<String> setting_keys = 	new ArrayList<String>(Arrays.asList(new String[]
			{"Contains name", "Contains", "Matches", "Publications", "Path pub", "Path out", "Path in"}
	)); 
	*/
		
	public static ArrayList<String> setting_names = new ArrayList<String>();
	public static ArrayList<String> setting_keys =new ArrayList<String>();
	
	
	
	//public static String[] setting_keys = 	{contains.getName(), matches.getName(), path_out.getName(), path_pub.getName(), path_in.getName(), contains_name.getName()};
	public static void setSettings(String name){
		HashMap<String, String> props = global_settings.get(name);	
		for (String key : props.keySet()) {
			System.out.println(key);
		}
		
		Set names = global_settings.keySet();
		Singleton.combo_box = new ComboBox(names, name);

		remove_name.setText(props.get(remove_name.getId()));
		remove.setText(props.get(remove.getId()));		
		matches.setText(props.get(matches.getId()));
		path_in.setText(props.get(path_in.getId()));
		path_out.setText(props.get(path_out.getId()));
		path_pub.setText(props.get(path_pub.getId()));
		setting_names.addAll(global_settings.keySet());
		setting_keys.addAll(props.keySet());
		
		
		System.out.println("l: " + props.get(remove.getId()));
		System.out.println("l: " + props.get(matches.getId()));
		System.out.println("l: " + props.get(path_out.getId()));
		System.out.println("l: " + props.get(path_pub.getId()));
		System.out.println("l: " + props.get(path_in.getId()));
		System.out.println("l: " + props.get(remove_name.getId()));
		
	}
}

