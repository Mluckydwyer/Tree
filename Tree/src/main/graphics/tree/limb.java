package main.graphics.tree;

import java.awt.Graphics;

public class limb {

	int x1;
	int y1;
	int x2;
	int y2;
	double slope;
	double b;

	public limb(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x1 = y1;
		this.x1 = x2;
		this.x1 = y2;
		slope = ((y2 - y1) / (x2 - x1));
		b = (y1 - (slope * x1));
	}

	// Draw line
	public void drawLine(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}
	
}
