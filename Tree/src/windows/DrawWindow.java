package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.Tree;

public class DrawWindow implements Runnable {

	private JFrame frame;
	private Dimension size;
	private int backgroundRGB;
	public int pixels[][];

	public DrawWindow(boolean fullScreen) {
		frame = new JFrame();
		size = Toolkit.getDefaultToolkit().getScreenSize();
		pixels = new int[(int) size.getWidth()][(int) size.getHeight()];

		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setBackground(new Color(backgroundRGB));
		frame.setLocation(0, 0);
		
		if (fullScreen) {
			frame.setUndecorated(true);
			frame.setIgnoreRepaint(true);
			//vc.setFullScreenWindow();
		}
	}

	@Override
	public void run() {
		notify();

		buildMenuBar();
		notify();

		synchronized (this) {

			while (Tree.running) {
				frame.dispose();
				draw(frame.getGraphics());
			}

		}
	}

	private void draw(Graphics g) {
		for (int x = 0; x <= pixels.length; x++) {
			for (int y = 0; y <= pixels[x].length; y++) {
				
			}
		}
	}

	protected void buildMenuBar() {

	}

	protected void setBackgroundColor(int RGB) {
		frame.setBackground(new Color(RGB));
	}

	public void resize(Dimension size) {
		frame.setSize(size);
	}

	public void resize(int width, int height) {
		frame.setSize(width, height);
	}
}
