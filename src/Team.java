package target.model;

import java.lang.Math;

// Team class
// Each Team object will have its team name, pre-defined seed,
// number of wins, number of draws, number of losses and points
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

	// GETTERS

	public String getName () {
		return this.name;
	}

	public int getSeed () {
		return this.seed;
	}

	public int getNoOfWin () {
		return this.noOfWin;
	}

	public int getNoOfDraw () {
		return this.noOfDraw;
	}

	public int getNoOfLoss () {
		return this.noOfLoss;
	}

	public int getNoOfPoint () {
		return this.noOfPoint;
	}

	// Calculate number of points acquired by the team.
	// 1 win = 3 points
	// 1 draw = 1 point
	// 1 loss = 0 point
	public void setNoOfPoint () {
		this.noOfPoint = 3 * this.noOfWin + this.noOfDraw;
	}

	// Acquire lock before writing into the Team object
	public synchronized void setWin () {
		this.noOfWin++;
		this.setNoOfPoint();
	}

	// Acquire lock before writing into the Team object
	public synchronized void setDraw () {
		this.noOfDraw++;
		this.setNoOfPoint();
	}

	// Acquire lock before writing into the Team object
	public synchronized void setLoss () {
		this.noOfLoss++;
		this.setNoOfPoint();
	}

	// Basic algorithm based on team's seed of previous season
	public void startMatch (Team opponent) {
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