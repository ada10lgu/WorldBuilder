package gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import world.Zone;

@SuppressWarnings("serial")
public class WorldGUI extends JFrame {

	/*
	 * Forest Plains Mountain Desert Swamp
	 */

	private ArrayList<Zone> points;

	public WorldGUI(ArrayList<Zone> points) {
		this.points = points;

		setSize(800, 600);
		add(new Canvas());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class Canvas extends JPanel {
		@Override
		public void paint(Graphics g) {
			long xMin = Long.MAX_VALUE;
			long xMax = Long.MIN_VALUE;
			long yMin = Long.MAX_VALUE;
			long yMax = Long.MIN_VALUE;
			double altMin = Double.MAX_VALUE;
			double altMax = Double.MIN_VALUE;
			for (Zone p : points) {
				xMin = Math.min(xMin, p.getX());
				xMax = Math.max(xMax, p.getX());
				yMin = Math.min(yMin, p.getY());
				yMax = Math.max(yMax, p.getY());
				altMin = Math.min(altMin, p.getAltitude());
				altMax = Math.max(altMax, p.getAltitude());
			}
			double deltaAlt = altMax - altMin;

			long xSize = getWidth() / (xMax - xMin + 1);
			long ySize = getHeight() / (yMax - yMin + 1);

			int size = (int) Math.min(xSize, ySize);

			g.setColor(Color.BLUE);
			g.fillRect(0, 0, getWidth(), getHeight());

			FontMetrics fm = g.getFontMetrics();

			int yOffset = fm.getHeight() - fm.getHeight() / 2 + size / 2;

			// g.setColor(Color.BLACK);
			for (Zone p : points) {
				int x = (int) (p.getX() - xMin);
				int y = (int) (p.getY() - yMin);
				double proc = (p.getAltitude() - altMin) / deltaAlt;

				int col = 200 - (int) (proc * 150);

				g.setColor(new Color(col, col, col));
				g.fillRect(x * size, y * size, size, size);
			}
//			g.setColor(Color.PINK);
//			for (Zone p : points) {
//				int x = (int) (p.getX() - xMin);
//				int y = (int) (p.getY() - yMin);
//				String text = "" + (int) (p.getAltitude() * 100);
//				int xOffset = size / 2 - fm.stringWidth(text) / 2;
//				g.drawString(text, x * size + xOffset, y * size + yOffset);
//			}

		}
	}
}
