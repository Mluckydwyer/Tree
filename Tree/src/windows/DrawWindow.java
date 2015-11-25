package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class DrawWindow implements Runnable {

	private JFrame frame;
	private Dimension size;
	private int width;
	private int height;
	private int backgroundRGB;

	public DrawWindow(boolean fullScreen, String title) {
		size = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) size.getWidth();
		height = (int) size.getHeight();
		frame = new JFrame();

		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setBackground(new Color(backgroundRGB));
		frame.setLocation(0, 0);

		if (fullScreen) {
			frame.setUndecorated(true);
			frame.setIgnoreRepaint(true);
			// vc.setFullScreenWindow();
		}
	}

	@Override
	public void run() {
		synchronized (this) {
			notify();

			frame.add(buildMenuBar());
			notify();

			if (frame.getBufferStrategy() == null)
				frame.createBufferStrategy(3);

			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			BufferStrategy bs = frame.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			int pixels[] = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

			for (int i = 0; i < width * height; i++)
				pixels[i] = backgroundRGB;

			// Draw Loop
			do {
				draw(to2DArray(pixels));
				g.drawImage(img, 0, 0, width, height, null);
				drawOverlay(g);
				g.dispose();
				bs.show();
			}
			while (bs.contentsLost());

		}
	}

	private void draw(int pixels[][]) {
		for (int x = 0; x <= pixels.length; x++) {
			for (int y = 0; y <= pixels[x].length; y++) {

			}
		}
	}

	private void drawOverlay(Graphics g) {
		g.drawString("Test", 100, 100);

	}

	protected JMenuBar buildMenuBar() {
		return null;
	}

	protected void setBackgroundColor(int RGB) {
		frame.setBackground(new Color(RGB));
	}

	public void resize(int width, int height) {
		frame.setSize(width, height);
	}

	private int[][] to2DArray(int[] pixels) {
		int pixels2D[][] = new int[width][height];

		for (int y = 0; y <= height; y++) {
			for (int x = 0; x <= width; x++) {
				pixels2D[x][y] = pixels[y * width + x];
			}
		}

		return pixels2D;
	}
}
