package check;

import java.util.ArrayList;

public class Stores {
	ArrayList<Store> found;
	ArrayList<Store> unfound;
	
	public Stores(ArrayList<Store> f, ArrayList<Store> u){
		found = f;
		unfound = u;
		
	}
	
	public ArrayList<Store> getFound(){
		return found;
	}
	
	public ArrayList<Store> getUnfound(){
		return unfound;
	}
	
	
}
