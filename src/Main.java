import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;


public class Main {
	//  final static Charset ENCODING = StandardCharsets.UTF_8;
	//  final static String OUTPUT_FILE_NAME = "/home/joel/proj/out";
	//  final static String FILE_NAME = "/home/joel/proj/rai_pub2";
	 // final static String 
	  public static void main(String... aArgs) throws IOException{
		Singleton.ENCODING = StandardCharsets.UTF_8;
		
		Singleton.contains = new JTextFieldE("Contains","In:");
		Singleton.matches = new JTextFieldE("Matches", ".* [0-9][0-9][0-9][0-9]$");
		Singleton.path_in = new JTextFieldE("Path in","/home/joel/proj/rai_pub");
		Singleton.contains_name = new JTextFieldE("Contains name","Rai");
		Singleton.area_text = new JTextField("area text");
		
    	Singleton.titles = new ArrayList<String>();
    	
		Singleton.path_out = new JTextFieldE("Path out","/home/joel/proj/out");
		Singleton.path_pub = new JTextFieldE("Path pub","/home/joel/proj/pub_compare");
		
		Singleton.uploaded_pub_list = new JTextField("");
		
		LoadSettings settings = new LoadSettings();
		Singleton.setSettings("Rai", settings.fprop);
		
	    ReadWriteTextFileJDK7 text = new ReadWriteTextFileJDK7();
	    Singleton.text = text;
	    
	    List<String> pub_lines = text.readSmallTextFile(Singleton.path_pub.getText());
	    
	    Singleton.uploaded_pub_list.setText(text.makeBlock(pub_lines));

	    new GUI();
	  }
	  

	
}
