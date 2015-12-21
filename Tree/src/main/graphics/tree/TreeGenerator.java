package main.graphics.tree;

import java.util.ArrayList;

public class TreeGenerator extends Thread {
    
	private Tree tree;
	
	ArrayList<Thread> limbs = new ArrayList<Thread>();
	
	public TreeGenerator(Tree tree) {
		this.tree = tree;
	}
	
    @Override
    public void run() {
        
        
    }
    
    public void mainLimb() {
        
    }
    
}
