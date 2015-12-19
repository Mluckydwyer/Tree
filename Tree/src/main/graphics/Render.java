package main.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.TreeGen;
import main.graphics.tree.Tree;
import main.graphics.tree.TreeGenerator;
import windows.DrawWindow;

public class Render {
    
    public int pixels[][];
    private int width;
    private int height;
    private DrawWindow dw;
    
    // Tree Stuff
    private ArrayList<Tree> trees = new ArrayList<Tree>();
    private Thread treeGenThread;
    
    public Render(DrawWindow dw, int width, int height) {
        this.dw = dw;
        this.width = width;
        this.height = height;
        pixels = new int[this.width][this.height];
        treeGenThread = new TreeGenerator();
        treeGenThread.start();
    }
    
    public void render(Graphics g) {
        setAll(dw.getBackgroundRGB());
        renderRawPixles();
        drawFrame();
    }
    
    private void drawFrame() {
        dw.setPixels(dw.to1DArray(pixels));
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
        if (TreeGen.isDrawInfo()) {
            int tlc = 15; // Top Left Corner
            g.setColor(Color.MAGENTA);
            g.drawString("Version:  " + TreeGen.getVersion(), tlc, (int) (tlc * 3.5));
            g.drawString("FPS:  " + FPS, tlc, (int) (tlc * 4.5));
            g.drawString("Mouse X: " + dw.dwm.lastClickX, tlc, (int) (tlc * 5.5));
            g.drawString("Mouse Y: " + dw.dwm.lastClickY, tlc, (int) (tlc * 6.5));
        }
    }
    
    public void genTree(int x, int y) {
        trees.add(new Tree());
        treeGenThread.newTree(trees.get(trees.size() - 1));
    }
    
}
