package check;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class ReadWriteTextFileJDK7 {
//tst
	  public ReadWriteTextFileJDK7(){
	  }
  
  public String makeBlock(List<String> lines, int index){
	  String retValue = lines.get(index);
	  for(int i = index+1; i < lines.size(); i++){
		  if (lines.get(i).matches("[0-9]+\\. .*")){
			  break;
		  }
		  retValue = retValue + " ---------------- " + lines.get(i);
	  }
	  return retValue;
  }

  public String getMissingTitles(List<String> titles, String text){
	  StringBuffer s = new StringBuffer();
	  text = text.toLowerCase();
	  
	  ArrayList<String> lines = new ArrayList<String>();
	  for(int i = 0; i < titles.size(); i++){
		  if(text.contains(titles.get(i).toLowerCase())){
			  System.out.println("found: " + titles.get(i));
		  } else {
			  lines.add(titles.get(i));
			  s.append(titles.get(i));
			  s.append("\n");
			  s.append("\n");
		  }
	  }
	   try {
		Singleton.text.writeSmallTextFile(lines, Singleton.path_out.getText());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//  Singleton.lns = lines;
	  return s.toString();
  }
  
  public String getTitles(List<String> lines, String contains, String matches, String path){
	  	StringBuffer s = new StringBuffer();  	
	    for (int i = 0 ; i < lines.size() ; i++){
	    	String[] vars;
	    	String title;
	    	String[] vars2;
	    	String block =null;
	    	String temp = null;
	    	
	    	if( lines.get(i).matches("[0-9]+\\. .*") ){
	    		temp =  lines.get(i)+ " ";
	        	vars = temp.split("\\. ");	
	        	for(int a = 0; a < vars.length ; a++){
	        		vars[a] = vars[a].trim();
	        	}

	        	title = this.getTitle(vars);
	        	title = title.trim();
	        	Singleton.titles.add(title);
	        	s.append(title);
	        	s.append("\n\n");
	    	}
	    }  
	   try {
		Singleton.text.writeSmallTextFile(lines, Singleton.path_out.getText());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return s.toString();
}
  
  public String makeBlock(List<String> lines){
	  	StringBuffer s = new StringBuffer();
	    for (int i = 0 ; i < lines.size() ; i++){
	    	s.append(lines.get(i).trim());
	    	s.append("\n");
	    }
	  return s.toString();
}

  public void printall(String[] a){
	  for(int i = 0; i<a.length ;i++){
		  System.out.print(a[i]);
	  }
  }
  
  private String getTitle(String[] varlen){
	  int len = 0;
	  String retVar = "";
	  for(int i = 0; i < varlen.length ; i++ ){
		  if(varlen[i].length() > len 
				  && !varlen[i].contains(Singleton.remove_name.getText()) 
				  && !varlen[i].contains(Singleton.remove.getText()) 
				  && !varlen[i].matches(Singleton.matches.getText()) ){
			  len = varlen[i].length();
			  retVar = varlen[i];
		  }
	  }
	  return retVar;
  }
  
  public String concatVarLen(String[] varlen){
	  String retVar = "";
	  for(int i = 0; i < varlen.length ; i++){
		  retVar = retVar + varlen[i];
	  }
	  return retVar;
  }
  
  public String printVarLen(String[] varlen){
	  
	  String retVar = "";
	  for(int i = 0; i < varlen.length ; i++){
		  System.out.print("\t" + i+ ": " + varlen[i]);
	  }
	  return retVar;
  }  

  //For smaller files
  public List<String> readSmallTextFile(String aFileName) throws IOException {
	Path path = Paths.get(aFileName);
    return Files.readAllLines(path, Singleton.ENCODING);
  }
  
  void writeSmallTextFile(List<String> aLines, String aFileName) throws IOException {
    Path path = Paths.get(aFileName);
    Files.write(path, aLines, Singleton.ENCODING);
  }
  
  private static void log(Object aMsg){
    System.out.println(String.valueOf(aMsg));
  }
  
} 