package main;

import windows.DrawWindow;
import windows.LoadingWindow;

public class Tree {
    // ---------- Variables & Objects & Setting ----------
    
    // Objects
    private static LoadingWindow load;
    private static DrawWindow draw;
    private static Thread mainWindow;
    
    // Variables
    public static boolean running;
    
    // Settings / General Info
    private static final String title = "Tree";
    private static final String version = "v0.1 Pre-Alpha";
    private static int FPSCap = -1;
    private static boolean fullScreen = false;
    private static boolean loadingWindow = false;
    
    // Testing Info
    private static boolean debug = true;
    private static boolean drawInfo = true;
    
    // Random / Fun / Secret Settings
    private static boolean fun = false;
    
    // ---------- Main Method For Whole Program ----------
    public static void main(String[] args) {
        running = true;
        draw = new DrawWindow();
        mainWindow = new Thread(draw, "Draw Window");
        
        // Starts Loading Window
        if (loadingWindow) {
            
            // Real
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
            // Fun
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
    
    // ---------- Getters For Program Main Settings ----------
    
    // Returns Program Title
    public static String getTitle() {
        return title;
    }
    
    // Returns Version Number
    public static String getVersion() {
        return version;
    }
    
    // Returns The Set FPS Limit
    public static int getFPSCap() {
        return FPSCap;
    }
    
    // Returns The State Of Fun Setting
    public static boolean isFun() {
        return fun;
    }
    
    // Returns The State Of Whether FullScreen Is Enabled
    public static boolean isFullScreen() {
        return fullScreen;
    }
    
    // Returns The state Of Whether Or Not To Draw The Debug Info Overlay Directly Over The Window
    public static boolean isDrawInfo() {
        return drawInfo;
    }
    
    // Returns The State Of Whether Debug Mode Is Enabled
    public static boolean isDebug() {
        return debug;
    }
    
}
