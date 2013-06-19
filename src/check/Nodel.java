package check;

import java.util.List;


class Nodel<T> {
    private T data;
    private Nodel<T> parent;
    private List<Nodel<T>> children;
    
    Nodel(T t){
    	data = t;
    }
    
    public Nodel<T> branch(Nodel<T> n){
    	n.setParent(this);
    	children.add(n);
    	return n;
    }
    
    private void setParent(Nodel<T> n){
    	this.parent = n;
    }
    
}