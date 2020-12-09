package com.ggl.game.life.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ggl.game.life.model.GameOfLifeModel;
import com.ggl.game.life.view.GameOfLifeFrame;

public class ButtonListener implements ActionListener {

	private GameOfLifeFrame frame;
	
	private GameOfLifeModel model;
	
	public ButtonListener(GameOfLifeFrame frame, 
			GameOfLifeModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (model.isRunning()) {
			frame.getControlPanel().setButtonText(
					"Resume Simulation");
			model.setRunning(false);
		} else {
			frame.getControlPanel().setButtonText(
					"Pause Simulation");
			model.setRunning(true);
		}
	}

}
