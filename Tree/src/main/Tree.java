package main;

import windows.DrawWindow;
import windows.LoadingWindow;

public class Tree {
    
    private static LoadingWindow load;
    private static DrawWindow draw;
    private static Thread mainWindow;
    public static boolean running;
    public static boolean fullScreen = false;
    
    public static void main(String[] args) {
        running = true;
    	load = new LoadingWindow();
        draw = new DrawWindow(fullScreen);
        mainWindow = new Thread(draw);
        
        mainWindow.start();
        
        synchronized (mainWindow) {
            try {
                load.advance("Creating Window");
                mainWindow.wait();
                load.advance("Creating Menus");
                mainWindow.wait();
                load.advance("Sure");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
}
