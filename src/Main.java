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
		Singleton.contains = new JTextField("In:");
		Singleton.matches = new JTextField(".* [0-9][0-9][0-9][0-9]$");
		Singleton.path_in = new JTextField("/home/joel/proj/rai_pub");
		Singleton.contains_name = new JTextField("Rai");
		Singleton.area_text = new JTextField("area text");
		
    	Singleton.titles = new ArrayList<String>();
    	
		Singleton.path_out = new JTextField("/home/joel/proj/out");
		Singleton.path_pub = new JTextField("/home/joel/proj/pub_compare");
		
		Singleton.comparator = new JTextField("");
		
	    ReadWriteTextFileJDK7 text = new ReadWriteTextFileJDK7();
	    Singleton.text = text;
	    
	    List<String> pub_lines = text.readSmallTextFile(Singleton.path_pub.getText());
	    
	    Singleton.comparator.setText(text.makeBlock(pub_lines));

	    new GUI();
	  }
	
}
