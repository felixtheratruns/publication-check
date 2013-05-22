import java.io.IOException;
import java.util.List;


public class Settings {
	
	public Settings(){
		
		
	}
	
	public void LoadSettings(String path) throws IOException{
		List<String> lines = Singleton.text.readSmallTextFile(path);
		String area = Singleton.text.makeBlock(lines);
		
		
	}

}
