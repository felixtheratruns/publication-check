package check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	 
	 public static String getBlockFromStores(ArrayList<Store> stores){
		 StringBuffer sb = new StringBuffer();
		 for(int i = 0; i < stores.size(); i++){
			 sb.append(stores.get(i).getUpload());
		 } 
		 return sb.toString();
	 }
	 
	 public static ArrayList<Store> getTitlesNew(List<String> lines_m){
		 
		 ReadWriteTextFileJDK7 rwtf = new ReadWriteTextFileJDK7();
		 List<String> published = null;
		 try {
			published = rwtf.readSmallTextFile(Singleton.path_pub.getText());
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 String cur_uppub = null;
		 String[] cur_up_s = null;
		 
		 //HashMap<String, String> store = new HashMap<String, String>();
		 
		 ArrayList<Store> storages = new ArrayList<Store>();
		 
		 
		 
		 for(int i = 0; i < published.size() ; i++){
			 cur_uppub = published.get(i);
			 cur_uppub = cur_uppub.toLowerCase();
			 if(cur_uppub.matches("^[ \t]+") || cur_uppub.contains("doi")){
				 
			 } else {
				 //is title
				 cur_uppub = cur_uppub.trim();
				 cur_up_s = cur_uppub.split(" ");
				 String cur_line_match =null;
				 String[] match_sp = null;
				 int cur_index = 0;
				 int cur_match_len = 0;
				 int match_len = 0;
				 int index = 0;
				 String matching = "";
				 String[] up_s = null;
				 String[] match = null;
				 
				 
				 Iterator<String> lines_mat = lines_m.iterator();
				 String lines_matches = null;
				 while(lines_mat.hasNext()){
				// for(int a = 0; a < lines_matches.size(); a++){
					 lines_matches = lines_mat.next();
					 cur_line_match = Sanitize(lines_matches);
					 match_sp = cur_line_match.split(" ");
					 cur_index = getFirstMatchingIndex(cur_up_s[0], match_sp);
					 if(cur_index == -1){
						 continue;
					 }
					 cur_match_len = getMatchLength(cur_index, cur_up_s, match_sp);
	 
					 if(cur_match_len > match_len){
						 index = cur_index;
						 matching = cur_line_match;
						 match_len = cur_match_len;
						 up_s = cur_up_s;
						 match = match_sp;
					 }
				 }
				 if (up_s != null && match_len == up_s.length){
					 storages.add(new Store(up_s, match,cur_uppub,matching,match_len));
					 lines_mat.remove();
				 }
			 }
		 }
		return storages;
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
	 
	 public static int getMatchLength(int index, String[] match, String[] cur_line_sp){
		int a = 0;
		int b = index; 

		
		while(a< match.length && b < cur_line_sp.length){
			if(match[a].equals(cur_line_sp[b])){
				
			} else {
				return a+1;
			}
			a++;
			b++;
		}
		return a+1;
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
