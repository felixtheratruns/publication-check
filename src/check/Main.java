package check;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;


public class Main {
	  public static void main(String... aArgs) throws IOException{
			
		Singleton.ENCODING = StandardCharsets.UTF_8;
		File f = new File(".","paper_finder_settings");
		
		Singleton.xml_path = new BTextPair("XML path", "data/publications.xml");

		Singleton.settings_location = new BTextPairSettings("Settings location: ",f.getAbsolutePath());
		Singleton.remove = new LTextPair("Contains","In:");
		Singleton.matches = new LTextPair("Matches", ".* [0-9][0-9][0-9][0-9]$");
		
		Singleton.path_in = new BTextPair("Path in","./rai_pub");
		Singleton.remove_name = new LTextPair("Contains name","Rai");
		Singleton.split_by = new LTextPair("Split By:", ".");
		
		Singleton.area_text = new JTextField("area text");
		Singleton.path_out = new BTextPair("Path out","./out");
		Singleton.path_pub = new BTextPair("Path pub","./pub_compare");
		
		Singleton.general_settings_key = "general";
		Singleton.settings_key = "Settings";
		
		LoadSettings settings = new LoadSettings("paper_finder_settings");
		Singleton.json_object = settings.getJSONObject();
		Singleton.global_settings = settings.getFprop();
		
		Singleton.setSettings("Rai");
		Singleton.setGeneralSettings(Singleton.general_settings_key);
    	Singleton.titles = new ArrayList<String>();
		Singleton.uploaded_pub_list = new JTextField("");
		
	    Singleton.text = new ReadWriteTextFileJDK7();	    
	    List<String> pub_lines = Singleton.text.readSmallTextFile(Singleton.path_pub.getText());
	    Singleton.uploaded_pub_list.setText(DataProc.makeBlock(pub_lines));

	   // Singleton.gui = new GUI();
	    Singleton.gui = new GUI();
	    
		/*  ArrayList<String> ar = new ArrayList<String>();
		  ar.add("ar");
		  test(ar);
		  ar.add("main");
		  printar(ar);
		  */
	    
	    
	  }
	  
	 /* 
	  public static void test(ArrayList<String> derp){
		  derp.add("test string");
		  for(int i = 0; i < derp.size(); i++){
		  	System.out.println("first" + derp.get(i));
		  }
	  }
	  	
	  
	  
	  public static void printar(ArrayList<String> ar){
		  for(int i = 0; i < ar.size(); i++){
			  System.out.println(ar.get(i));
		  }
	  }
	  */
}
