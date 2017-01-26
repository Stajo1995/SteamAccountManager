package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

// Parent to all View elements which would otherwise extend a JFrame.
public class View extends JFrame {

	protected static final Dimension SCREEN_SIZE_DIM = new Dimension(1280, 720); // 1280,720  default screen size for all  views.
	protected static Point position; // Saves the last known location of JFrame views on their disposal.

	public View() {
		this.buildSuperView();
	}

	private void buildSuperView() {

		this.setResizable(false);
		this.setSize(SCREEN_SIZE_DIM);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Steam Account Manager");

		// This WindowListener ensures that after a JFrame view is closed that it will open the next frame on the same position.
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent evt) {
				View.position = View.this.getLocation();
			}

			public void windowOpened(WindowEvent evt) {
				if (position != null) {
					View.this.setLocation(position);
				} else {
					View.this.setLocationRelativeTo(null);
				}
			}
		});
	}
}
