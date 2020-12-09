package com.ggl.game.life.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.ggl.game.life.controller.Generation;
import com.ggl.game.life.model.GameOfLifeModel;

public class GameOfLifeFrame {
	
	private DrawingPanel drawingPanel;
	
	private ControlPanel controlPanel;
	
	private JFrame frame;
	
	private GameOfLifeModel model;
	
	private Generation runnable;
	
	public GameOfLifeFrame(GameOfLifeModel model) {
		this.model = model;
		createPartControl();
	}

	private void createPartControl() {
		frame = new JFrame("Conway's Game of Life");
		frame.setIconImage(getImage("life.png"));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent event) {
		        exitProcedure();
		    }
		});
		
		controlPanel = new ControlPanel(this, model);
		frame.add(controlPanel.getPanel(), 
				BorderLayout.LINE_END);
		
		drawingPanel = new DrawingPanel(model);
		frame.add(drawingPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		runnable = new Generation(this, model);
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public DrawingPanel getDrawingPanel() {
		return drawingPanel;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	private Image getImage(String filename) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(
					"/" + filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void exitProcedure() {
		runnable.setRunning(false);
		frame.dispose();
	    System.exit(0);
	}
	
}
