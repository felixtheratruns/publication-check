package check;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
	
/**
 * 
 * @author joel
 *
 */

public class ReadXMLFile {
	File out_file = new File("/media/datal/Documents/test");
	BufferedWriter bw = null;
	ArrayList<String> file_list = null;
	File in_file;
	
	public ArrayList<String> getFileList(){
		return file_list;
	}
	
	public ReadXMLFile(File in_f){
		in_file = in_f;
	}
	public ReadXMLFile(File in_f, File out_f){
		in_file = in_f;
		out_file = out_f;
	}
	
	
	private void openFile(){
		try {
			//String content = "This is the content to write into file";
			// if file doesnt exists, then create it
			if (!out_file.exists()) {
				out_file.createNewFile();
			}
			FileWriter fw = new FileWriter(out_file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	  
	  private void writeFile(String s) throws IOException{
		  bw.write(s);
	  }
	  
	  private void closeFile() throws IOException{
		  bw.close();
	  }
	
  public static void main(String[] args) {
    try {
 
	File file = new File("/media/datal/Documents/publications.xml");
 
	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                             .newDocumentBuilder();
 
	Document doc = dBuilder.parse(file);
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
	ReadXMLFile rxf = new ReadXMLFile(new File("/media/datal/Documents/publications.xml"));
	
	rxf.openFile();
	if (doc.hasChildNodes()) {
		rxf.printNode(doc.getChildNodes());
	}
	rxf.closeFile();
    } catch (Exception e) {
    	System.out.println(e.getMessage());
    }
  }
  
  
  private String removeEndPunctuation(String str){
	  if ((str.endsWith(",")) || str.endsWith(".")) {
		  str = str.substring(0,str.length()-1);  
	  }
	  
	  if ((str.endsWith(",")) || str.endsWith(".")) {
		  str = removeEndPunctuation(str);
	  }
	  
	  return str;
  }
 
  private void printNode(NodeList nodeList) throws DOMException, IOException {
    for (int count = 0; count < nodeList.getLength(); count++) {
		Node tempNode = nodeList.item(count);
		// make sure it's element node.
		if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
			if (pathCorrect(tempNode)){
				file_list.add(tempNode.getTextContent());
			//	System.out.println("Node value =" + removeEndPunctuation(tempNode.getTextContent()) + " [CLOSE]");
			}
			if (tempNode.hasAttributes()) {
				// get attributes names and values
				NamedNodeMap nodeMap = tempNode.getAttributes();
				tempNode.getNodeName();
				for (int i = 0; i < nodeMap.getLength(); i++) {
					Node node = nodeMap.item(i);
				}
			}
			if (tempNode.hasChildNodes()) {
				// loop again if has child nodes
				printNode(tempNode.getChildNodes());
			}
		}
    }
  }
  
  private boolean pathCorrect(Node test) throws IOException{

	  Node itemNode = null;
	  Node orig = test;
	 // Node keyNode = null;
	  
	  if(!test.getNodeName().equals("unicode"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("value"))
		  return false;
	  test = test.getParentNode();
	  itemNode = test;
	  if(!test.getNodeName().equals("item"))
		  return false;
	  
	  NodeList nl = itemNode.getChildNodes();	   
	  itemNode = nl.item(1); //key node
	  if(!itemNode.getNodeName().equals("key"))
		  return false;	  
	  
	  nl = itemNode.getChildNodes();
	  itemNode = nl.item(0); //string node	  
	//  System.out.println("String "+itemNode.getNodeName());

	  if(!itemNode.getNodeName().equals("string"))
		  return false;	 	  
	  if(!itemNode.getTextContent().equals("title"))
		  return false;
	  
	  test = test.getParentNode();
      if(!test.getNodeName().equals("dictionary"))
    	  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("pickle"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("record"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("ZopeData"))
		  return false;
	  test = test.getParentNode();
	  if(!test.getNodeName().equals("#document"))
		  return false;
	  if(orig.getTextContent().equals("Publications"))
		  return false;
	  /*
	  if(orig.getTextContent().contains("diffusion") ){
		  while(orig != null){
			  System.out.println("tree: " +orig.getNodeName());
			  NodeList node_list = orig.getChildNodes();
			//  writeFile(orig.getNodeName() + ":"+ orig.getTextContent() + "\n");
			  System.out.println(orig.getNodeName() + ":"+ orig.getTextContent() + "\n");
			  for(int i = 0; i < node_list.getLength(); i++){
			//	  System.out.println("---" + node_list.item(i).getNodeName() + ":" + node_list.item(i).getTextContent()  +"\n");
			//	  writeFile("---" + node_list.item(i).getNodeName() + ":" + node_list.item(i).getTextContent()  +"\n");
			  }
			//  writeFile("\n");
			  orig = orig.getParentNode();
		  }
	  }*/
	  
	  return true;
  }
  

  
  private void closeFile(BufferedWriter bw) throws IOException{
		bw.close();
		System.out.println("Done");
  }
  
 
}