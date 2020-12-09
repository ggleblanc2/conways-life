package com.ggl.game.life.controller;

import javax.swing.SwingUtilities;

import com.ggl.game.life.model.GameOfLifeModel;
import com.ggl.game.life.view.GameOfLifeFrame;

public class Generation implements Runnable {
	
	private volatile boolean running;
	
	private GameOfLifeFrame frame;
	
	private GameOfLifeModel model;

	public Generation(GameOfLifeFrame frame, 
			GameOfLifeModel model) {
		this.frame = frame;
		this.model = model;
		this.running = true;
	}

	@Override
	public void run() {
		while (running) {
			if (model.isRunning()) {
				model.calculateNextGeneration();
				updateView(model.getGeneration());
			}
			long duration = 1000L * model.getDelay();
			sleep(duration);
		}
	}
	
	private void updateView(int generation) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.getDrawingPanel().repaint();
				frame.getControlPanel().setGenerationField(
						generation);
			}	
		});
	}
	
	private void sleep(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// Deliberately left empty
		}
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}

}
