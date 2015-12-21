package main.graphics.tree;

import java.awt.Graphics;
import java.util.ArrayList;

public class Tree {

	private Thread treeGenThread;
	
	ArrayList<Limb> limbs = new ArrayList<Limb>();	
	
	
	
	
	public Tree() {
		treeGenThread = new TreeGenerator(this);
	}

	public void compute() {
		treeGenThread.start();
	}

	public void draw(Graphics g) {
		for (int i = limbs.size(); i > 0; i--) {
			limbs.get(i).drawBranch(g);
		}
	}
}
