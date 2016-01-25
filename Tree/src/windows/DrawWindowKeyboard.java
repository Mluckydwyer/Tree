package windows;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.TreeGen;
import main.graphics.Render;

public class DrawWindowKeyboard extends DrawWindow implements KeyListener {

	Render r;

	public DrawWindowKeyboard(Render r) {
		super();

		this.r = r;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		if (e.getKeyChar() == 'c') {
			if (TreeGen.isDebug())
				System.out.println("C Key Pressed, Clearing Screen");
			
			r.clearTrees();
		}
		else if (e.getKeyChar() == 'q') {
			if (TreeGen.isDebug())
				System.out.println("Q Key Pressed, Joining Threads");
			
			r.terminate();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Not Used
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not Used
	}

}
