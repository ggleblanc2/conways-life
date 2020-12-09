package com.ggl.game.life.model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameOfLifeModel {
	
	private boolean isRunning;
	
	private int numberOfCells;
	private int generation;
	private int delay;
	
	private Set<Point> liveCells;
	
	public GameOfLifeModel() {
		this.numberOfCells = 80;
		this.delay = 5;
		this.liveCells = new HashSet<>();
		createInitialState();
	}
	
	public void createInitialState() {
		this.generation = 0;
		this.isRunning = false;
		int totalCells = numberOfCells * numberOfCells / 5;
		liveCells.clear();
		Random random = new Random();
		for (int i = 0; i < totalCells; i++) {
			int x = random.nextInt(numberOfCells);
			int y = random.nextInt(numberOfCells);
			liveCells.add(new Point(x, y));
		}
	}
	
	public void calculateNextGeneration() {
		Set<Point> cells = new HashSet<>();
		for (int x = 0; x < numberOfCells; x++) {
			for (int y = 0; y < numberOfCells; y++) {
				Point p = new Point(x, y);
				int neighbors = getNeighborCount(p);
				if ((neighbors == 3) || 
						(isLiveCell(p) && neighbors == 2)) {
					cells.add(p);
				}
			}
		}
		liveCells = cells;
		generation++;
	}
	
	private int getNeighborCount(Point p) {
		int count = 0;
		
		for (int i = p.x - 1; i < p.x + 2; i++) {
			for (int j = p.y - 1; j < p.y + 2; j++) {
				if (i == p.x && j == p.y) {
					continue;
				} else {
					Point q = new Point(i, j);
					count = isLiveCell(q) ? count + 1 : count;
				}
			}
		}
		
		return count;
	}
	
	private boolean isLiveCell(Point p) {
		return liveCells.contains(p);
	}

	public int getNumberOfCells() {
		return numberOfCells;
	}

	public int getGeneration() {
		return generation;
	}
	
	public Set<Point> getLiveCells() {
		return liveCells;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
}
