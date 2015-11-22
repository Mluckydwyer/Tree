package windows;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class LoadingWindow {
    
    private final int width = 400;
    private final int height = 200;
    
    private JFrame frame;
    private JProgressBar bar;
    
    public LoadingWindow() {
        frame = new JFrame();
        
        frame.setVisible(false);
        frame.setUndecorated(true);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setBackground(Color.GREEN);
        frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - (width / 2), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - (height / 2));
        bar = new JProgressBar(0, 0, 1);
        bar.setStringPainted(true);
        bar.setString("Preparing to Load Window");
        
        frame.add(bar);
        frame.setVisible(true);
    }
    
    public void advance(String step) {
        bar.setValue(bar.getValue() + 1);
        bar.setString(step);
    }
    
    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }
}
