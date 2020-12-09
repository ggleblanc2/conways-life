package com.ggl.game.life.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ggl.game.life.model.GameOfLifeModel;
import com.ggl.game.life.view.GameOfLifeFrame;

public class RestartListener implements ActionListener {

	private GameOfLifeFrame frame;
	
	private GameOfLifeModel model;
	
	public RestartListener(GameOfLifeFrame frame, 
			GameOfLifeModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		model.createInitialState();
		frame.getControlPanel().setGenerationField(
				model.getGeneration());
		frame.getControlPanel().setButtonText(
				"Start Simulation");
		frame.getDrawingPanel().repaint();
	}
	
}
