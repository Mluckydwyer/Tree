package main.graphics.tree;

public class Line {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int length;
    private int level;
    private boolean trunk;
    
    public Line() {
        
    }
    
    public Line(int x1, int y1, int x2, int y2, boolean trunk, int length, int level) {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setLength(length);
        setLevel(level);
        setTrunk(trunk);
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
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
}
