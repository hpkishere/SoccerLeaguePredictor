package target.model;

import java.util.*;
import target.model.Team;
import target.thread.StartSeasonRunnable;
import java.util.concurrent.*;

public class League {
	private int size;
	private String name;
	private ArrayList<Team> teams = new ArrayList<>();
	private int teamNameMaxWidth = 0;

	public League (int size, String name) {
		this.size = size;
		this.name = name;
	}

	// Add a team to the league
	public void addTeam (Team teamToBeAdded) {
		this.teams.add(teamToBeAdded);
	}

	// Sort and get all teams in the league
	public ArrayList<Team> getTeams () {
		Collections.sort(this.teams, (team1, team2) -> team1.compareTo(team2));
		return this.teams;
	}

	// Insert data into the league
	public void insertData (Map<String, Integer> data) {
		for (String name : data.keySet()) {
			this.addTeam(new Team(name, data.get(name)));

			if (name.length() >= this.teamNameMaxWidth) {
				this.teamNameMaxWidth = name.length();
			}
		}
	}

	// Print table of the league (testing purposes)
	public void printTable () {
		Collections.sort(this.teams, (team1, team2) -> team1.compareTo(team2));
		String printFormat = "%-" + (this.teamNameMaxWidth) + "s %2s %2s %2s %2s%n";
		System.out.printf(printFormat, "Team", "W", "D", "L", "P");

		this.teams.forEach((team) -> {
			System.out.printf(printFormat, team.getName(), team.getNoOfWin(), team.getNoOfDraw(), team.getNoOfLoss(), team.getNoOfPoint());
		});
	}

	// Start a full 38 matches season
	public void startSeason () {
		// Create 4 threads, each thread runs 38 matches for 5 teams
		Thread t1 = new Thread(new StartSeasonRunnable(this.teams, 0, 5));
		Thread t2 = new Thread(new StartSeasonRunnable(this.teams, 5, 10));
		Thread t3 = new Thread(new StartSeasonRunnable(this.teams, 10, 15));
		Thread t4 = new Thread(new StartSeasonRunnable(this.teams, 15, 20));
		t1.start();
		t2.start();
		t3.start();
		t4.start();


		// ---RACE CONDITION CHECK STARTS---
		// If any of the total matches played by any team is not 38, triggers
		// a print statement on console
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.teams.size(); i++) {
			Team team = this.teams.get(i);
			if (team.getNoOfWin() + team.getNoOfDraw() + team.getNoOfLoss() != 38) {
				System.out.println("RACE CONDITION!");
				this.printTable();
			}
		}

		// ---RACE CONDITION CHECK ENDS---
	}
}