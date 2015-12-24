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
    private int level; // What Level Branch It Is
    private double m; // Slope Of Line
    private double b; // Y-intercept Of Line
    private boolean trunk; // If it is the trunk
    
    private Limb baseLimb; // The limb this is attached too
    private TreeGenerator Tg;
    
    private ArrayList<Limb> subLimbs = new ArrayList<Limb>();
    
    public Limb(Limb bassLimb, TreeGenerator Tg) {
        setBaseLimb(bassLimb);
        setTg(Tg);
    }
    
    public Limb(Line line) {
        this(line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getLevel(), line.isTrunk());
    }
    
    public Limb(Line line, Limb bassLimb) {
        this(line);
        this.baseLimb = bassLimb;
    }
    
    public Limb(int x1, int y1, int x2, int y2, int level, boolean trunk, Limb bassLimb) {
        this(x1, y1, x2, y2, level, trunk);
        this.baseLimb = bassLimb;
    }
    
    public Limb(int x1, int y1, int x2, int y2, int level, boolean trunk) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.setLevel(level);
        this.setTrunk(trunk);
        
        if (!trunk) m = ((y2 - y1) / (x2 - x1));
        else m = (y2 + y1) / 2;
        
        b = (y1 - (m * x1));
        xMid = (x1 + x2) / 2;
        yMid = (y1 + y2) / 2;
    }
    
    @Override
    public void run() {
        getTg().computeBranch(getBaseLimb());
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
    
    // ----------Getters & Setters----------
    
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
    
    public Limb getBaseLimb() {
        return baseLimb;
    }
    
    public void setBaseLimb(Limb baseLimb) {
        this.baseLimb = baseLimb;
    }
    
    public boolean isTrunk() {
        return trunk;
    }
    
    public void setTrunk(boolean trunk) {
        this.trunk = trunk;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public TreeGenerator getTg() {
        return Tg;
    }
    
    public void setTg(TreeGenerator Tg) {
        this.Tg = Tg;
    }
    
}
