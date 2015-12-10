package main.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Tree;
import windows.DrawWindow;

public class Render extends DrawWindow {
    
    public int pixels[][];
    private int width;
    private int height;
    
    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[this.width][this.height];
    }
    
    public void renderAll(Graphics g) {
        //setAll(getBackgroundRGB());
        // renderRawPixles();
        // drawFrame();
        renderOverlay(g);
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
    
    private void renderOverlay(Graphics g) {
        // Info
        if (Tree.drawInfo) {
            int tl = 15; // tl = Top Left
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("ComicSans", Font.BOLD, tl));
            g.drawString("Version:  " + Tree.version, tl, (int) (tl * 3.5));
            g.drawString("FPS:  " + lastFPS, tl, (int) (tl * 4.5));
        }
    }
    
}
