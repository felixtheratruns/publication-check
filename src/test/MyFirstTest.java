package test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import check.LoadSettings;
import check.ReadWriteTextFileJDK7;
import check.Singleton;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyFirstTest {
	LoadSettings settings = null;
    @Test
    public void  LoadSettings() {
		LoadSettings settings = new LoadSettings("paper_finder_settings");
		ArrayList<String> a = settings.getSettingNames();
		assertTrue(a.size() > 0);	
    }   
    @Test
    public void  Fprop() {
    	assertTrue(settings.getFprop().size()>0);
    }
    
    @Test
    public void ReadWriteTextFileJDK7(){
    	Singleton.text = new ReadWriteTextFileJDK7();
    }
    
    @Test
    public void readSmallTextFile() throws IOException{
    	List<String> pub_lines = Singleton.text.readSmallTextFile(Singleton.path_pub.getText());
    	Singleton.uploaded_pub_list.setText(Singleton.text.makeBlock(pub_lines));
    }
}
