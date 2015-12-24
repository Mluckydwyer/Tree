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
        
        line.setTrunk(false);
        line.setX1(base.getXMid() + r.nextInt(base.getXMid()));
        line.setY1(base.getYMid() + r.nextInt(base.getYMid()));
        line.setLength(tree.getLimbsMaxLength(base.getLevel() + 1) + r.nextInt(tree.getLimbsMaxLength(base.getLevel() + 1)));
        
        int m = 1;
        double x; // line.getX1() + Math.sqrt(Math.pow(line.getLength(), 2) / (1 + Math.pow(m, 2)));
        int b = -m * line.getX1() + line.getY1();
        
        if (m > 0) x = Math.max(line.getX1() + Math.sqrt(Math.pow(line.getLength(), 2) / (1 + Math.pow(m, 2))), line.getX1() - Math.sqrt(Math.pow(line.getLength(), 2) / (1 + Math.pow(m, 2))));
        else x = Math.min(line.getX1() + Math.sqrt(Math.pow(line.getLength(), 2) / (1 + Math.pow(m, 2))), line.getX1() - Math.sqrt(Math.pow(line.getLength(), 2) / (1 + Math.pow(m, 2))));
        
        line.setX2((int) Math.round(x));
        line.setY2(m * line.getX2() + b);
        
        return line;
    }
}
