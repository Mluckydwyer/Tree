package main.graphics.tree;

import java.util.Random;

public class TreeGenerator {

	private Tree tree;

	public TreeGenerator(Tree tree) {
		this.tree = tree;
	}

	protected Line computeBranch(Limb base) {
		Random r = new Random();
		Line line = new Line();
		int l; // Temporary variable used to store desired length

		line.setTrunk(false);
		line.setLevel(base.getLevel() + 1);
		line.compLength();

		if (line.getLevel() == 1) {
			line.setX1(base.getLine().getX1());
			line.setY1((int) (base.getLine().getX1() - ((r.nextInt( (int) (base.getLine().getLength() / 2)) + (base.getLine().getLength() / 2)))));
		}
		else {
			if (r.nextBoolean())
				//line.setX1(findXPos(r.nextInt((int) (base.getLine().getLength() / 2)), base.getLine()));
				line.setX1(findXPos(r.nextInt(tree.getLimbsMaxLength(line.getLevel())), base.getLine()));
			else
				//line.setX1(findXNeg(r.nextInt((int) (base.getLine().getLength() / 2)), base.getLine()));
				line.setX1(findXPos(r.nextInt(tree.getLimbsMaxLength(line.getLevel())), base.getLine()));

			line.setY1(base.computeY(line.getX1()));
		}

		System.out.println(line);
		
		line.setSlope(r.nextDouble() - r.nextDouble());
		l = tree.getLimbsMaxLength(base.getLevel() + 1) + r.nextInt(tree.getLimbsMaxLength(base.getLevel() + 1));

		if (r.nextBoolean())
			//line.setX2(findXPos(l, line));
			line.setX2(findXPos(r.nextInt(tree.getLimbsMaxLength(line.getLevel())), base.getLine()));
		else
			//line.setX2(findXNeg(l, line));
			line.setX2(findXPos(r.nextInt(tree.getLimbsMaxLength(line.getLevel())), base.getLine()));

		line.setY2(line.compY(line.getX2()));

		return line;
	}

	private int findXPos(double d, Line line) {
		int x = line.getX1();
		double b = line.getYInt();
		double m = line.getSlope();
		System.out.println("### X1: " + x + " -B:  " + b + " -M: " + m);
		return (int) QuadraticPos((1 + Math.pow(m, 2)), (-2 * x + (2 * m * (b - x))), (Math.pow(x, 2) + (Math.pow(b - x, 2)) ) );
	}

	private int findXNeg(int d, Line line) {
		int x = line.getX1();
		double b = line.getYInt();
		double m = line.getSlope();
		
		return (int) QuadraticNeg((1 + Math.pow(m, 2)), (-2 * x + (2 * m * (b - x))), (Math.pow(x, 2) + (Math.pow(b - x, 2)) ) );
	}
	
	private double QuadraticPos(double a, double b, double c) {
		return (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c) / 2 * a);
	}
	
	private double QuadraticNeg(double a, double b, double c) {
		return (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c) / 2 * a);
	}
}
