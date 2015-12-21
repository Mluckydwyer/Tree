package main.graphics.tree;

import java.awt.Graphics;
import java.util.ArrayList;

public class Limb extends Thread {
    
    private int x1; // Starting X Cord
    private int y1; // Starting Y Cord
    private int x2; // Ending X Cord
    private int y2; // Ending Y Cord
    private int xMid; // Mid-Point X Cord
    private int yMid; // Mid-Point Y Cord
    private double m; // Slope Of Line
    private double b; // Y-intercept Of Line
    
	ArrayList<Limb> subLimbs = new ArrayList<Limb>();
    
    public Limb(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x1 = y1;
        this.x1 = x2;
        this.x1 = y2;
        
        m = ((y2 - y1) / (x2 - x1));
        b = (y1 - (m * x1));
        xMid = (x1 + x2) / 2;
        yMid = (y1 + y2) / 2;
    }
    
    @Override
    public void run() {
        genSubLimb();
    }
    
    // Generate a subLimb
    private void genSubLimb() {
    	// Do Something
    }
    
    
    // Draw
    public void drawBranch(Graphics g) {
        drawLimb(g, this);
        if (subLimbs.isEmpty()) return;
        
        for (int i = subLimbs.size(); i > 0; i--) {
        	drawLimb(g, subLimbs.get(i));
        }
    }
    
    public void drawLimb(Graphics g, Limb limb) {
        g.drawLine(limb.getXStart(), limb.getYStart(), limb.getXEnd(), limb.getYEnd());
    }
    
    //----------Getters & Setters----------
    
    public int getYFromX(int x) {
        return (int) (m * x + b);
    }
    
    public int getXFromY(int y) {
        return (int) ((y - b) / m);
    }
    
    public int getXStart() {
        return x1;
    }
    
    public int getYStart() {
        return y1;
    }
    
    public int getXEnd() {
        return x2;
    }
    
    public int getYEnd() {
        return y2;
    }
    
    public int getXMid() {
        return xMid;
    }
    
    public int getYMid() {
        return yMid;
    }
    
}
