package check;

public class Store{
	 private String[] upload_s;
	 private String[] match_s;
	 private String upload;
	 private String match;
	 private int len_of_match;
	 public Store(String[] a, String[] b, String c, String d, int len){
		 upload_s = a;
		 match_s = b;
		 upload = c;
		 match = d;
		 len_of_match = len;
		 
	 }
	 
	 public Store(String up, String[] up_s, int len){
		 upload = up;
		 upload_s = up_s;
		 len_of_match = len;
	 }
	 
	 public String getMatch(){
		 return match;
	 }
	 
	 public String getUpload(){
		 return upload;
	 }
}
