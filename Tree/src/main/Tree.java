package main;

import windows.DrawWindow;
import windows.LoadingWindow;

public class Tree {
    
    private static LoadingWindow load;
    private static DrawWindow draw;
    private static Thread mainWindow;
    
    public static void main(String[] args) {
        load = new LoadingWindow();
        draw = new DrawWindow();
        mainWindow = new Thread(draw);
        
        mainWindow.start();
        
        synchronized (mainWindow) {
            try {
                load.advance("Creating Window");
                mainWindow.wait();
                load.advance("Creating Menues");
                mainWindow.wait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
}
