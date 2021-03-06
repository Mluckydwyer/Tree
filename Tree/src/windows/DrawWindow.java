package windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import main.TreeGen;
import main.graphics.Render;

public class DrawWindow extends Thread {

	// ---------- Variables & Objects ----------

	// Objects
	protected JFrame frame;
	private Render render;
	private BufferedImage img;
	public static DrawWindowMouse dwm;
	public static DrawWindowKeyboard dwk;

	// Variables
	private int pixels[];
	public static int width;
	public static int height;
	private int backgroundRGB = 255;

	// FPS Counter
	public long lastFPS;
	private int frames;
	private long startRecodTime;
	private long frameTime;
	private int maxWaitTime;
	private boolean fpsCap = false;

	// ---------- Constructor ----------
	public DrawWindow() {

		// Create Main DrawWindow JFrame
		frame = new JFrame(TreeGen.getTitle());

		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(0, 0);

		// Sets Settings For FullSCreen Mode
		if (TreeGen.isFullScreen()) {
			frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
					Toolkit.getDefaultToolkit().getScreenSize().height);
			frame.setUndecorated(true);
			frame.setIgnoreRepaint(true);
			// vc.setFullScreenWindow();
		}
		// Sets Settings For Standard Mode
		else {
			frame.setSize(
					Toolkit.getDefaultToolkit().getScreenSize().width - 200,
					Toolkit.getDefaultToolkit().getScreenSize().height - 100);
		}
		
		if (TreeGen.getFPSCap() > 0) {
			maxWaitTime = 1000 / TreeGen.getFPSCap();
			fpsCap = true;
		}
	}

	// ---------- Draw Window Thread Run & Main Program Run Loop ----------
	@Override
	public void run() {

		// Set Settings For JFrame
		frame.add(buildMenuBar());
		frame.setVisible(true);

		height = (int) frame.getSize().getHeight();
		width = (int) frame.getSize().getWidth();

		render = new Render(this, width, height);

		// Set Up Buffer Strategy For Direct Pixel Access
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		setPixels(((DataBufferInt) img.getRaster().getDataBuffer()).getData());

		// Mouse Setup
		dwm = new DrawWindowMouse(render);
		frame.addMouseListener(dwm);
		
		// Keyboard Setup
		dwk = new DrawWindowKeyboard(render);
		frame.addKeyListener(dwk);

		// Draw Loop
		do {
			// Starts Recording Render Time
			frameTime = System.currentTimeMillis();
			
			if (System.currentTimeMillis() - startRecodTime >= 1000){
				startRecodTime = System.currentTimeMillis();
				lastFPS = frames;
				frames = 0;
			}
				
			
			// Renders Screen
			render();

			// Calculates FPS (Try  - Catch for catching start divide by zero)
			try {
				if (frameTime < maxWaitTime && fpsCap)
					Thread.sleep(maxWaitTime - frameTime);
				//lastFPS = 1000 / (System.currentTimeMillis() - startRecodTime);				
			} 
			catch (Exception e) {
				if (TreeGen.isDebug())
					e.printStackTrace();
			}
			
			frames++;
		} while (TreeGen.running);

		if (!TreeGen.running)
			System.exit(0);
	}

	// ---------- Main Render Method ----------
	private void render() {

		// Validates Buffer Strategy
		BufferStrategy bs = frame.getBufferStrategy();

		if (bs == null) {
			frame.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Renders Buffer Strategy Things / Direct Pixels
		render.render(g);
		g.drawImage(img, 0, 0, width, height, null);

		// Renders Trees
		render.renderTrees(g);

		// Renders Graphics Object Things
		render.renderOverlay(g, lastFPS);

		g.dispose();
		bs.show();
	}

	// ---------- Tools & Utilities ----------

	// Makes and Returns Menu Bar
	protected JMenuBar buildMenuBar() {
		return new JMenuBar();
	}

	// Resizes Draw Window
	public void resize(int width, int height) {
		frame.setSize(width, height);
	}

	// Takes 1 Dimensional Array Of Pixels And Returns A 2 Dimensional Array Of
	// Them
	protected int[][] to2DArray(int[] pixels) {
		int pixels2D[][] = new int[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels2D[x][y] = pixels[y * width + x];
			}
		}

		return pixels2D;
	}

	// Takes 2 Dimensional Array Of Pixels And Returns A 1 Dimensional Array Of
	// Them
	public int[] to1DArray(int[][] pixels) {
		int pixels1D[] = new int[width * height];

		for (int i = 0; i < width * height; i++) {
			pixels1D[i] = pixels[i % width / width][i / width];
		}

		return pixels1D;
	}

	// ---------- Getters & Setters ----------

	// Returns The Background Color's RGB Value
	public int getBackgroundRGB() {
		return backgroundRGB;
	}

	// Sets The Background Color's RGB Value
	public void setBackgroundRGB(int backgroundRGB) {
		this.backgroundRGB = backgroundRGB;
		frame.setBackground(new Color(backgroundRGB));
	}

	// Returns The 1 Dimensional Array of Pixels Of the Buffer Strategy
	public int[] getPixels() {
		return pixels;
	}

	// Sets The 1 Dimensional Array of Pixels Of the Buffer Strategy
	public void setPixels(int pixels[]) {
		this.pixels = pixels;
	}

	// Returns The Last FPS Recorded
	public long getLastFPS() {
		return lastFPS;
	}

}
