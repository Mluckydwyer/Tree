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
				line.setX1(findXPos(r.nextInt((int) (base.getLine().getLength() / 2)), base.getLine()));
			else
				line.setX1(findXNeg(r.nextInt((int) (base.getLine().getLength() / 2)), base.getLine()));

			line.setY1(base.computeY(line.getX1()));
		}

		line.setSlope(r.nextDouble() - r.nextDouble());
		l = tree.getLimbsMaxLength(base.getLevel() + 1) + r.nextInt(tree.getLimbsMaxLength(base.getLevel() + 1));

		if (r.nextBoolean())
			line.setX2(findXPos(l, line));
		else
			line.setX2(findXNeg(l, line));

		line.setY2(line.compY(line.getX2()));

		return line;
	}

	private int findXPos(double d, Line line) {
		return (int) (line.getX1() + (d + (line.getLength() / 2)) / Math.sqrt(1 + Math.pow(line.getSlope(), 2)));
	}

	private int findXNeg(int d, Line line) {
		return (int) (line.getX1() - (d + (line.getLength() / 2)) / Math.sqrt(1 + Math.pow(line.getSlope(), 2)));
	}
}
