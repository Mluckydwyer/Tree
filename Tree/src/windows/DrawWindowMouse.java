package windows;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.TreeGen;
import main.graphics.Render;

public class DrawWindowMouse extends DrawWindow implements MouseListener {
    
    // private boolean focus = false;
    public int lastClickX = 0;
    public int lastClickY = 0;
    private Render r;
    
    public DrawWindowMouse(Render r) {
        super();
        
        this.r = r;
        if (TreeGen.isFun()) frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("res/cursors/Leaf.png"), new Point(frame.getX(), frame.getY()), "img"));
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // if (frame.isFocusOwner()) {
        lastClickX = e.getX();
        lastClickY = e.getY();
        if (TreeGen.isDebug()) System.out.println("\nMouse Clicked At  X: " + lastClickX + "  Y: " + lastClickY);
        
        r.genNewTree(lastClickX, lastClickY);
        if (TreeGen.isDebug()) System.out.println("Generating Tree At  X: " + lastClickX + "  Y: " + lastClickY);
        // }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        // Do Noting on Press For Now
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        // Do Noting on Press For Now
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        // ???????????????????????????????????
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        // ???????????????????????????????????
    }
}
