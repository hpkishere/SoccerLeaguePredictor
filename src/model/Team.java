package target.model;

import java.lang.Math;

public class Team {
	private String name;
	private int seed;
	private int noOfWin = 0;
	private int noOfDraw = 0;
	private int noOfLoss = 0;
	private int noOfPoint = 0;

	public Team (String name, int seed) {
		this.name = name;
		this.seed = seed;
	}

	public String getName () {
		return this.name;
	}

	public int getSeed () {
		return this.seed;
	}

	public int getNoOfWin () {
		return this.noOfWin;
	}

	public void setNoOfWin (int newNoOfWin) {
		this.noOfWin = newNoOfWin;
	}

	public int getNoOfDraw () {
		return this.noOfDraw;
	}

	public void setNoOfDraw (int newNoOfDraw) {
		this.noOfDraw = newNoOfDraw;
	}

	public int getNoOfLoss () {
		return this.noOfLoss;
	}

	public void setNoOfLoss (int newNoOfLoss) {
		this.noOfLoss = newNoOfLoss;
	}

	public int getNoOfPoint () {
		return this.noOfPoint;
	}

	public void setNoOfPoint () {
		this.noOfPoint = 3 * this.noOfWin + this.noOfDraw;
	}

	public void setWin () {
		this.noOfWin++;
		this.setNoOfPoint();
	}

	public void setDraw () {
		this.noOfDraw++;
		this.setNoOfPoint();
	}

	public void setLoss () {
		this.noOfLoss++;
		this.setNoOfPoint();
	}

	public synchronized void startMatch (Team opponent) {
		double randomVal = Math.random();

		if (this.seed < opponent.getSeed()) {
			if (randomVal < 0.7) {
				this.setWin();
				opponent.setLoss();
			} else if (randomVal < 0.9) {
				this.setDraw();
				opponent.setDraw();
			} else {
				this.setLoss();
				opponent.setWin();
			}
		} else {
			if (randomVal < 0.3) {
				this.setWin();
				opponent.setLoss();
			} else if (randomVal < 0.5) {
				this.setDraw();
				opponent.setDraw();
			} else {
				this.setLoss();
				opponent.setWin();
			}
		}
	}

	public int compareTo (Team anotherTeam) {
		return anotherTeam.getNoOfPoint() - this.noOfPoint;
	}
}