package windows;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Tree;

public class DrawWindowMouse extends DrawWindow implements FocusListener, MouseListener {
    
    private boolean focus = false;
    private int lastClickX;
    private int lastClickY;
    
    public DrawWindowMouse() {
        super();
        
        frame.addFocusListener(this);
        frame.addMouseListener(this);
    }
    
    private int getX() {
        return frame.getX();
    }
    
    private int getY() {
        return frame.getY();
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        focus = true;
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        focus = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (focus) {
            lastClickX = getX();
            lastClickY = getY();
            if (Tree.isDebug()) System.out.println("Mouse Clicked At X: " + lastClickX + "\tY: " + lastClickY);
            
            render.genTree(lastClickX, lastClickY);
            if (Tree.isDebug()) System.out.println("Generating Tree At X: " + lastClickX + "\tY: " + lastClickY);
        }
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
