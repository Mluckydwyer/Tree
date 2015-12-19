package main.graphics;

import java.awt.Color;
import java.awt.Graphics;

import main.Tree;
import windows.DrawWindow;

public class Render extends DrawWindow {
    
    public int pixels[][];
    private int width;
    private int height;
    
    public Render(int width, int height) {
        super();
        
        this.width = width;
        this.height = height;
        pixels = new int[this.width][this.height];
    }
    
    public void render(Graphics g) {
        setAll(getBackgroundRGB());
        renderRawPixles();
        drawFrame();
    }
    
    private void drawFrame() {
        setPixels(to1DArray(pixels));
    }
    
    private void renderRawPixles() {
        
    }
    
    private void setAll(int RGB) {
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[x].length; y++) {
                pixels[x][y] = RGB;
            }
        }
    }
    
    public void renderOverlay(Graphics g, long FPS) {
        // Info
        if (Tree.isDrawInfo()) {
            int tlc = 15; // Top Left Corner
            g.setColor(Color.MAGENTA);
            g.drawString("Version:  " + Tree.getVersion(), tlc, (int) (tlc * 3.5));
            g.drawString("FPS:  " + FPS, tlc, (int) (tlc * 4.5));
        }
    }
    
    public void genTree(int x, int y) {
        
    }
    
}
