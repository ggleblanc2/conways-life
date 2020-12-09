package com.ggl.game.life.controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ggl.game.life.model.GameOfLifeModel;
import com.ggl.game.life.view.GameOfLifeFrame;

public class DelayListener implements ChangeListener {

	private GameOfLifeFrame frame;
	
	private GameOfLifeModel model;
	
	public DelayListener(GameOfLifeFrame frame, 
			GameOfLifeModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		model.setDelay(frame.getControlPanel().getSliderValue());
	}

}
