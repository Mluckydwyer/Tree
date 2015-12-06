package main;

import windows.DrawWindow;
import windows.LoadingWindow;

public class Tree {
    
    private static LoadingWindow load;
    private static DrawWindow draw;
    private static Thread mainWindow;
    public static boolean running;
    
    // Settings / Info
    public static final String title = "Tree";
    public static final String version = "v0.1 Pre-Alpha";
    public static boolean fullScreen = false;
    public static boolean drawInfo = true;
    private static boolean loadingWindow = false;
    public static boolean fun = false;
    
    public static void main(String[] args) {
        running = true;
        draw = new DrawWindow();
        mainWindow = new Thread(draw, "Draw Window");
        
        if (loadingWindow) {
            
            if (!fun) {
                try {
                    load = new LoadingWindow();
                    
                    load.advance("Creating Window");
                    load.advance("Creating Menus");
                    load.advance("Displaying Window");
                    
                    load.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            else {
                
                try {
                    load = new LoadingWindow();
                    
                    load.advance("Turning On Lamps");
                    load.advance("Closing Tabs");
                    load.advance("Downloading Labs");
                    load.advance("Putting in Grades at 1:30 AM");
                    load.advance("Buying Google Cardboard For $19.99");
                    load.advance("Finally Starting");
                    
                    load.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            
        }
        
        mainWindow.start();
    }
    
}
