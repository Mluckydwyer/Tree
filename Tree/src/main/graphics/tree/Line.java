package main.graphics.tree;

public class Line {

	private int x1; // Starting X Cord
	private int y1; // Starting Y Cord
	private int x2; // Ending X Cord
	private int y2; // Ending Y Cord
	private int xMid; // Mid-Point X Cord
	private int yMid; // Mid-Point Y Cord
	private int level; // What Level Branch It Is
	private double slope; // Slope Of Line
	private double yInt; // Y-intercept Of Line
	private double length; // Length Of The Line
	private boolean trunk; // If it is the trunk

	public Line() {

	}

	public Line(int x1, int y1, int x2, int y2, boolean trunk, int length, int level) {
		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);
		setLevel(level);
		setTrunk(trunk);
		compSlope();
		compLength();
	}

	public int compY(int x) {
		return (int) ((slope * x) + yInt);
	}

	public void compSlope() {
		if(isTrunk()) {
			length = (y2 - y1) / 2;
			return;
		}
		
		slope = ((y2 - y1) / (x2 - x1));
	}

	public void compYInt() {
		yInt = y1 - (slope * x1);
	}

	public void compXMid() {
		xMid = (x1 + x2) / 2;
	}

	public void compYMid() {
		yMid = (y1 + y2) / 2;
	}
	public void compLength() {
		length = Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public boolean isTrunk() {
		return trunk;
	}

	public void setTrunk(boolean trunk) {
		this.trunk = trunk;
	}

	public double getLength() {
		return length;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getSlope() {
		return slope;
	}

	public void setSlope(double m) {
		this.slope = m;
	}
	
	public double getYInt() {
		return yInt;
	}

	public int getXMid() {
		return xMid;
	}

	public int getYMid() {
		return yMid;
	}
}
