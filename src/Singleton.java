import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextField;


public class Singleton {
	public static List<String> titles =null;
	public static Charset ENCODING = null;
	public static JTextFieldE contains = null;
	public static JTextField uploaded_pub_list = null;
	public static JTextFieldE matches = null;
	public static JTextFieldE path_out = null;
	public static JTextFieldE path_pub = null;
	public static JTextField area_text = null;
	public static List<String> lines = null;
	public static ReadWriteTextFileJDK7 text;
	public static JTextFieldE path_in;
	public static JTextFieldE contains_name;	

	public static String settings = "Settings";
	
	public static ArrayList<String> setting_names = new ArrayList<String>();
	public static ArrayList<String> additional_name_options =new ArrayList<String>();
	public static ArrayList<String> setting_keys =new ArrayList<String>();
	/*
	public static ArrayList<String> setting_names = new ArrayList<String>(Arrays.asList(new String[]
			{"Rai","Pan"}
	));
	public static ArrayList<String> additional_name_options =new ArrayList<String>(Arrays.asList(new String[] {"All"}));
	public static ArrayList<String> setting_keys = 	new ArrayList<String>(Arrays.asList(new String[]
			{"Contains name", "Contains", "Matches", "Publications", "Path pub", "Path out", "Path in"}
	)); */
	//public static String[] setting_keys = 	{contains.getName(), matches.getName(), path_out.getName(), path_pub.getName(), path_in.getName(), contains_name.getName()};
	
	public static void setSettings(String name, HashMap<String,HashMap<String,String>> map){
		HashMap<String, String> props = map.get(name);
		
		for (String key : props.keySet()) {
			System.out.println(key);
		
		}
		System.out.println("l: " + props.get(contains.getName()));
		System.out.println("l: " + props.get(matches.getName()));
		System.out.println("l: " + props.get(path_out.getName()));
		System.out.println("l: " + props.get(path_pub.getName()));
		System.out.println("l: " + props.get(path_in.getName()));
		System.out.println("l: " + props.get(contains_name.getName()));

		contains.setText(props.get(contains.getName()));		
		matches.setText(props.get(matches.getName()));
		path_out.setText(props.get(path_out.getName()));
		path_pub.setText(props.get(path_pub.getName()));
		path_in.setText(props.get(path_in.getName()));
		contains_name.setText(props.get(contains_name.getName()));
	}
}

