package main.graphics.tree;

import java.awt.Graphics;
import java.util.ArrayList;

import main.TreeGen;

public class Limb extends Thread {

	private Line line; // The Basic Line Of The Limb
	private Limb baseLimb; // The limb this is attached too
	private TreeGenerator Tg; // The Tree Generator Used To Generate The Limbs
	private ArrayList<Limb> subLimbs = new ArrayList<Limb>(); // List Of SubLimbs If Any

	public Limb(Limb bassLimb, TreeGenerator Tg) {
		this(new Line());
		
		setBaseLimb(bassLimb);
		setTg(Tg);
		setLevel(1);
	}

	public Limb(Line line) {
		setLine(line);
	}

	public Limb(Line line, Limb bassLimb) {
		this(line);
		this.baseLimb = bassLimb;
		setTg(bassLimb.getTg());
	}

	@Override
	public void run() {
		computeBranch();
		
		if (TreeGen.isDebug())
			System.out.println(this);
		
		//if (line.getLevel() == 1)
		//	terminate();
	}
	
	private void terminate() {
		try {
			System.out.println("^^^^%%%%%%%%^^^");
			this.join();
			System.out.println("^^^^^^^^^^^^^");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void computeBranch() {
		setLine(getTg().computeBranch(getBaseLimb()));

		if (line.getLevel() < 4)
			addSubBranches();

		if (subLimbs.isEmpty())
			return;

		for (int i = subLimbs.size() - 1; i >= 0; i--) {
			subLimbs.get(i).setLine(getTg().computeBranch(subLimbs.get(i).getBaseLimb()));
		}
	}

	private void addSubBranches() {
		subLimbs = new ArrayList<Limb>();

		for (int i = 0; i < Tree.getLimbsNum(1); i++) {
			subLimbs.add(new Limb(getTg().computeBranch(this), this));
			 // subLimbs.get(i).start();
		}
	}

	// Draw
	public void drawBranch(Graphics g) {
		drawLimb(g, this);
		if (subLimbs.isEmpty())
			return;

		for (int i = subLimbs.size() - 1; i >= 0; i--) {
			drawLimb(g, subLimbs.get(i));
		}
	}

	public void drawLimb(Graphics g, Limb limb) {
		g.drawLine(limb.getLine().getX1(), limb.getLine().getY1(), limb.getLine().getX2(), limb.getLine().getY2());
	}

	public int computeY(int x){
		return (int) (line.getSlope() * x + line.getYInt());	
	}
	
	public int computeX(int y) {
		return (int) ((y - line.getYInt()) / line.getSlope());
	}
	
	public String toString() {
		return "Limb - X1: " + line.getX1() + "\tY1: " + line.getY1() + "\tX2: " + line.getX2() + "\tY2: " + line.getY2() + "\tLength: " + line.getLength() + "\tLevel: " + line.getLevel();
	}

	// ----------Getters & Setters----------
	
	public Line getLine() {
		return line;
	}
	
	public void setLine(Line line) {		
		this.line = line;
		
		if (!line.isTrunk() && !line.equals(new Line()))
			//line.compSlope();
		/*else
			line.compSlope(); = (y2 + y1) / 2;*/
	
		line.compYInt();
		line.compLength();
		line.compXMid();
		line.compYMid();
	}

	public int getYFromX(int x) {
		return (int) (line.getSlope() * x + line.getYInt());
	}

	public int getXFromY(int y) {
		return (int) ((y - line.getYInt()) / line.getSlope());
	}

	public Limb getBaseLimb() {
		return baseLimb;
	}

	public void setBaseLimb(Limb baseLimb) {
		this.baseLimb = baseLimb;
	}

	public boolean isTrunk() {
		return line.isTrunk();
	}

	public void setTrunk(boolean trunk) {
		this.line.setTrunk(trunk);
	}

	public int getLevel() {
		return line.getLevel();
	}

	public void setLevel(int level) {
		this.line.setLevel(level);
	}

	public TreeGenerator getTg() {
		return Tg;
	}

	public void setTg(TreeGenerator Tg) {
		this.Tg = Tg;
	}

}
