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

import main.Tree;

public class DrawWindow extends JFrame implements Runnable {

	private Dimension size;
	private int backgroundRGB;
	private int pixels[];
	private BufferedImage img;
	private BufferStrategy bs;

	public DrawWindow(boolean fullScreen) {
		size = Toolkit.getDefaultToolkit().getScreenSize();

		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(size);
		setResizable(false);
		setBackground(new Color(backgroundRGB));
		setLocation(0, 0);

		if (fullScreen) {
			setUndecorated(true);
			setIgnoreRepaint(true);
			// vc.setFullScreenWindow();
		}
	}

	@Override
	public void run() {
		notify();

		add(buildMenuBar());
		notify();

		synchronized (this) {
			img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
			BufferStrategy bs = getBufferStrategy();

			if (bs == null)
				createBufferStrategy(3);

			for (int i = 0; i < WIDTH * HEIGHT; i++)
				pixels[i] = 0;

			Graphics g = bs.getDrawGraphics();

			g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
			g.dispose();
			bs.show();

			do {
				try {
					g = bs.getDrawGraphics();
					draw();
				}
				finally {
					g.dispose();
				}
				bs.show();
			}
			while (bs.contentsLost());
		}
	}

	private void draw(Graphics g, int pixels[][]) {
		for (int x = 0; x <= pixels.length; x++) {
			for (int y = 0; y <= pixels[x].length; y++) {

			}
		}
	}

	protected JMenuBar buildMenuBar() {
		return null;
	}

	protected void setBackgroundColor(int RGB) {
		setBackground(new Color(RGB));
	}

	public void resize(Dimension size) {
		setSize(size);
	}

	public void resize(int width, int height) {
		setSize(width, height);
	}
}
