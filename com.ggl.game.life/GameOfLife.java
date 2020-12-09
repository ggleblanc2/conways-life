package com.ggl.game.life;

import javax.swing.SwingUtilities;

import com.ggl.game.life.model.GameOfLifeModel;
import com.ggl.game.life.view.GameOfLifeFrame;

public class GameOfLife implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new GameOfLife());
	}
	
	@Override
	public void run() {
		new GameOfLifeFrame(new GameOfLifeModel());
	}

}
