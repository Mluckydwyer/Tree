package main.graphics.tree;

import java.awt.Graphics;
import java.util.ArrayList;

public class Tree {
    
    private Thread treeGenThread;
    private int limbsL1;
    private int limbsL2;
    private int limbsL3;
    private int x;
    private int y;
    
    private Limb trunk;
    private ArrayList<Limb> limbs;
    
    public Tree(int limbsL1, int limbsL2, int limbsL3, int x, int y, int trunkHeight) {
        treeGenThread = new TreeGenerator(this);
        limbs = new ArrayList<Limb>();
        trunk = new Limb(x, y, x, y - trunkHeight, true);
        
        this.setLimbsL1(limbsL1);
        this.setLimbsL2(limbsL2);
        this.setLimbsL3(limbsL3);
        this.x = x;
        this.y = y;
    }
    
    public void compute() {
        limbs = new ArrayList<Limb>();
        treeGenThread.start();
    }
    
    public void draw(Graphics g) {
        trunk.drawLimb(g, trunk);
        
        for (int i = limbs.size(); i > 0; i--) {
            limbs.get(i).drawBranch(g);
        }
    }
    
    // ---------- Getters & Setters ----------
    
    public int getLimbsL1() {
        return limbsL1;
    }
    
    public void setLimbsL1(int limbsL1) {
        this.limbsL1 = limbsL1;
    }
    
    public int getLimbsL2() {
        return limbsL2;
    }
    
    public void setLimbsL2(int limbsL2) {
        this.limbsL2 = limbsL2;
    }
    
    public int getLimbsL3() {
        return limbsL3;
    }
    
    public void setLimbsL3(int limbsL3) {
        this.limbsL3 = limbsL3;
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
}
