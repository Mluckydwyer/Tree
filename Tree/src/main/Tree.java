package main;

import windows.DrawWindow;
import windows.LoadingWindow;

public class Tree {

	private static LoadingWindow load;
	private static DrawWindow draw;
	private static Thread mainWindow;
	private static boolean fullScreen = false;
	private static final String title = "Tree Generator";
	public static boolean running;

	public static void main(String[] args) {
		running = true;
		load = new LoadingWindow();
		draw = new DrawWindow(fullScreen, title);
		mainWindow = new Thread(draw, "Draw Window");

		mainWindow.start();

		synchronized (mainWindow) {
			try {
				load.advance("Creating Window");
				mainWindow.wait();
				load.advance("Creating Menus");
				mainWindow.wait();
				load.advance("Sure");
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
