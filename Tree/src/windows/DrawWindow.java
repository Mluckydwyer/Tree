package windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import main.Tree;
import main.graphics.Render;

public class DrawWindow implements Runnable {

	private JFrame frame;
	private Render render;
	private BufferedImage img;

	private int pixels[];
	public static int width;
	public static int height;
	private int backgroundRGB = 255;

	// FPS Counter
	private int frames;
	private int lastFPS;
	private long startRecodTime;

	public DrawWindow() {
		frame = new JFrame(Tree.getTitle());

		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(0, 0);

		if (Tree.isFullScreen()) {
			frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
					Toolkit.getDefaultToolkit().getScreenSize().height);
			frame.setUndecorated(true);
			frame.setIgnoreRepaint(true);
			// vc.setFullScreenWindow();
		}
		else {
	        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200, Toolkit.getDefaultToolkit().getScreenSize().height - 100);
		}
	}

	@Override
	public void run() {

		frame.add(buildMenuBar());

		frame.setVisible(true);

		height = (int) frame.getSize().getHeight();
		width = (int) frame.getSize().getWidth();
		render = new Render(width, height);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		setPixels(((DataBufferInt) img.getRaster().getDataBuffer()).getData());

		// FPS Counter
		startRecodTime = System.currentTimeMillis();
		// lastFPS = 0;
		// frames = 0;

		// Draw Loop
		do {

			if (System.currentTimeMillis() < startRecodTime + 1000) {
				frames++;
			} else {
				startRecodTime = System.currentTimeMillis();
				lastFPS = frames;
				frames = 0;
				System.out.println(lastFPS);
			}

			render();
		} while (true);

	}

	private void render() {
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs == null) {
			frame.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		render.render(g);
		g.drawImage(img, 0, 0, width, height, null);
		render.renderOverlay(g);

		g.dispose();
		bs.show();
	}

	protected JMenuBar buildMenuBar() {
		return new JMenuBar();
	}

	public void resize(int width, int height) {
		frame.setSize(width, height);
	}

	protected int[][] to2DArray(int[] pixels) {
		int pixels2D[][] = new int[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels2D[x][y] = pixels[y * width + x];
			}
		}

		return pixels2D;
	}

	public int[] to1DArray(int[][] pixels) {
		int pixels1D[] = new int[width * height];

		for (int i = 0; i < width * height; i++) {
			pixels1D[i] = pixels[i % width / width][i / width];
		}

		return pixels1D;
	}

	public int getBackgroundRGB() {
		return backgroundRGB;
	}

	public void setBackgroundRGB(int backgroundRGB) {
		this.backgroundRGB = backgroundRGB;
		frame.setBackground(new Color(backgroundRGB));
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int pixels[]) {
		this.pixels = pixels;
	}

	public int getLastFPS() {
		return lastFPS;
	}
	
}
