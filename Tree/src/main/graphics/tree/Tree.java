package main.graphics.tree;

import java.awt.Graphics;
import java.util.ArrayList;

import main.TreeGen;

public class Tree {

	// private Thread treeGenThread;
	private static int limbsL1;
	private static int limbsL2;
	private static int limbsL3;
	private int x;
	private int y;

	private Limb trunk;
	private ArrayList<Limb> limbs;

	private final int limbsL1MaxLength = 100;
	private final int limbsL2MaxLength = 50;
	private final int limbsL3MaxLength = 25;

	public Tree(int limbsL1, int limbsL2, int limbsL3, int x, int y, int trunkHeight) {
		// treeGenThread = new TreeGenerator(this);
		limbs = new ArrayList<Limb>();
		trunk = new Limb(new Line(x, y, x, y - trunkHeight, true, getLimbsMaxLength(0), 0));

		Tree.limbsL1 = limbsL1;
		Tree.limbsL2 = limbsL2;
		Tree.limbsL3 = limbsL3;
		this.x = x;
		this.y = y;
	}

	public void genSubBranches() {
		limbs = new ArrayList<Limb>();

		for (int i = 0; i < getLimbsNum(1); i++) {
			limbs.add(new Limb(getTrunk(), new TreeGenerator(this)));
			limbs.get(i).start();
		}

		if (TreeGen.isDebug())
			System.out.println(this);
	}

	public void draw(Graphics g) {
		trunk.drawLimb(g, trunk);

		for (int i = limbs.size(); i > 0; i--) {
			limbs.get(i - 1).drawBranch(g);
		}
	}

	public String toString() {
		return "Tree - X1: " + x + "\tY1: " + y + "\tX2: " + trunk.getLine().getX2() + "\tY2: " + trunk.getLine().getY2() + "\t\tLimbs: " + limbs.size();
	}

	// ---------- Getters & Setters ----------

	public static int getLimbsNum(int level) {
		switch (level) {
			case 1 :
				return limbsL1;
			case 2 :
				return limbsL2;
			case 3 :
				return limbsL3;
		}

		return -1;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public Limb getTrunk() {
		return trunk;
	}

	public int getLimbsMaxLength(int level) {

		switch (level) {
			case 0 :
				return 500;

			case 1 :
				return limbsL1MaxLength;

			case 2 :
				return limbsL2MaxLength;

			case 3 :
				return limbsL3MaxLength;
		}

		return 1000;
	}

}
