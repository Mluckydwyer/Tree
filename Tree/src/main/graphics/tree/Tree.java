package main.graphics.tree;

import java.awt.Graphics;
import java.util.ArrayList;

public class Tree {
    
    // private Thread treeGenThread;
    private int limbsL1;
    private int limbsL2;
    private int limbsL3;
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
        trunk = new Limb(x, y, x, y - trunkHeight, 0, true);
        
        this.setLimbsL1(limbsL1);
        this.setLimbsL2(limbsL2);
        this.setLimbsL3(limbsL3);
        this.x = x;
        this.y = y;
    }
    
    public void compute() {
        limbs = new ArrayList<Limb>();
        
        for (int i = 0; i < getLimbsL1(); i++) {
            limbs.add(new Limb(getTrunk(), new TreeGenerator(this)));
            limbs.get(i).start();
        }
    }
    
    public void draw(Graphics g) {
        trunk.drawLimb(g, trunk);
        
        for (int i = limbs.size(); i > 0; i--) {
            limbs.get(i - 1).drawBranch(g);
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
    
    public int getLimbsMaxLength(int level) {
        
        switch (level) {
            case 0:
                return 500;
                
            case 1:
                return limbsL1MaxLength;
                
            case 2:
                return limbsL2MaxLength;
                
            case 3:
                return limbsL3MaxLength;
        }
        
        return 1000;
    }
    
}
