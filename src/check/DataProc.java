package check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProc {
	 public static String makeBlock(List<String> lines, int index){
		  String retValue = lines.get(index);
		  for(int i = index+1; i < lines.size(); i++){
			  if (lines.get(i).matches("[0-9]+\\. .*")){
				  break;
			  }
			  retValue = retValue + " ---------------- " + lines.get(i);
		  }
		  return retValue;
	  }
	 
	 public static String getTitlesNew(List<String> lines){
		 ReadWriteTextFileJDK7 rwtf = new ReadWriteTextFileJDK7();
		 List<String> published = null;
		 try {
			published = rwtf.readSmallTextFile(Singleton.path_pub.getText());
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 String cur_uppub = null;
		 String[] cur_uppub_split = null;
		 for(int i = 0; i < published.size() ; i++){
			 cur_uppub = published.get(i);
			 cur_uppub = cur_uppub.toLowerCase();
			 if(cur_uppub.matches("^[ \t]+") || cur_uppub.contains("doi")){
				 
			 } else {
				 //is title
				 cur_uppub.trim();
				 cur_uppub_split = cur_uppub.split(" ");
			//	 Singleton.lines
				 
				 String cur_line =null;
				 String[] cur_line_sp = null;
				 int index = 0;
				 for(int a = 0; a < lines.size(); a++){
					 cur_line = Sanitize(lines.get(a));
					 cur_line_sp = cur_line.split(" ");
					 index = getFirstMatchingIndex(cur_uppub_split[0], cur_line_sp);
					 if(index == -1){
						 break;
					 }
				 }
			 }
		 }
		return cur_uppub;
	 }
	 
	 public static String Sanitize(String s){
		 return s.toLowerCase().trim();
	 }
	 
	 public static int getFirstMatchingIndex(String a, String[] b){
		 for(int i = 0; i < b.length ; i++){
			 if(a.equals(b[i])){
				 return i;
			 }
		 }
		 return -1;
	 }
	 
	 public static int getMatchLength(int index, String[] cur_line_sp){
		// for(i = index; i< ;i++){
			 
		// }
		return -1;
	 }
	 
	 public static String getTitles(List<String> lines, String contains, String matches, String path){
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

		        	title = getTitle(vars);
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
	  
	  public static String makeBlock(List<String> lines){
		  	StringBuffer s = new StringBuffer();
		    for (int i = 0 ; i < lines.size() ; i++){
		    	s.append(lines.get(i).trim());
		    	s.append("\n");
		    }
		  return s.toString();
	}

	  public static void printall(String[] a){
		  for(int i = 0; i<a.length ;i++){
			  System.out.print(a[i]);
		  }
	  }
	  
	  private static String getTitle(String[] varlen){
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
	  
	  public static String concatVarLen(String[] varlen){
		  String retVar = "";
		  for(int i = 0; i < varlen.length ; i++){
			  retVar = retVar + varlen[i];
		  }
		  return retVar;
	  }
	  
	  public static String printVarLen(String[] varlen){
		  
		  String retVar = "";
		  for(int i = 0; i < varlen.length ; i++){
			  System.out.print("\t" + i+ ": " + varlen[i]);
		  }
		  return retVar;
	  }  
	 
	  public static String getMissingTitles(List<String> titles, String text){
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

}
