package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DrawWindow implements Runnable {
    
    private JFrame frame;
    private Dimension size;
    
    public DrawWindow() {
        frame = new JFrame();
        size = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setResizable(false);
        frame.setBackground(Color.GREEN);
        frame.setLocation(0, 0);
    }
    
    @Override
    public void run() {
        
    }
    
    protected void buildMenuBar() {
        
    }
    
    public void resize(Dimension size) {
        frame.setSize(size);
    }
    
    public void resize(int width, int height) {
        frame.setSize(width, height);
    }
}
