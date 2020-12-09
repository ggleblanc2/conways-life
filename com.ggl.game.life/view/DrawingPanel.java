package com.ggl.game.life.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;

import com.ggl.game.life.model.GameOfLifeModel;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int cellWidth;
	private int margin;
	private int width;
	
	private GameOfLifeModel model;
	
	public DrawingPanel(GameOfLifeModel model) {
		this.model = model;
		this.cellWidth = 8;
		this.margin = 10;
		this.width = cellWidth * model.getNumberOfCells();
		
		int panelWidth = width + margin + margin;
		this.setPreferredSize(new Dimension(panelWidth, 
				panelWidth));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		drawGrid(g);
		drawLiveCells(g);
	}

	private void drawGrid(Graphics g) {
		int x1 = margin;
		int x2 = width + margin;
		g.setColor(Color.BLACK);
		for (int i = 0; i <= model.getNumberOfCells(); i++) {
			int y = i * cellWidth + margin;
			g.drawLine(x1, y, x2, y);
			g.drawLine(y, x1, y, x2);
		}
	}
	
	private void drawLiveCells(Graphics g) {
		Set<Point> cells = model.getLiveCells();
		Iterator<Point> iter = cells.iterator();
		g.setColor(Color.RED);
		while (iter.hasNext()) {
			Point p = iter.next();
			int x = p.x * cellWidth + margin + 1;
			int y = p.y * cellWidth + margin + 1;
			g.fillRect(x, y, cellWidth - 1, cellWidth - 1);
		}
	}
	
	public BufferedImage getBufferedImage() {
		int height = 36;
		BufferedImage image = new BufferedImage(
				getWidth(), getHeight() + height,
				BufferedImage.TYPE_INT_RGB);
		BufferedImage top = getBufferedGenerationImage(height);
		BufferedImage bottom = getBufferedGridImage();
		
		Graphics g = image.getGraphics();
		g.drawImage(top, 0, 0, this);
		g.drawImage(bottom, 0, height, this);
		g.dispose();
		
		return image;
	}
	
	private BufferedImage getBufferedGenerationImage(int height) {
		BufferedImage image = new BufferedImage(
				getWidth(), height,
				BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		NumberFormat nf = NumberFormat.getInstance();
		String generation = nf.format(model.getGeneration());
		String text = "Generation " + generation;
		
		g.setColor(Color.BLACK);
		g.setFont(getFont());
		g.drawString(text, margin, height * 2 / 3);
		g.dispose();
		
		return image;
	}
	
	private BufferedImage getBufferedGridImage() {
		BufferedImage image = new BufferedImage(
				getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		paintAll(g);
		g.dispose();
		
		return image;
	}
}
