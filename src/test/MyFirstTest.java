package test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import check.BTextPair;
import check.BTextPairSettings;
import check.DataProc;
import check.LTextPair;
import check.LoadSettings;
import check.ReadWriteTextFileJDK7;
import check.Singleton;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyFirstTest {
	LoadSettings settings = null;
    @Test
    public void  LoadSettings() throws IOException {
    	/*
		settings = new LoadSettings("paper_finder_settings");
		ArrayList<String> a = settings.getSettingNames();
		assertTrue(a.size() > 0);    	
		assertTrue(settings.getFprop().size()>0);
    	Singleton.text = new ReadWriteTextFileJDK7();
    	List<String> pub_lines = null;
		try {
			pub_lines = Singleton.text.readSmallTextFile(Singleton.path_pub.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Singleton.uploaded_pub_list.setText(FilterFile.makeBlock(pub_lines));
    	
    	*/
		Singleton.ENCODING = StandardCharsets.UTF_8;
		File f = new File(".","paper_finder_settings");

		Singleton.settings_location = new BTextPairSettings("Settings location: ",f.getAbsolutePath());
		Singleton.remove = new LTextPair("Contains","In:");
		Singleton.matches = new LTextPair("Matches", ".* [0-9][0-9][0-9][0-9]$");
		Singleton.path_in = new BTextPair("Path in","./rai_pub");
		Singleton.remove_name = new LTextPair("Contains name","Rai");
		Singleton.split_by = new LTextPair("Split By:", ".");
		Singleton.area_text = new JTextField("area text");
		Singleton.path_out = new BTextPair("Path out","./out");
		Singleton.path_pub = new BTextPair("Path pub","./pub_compare");
    	
		LoadSettings settings = new LoadSettings("paper_finder_settings");
		Singleton.global_settings = settings.getFprop();
		Singleton.setSettings("Rai");
    	Singleton.titles = new ArrayList<String>();
		Singleton.uploaded_pub_list = new JTextField("");
		
	    Singleton.text = new ReadWriteTextFileJDK7();
	    List<String> pub_lines = Singleton.text.readSmallTextFile(Singleton.path_pub.getText());
	    
	    assertTrue(pub_lines.size()>0);
	    Singleton.uploaded_pub_list.setText(DataProc.makeBlock(pub_lines));


    }   
    @Test
    public void  Fprop() {
  //  	assertTrue(settings.getFprop().size()>0);
    }
    
    @Test
    public void ReadWriteTextFileJDK7(){
    //	Singleton.text = new ReadWriteTextFileJDK7();
    }
    
    @Test
    public void readSmallTextFile() throws IOException{
 //   	List<String> pub_lines = Singleton.text.readSmallTextFile(Singleton.path_pub.getText());
 //   	Singleton.uploaded_pub_list.setText(FilterFile.makeBlock(pub_lines));
    }
}
