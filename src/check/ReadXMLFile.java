package check;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class ReadXMLFile {
	

  public static void main(String[] args) {
    try {
 
	File file = new File("/home/joel/Documents/publications.xml");
 
	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                             .newDocumentBuilder();
 
	Document doc = dBuilder.parse(file);
	
	
	Nodel<String> root = new Nodel<String>("Root element :" + doc.getDocumentElement().getNodeName());

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	//list.insert("Root element :" + doc.getDocumentElement().getNodeName());

	if (doc.hasChildNodes()) {
		printNode(doc.getChildNodes(),root);
	}
 
    } catch (Exception e) {
    	System.out.println(e.getMessage());
    }
	//System.out.println("test"+lines.get(0));

    //writeFile(lines);

  }
 
  private static void printNode(NodeList nodeList, Nodel<String> root) {
    for (int count = 0; count < nodeList.getLength(); count++) {
		Node tempNode = nodeList.item(count);
		// make sure it's element node.
		if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
	 
	//		list.add("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			// get node name and value
		/*	System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			System.out.println("Node Value =" + tempNode.getTextContent());
			*/
			
		//	Nodel<String> bran = nodeTree.branch(new Nodel<String>(tempNode.getNodeName()));
			
		//	System.out.println("Node Name =" + tempNode.getNodeName());

		//	System.out.println("Node Value =" + tempNode.getNodeValue());
		//	System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
			
			if (pathCorrect(tempNode)){
				System.out.println("Node value =" + tempNode.getTextContent() + " [CLOSE]");
			}
			
			
			if (tempNode.hasAttributes()) {
				// get attributes names and values
				NamedNodeMap nodeMap = tempNode.getAttributes();
				tempNode.getNodeName();
				for (int i = 0; i < nodeMap.getLength(); i++) {
	 
					Node node = nodeMap.item(i);
				//	System.out.println("attr name : " + node.getNodeName());
				//	System.out.println("attr value : " + node.getNodeValue());
		//			lines.add("attr name : " + node.getNodeName() );
		//			lines.add("attr value : " + node.getNodeValue());
				}
			}
	 
			if (tempNode.hasChildNodes()) {
				// loop again if has child nodes
				printNode(tempNode.getChildNodes(), root);
			}
	//		lines.add("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
		}
    }
  }
  
  private static boolean pathCorrect(Node test){

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
//	  System.out.println("t "+itemNode.getNodeName());

	  itemNode = nl.item(1); //key node
//	   System.out.println("test "+itemNode.getNodeName());
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
	/*  while(test != null){
		  System.out.println("tree: " +test.getNodeName());
		  test = test.getParentNode();
	  } */
	  return true;
  }
  
  private static void writeFile(List<String> content){
		try {
			 
			//String content = "This is the content to write into file";
 
			File file = new File("/home/joel/Documents/test");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i = 0 ; i<content.size(); i++){
				bw.write(content.get(i));
			}
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
	  
	  /*
	  ReadWriteTextFileJDK7 rwtf = new ReadWriteTextFileJDK7();
	  
	  try {
		rwtf.writeSmallTextFile(lines, "/home/joel/Documents/test");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
  }
  
 
}