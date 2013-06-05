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