package target.model;

import java.util.*;
import target.model.Team;
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

	public void addTeam (Team teamToBeAdded) {
		this.teams.add(teamToBeAdded);
	}

	public ArrayList<Team> getTeams () {
		Collections.sort(this.teams, (team1, team2) -> team1.compareTo(team2));
		return this.teams;
	}

	public void insertData (Map<String, Integer> data) {
		for (String name : data.keySet()) {
			this.addTeam(new Team(name, data.get(name)));

			if (name.length() >= this.teamNameMaxWidth) {
				this.teamNameMaxWidth = name.length();
			}
		}
	}

	public void printTable () {
		Collections.sort(this.teams, (team1, team2) -> team1.compareTo(team2));
		String printFormat = "%-" + (this.teamNameMaxWidth) + "s %2s %2s %2s %2s%n";
		System.out.printf(printFormat, "Team", "W", "D", "L", "P");

		this.teams.forEach((team) -> {
			System.out.printf(printFormat, team.getName(), team.getNoOfWin(), team.getNoOfDraw(), team.getNoOfLoss(), team.getNoOfPoint());
		});
	}

	public void startSeason () {
		// final ExecutorService executor = Executors.newFixedThreadPool(4);
		// for (int i = 0; i < this.teams.size(); i++) {
		// 	Team team = this.teams.get(i);
		// 	executor.submit(() -> {
		// 		for (int z = 0; z < this.teams.size(); z++) {
		// 			Team opponent = this.teams.get(z);
		// 			team.startMatch(opponent);
		// 		}
		// 	});
		// }
		// this.teams.forEach((team) -> {
		// 	executor.submit(() -> {
		// 		this.teams.forEach((opponent) -> {
		// 			if (team.getName().equals(opponent.getName())) return;	
		// 			team.startMatch(opponent);	
		// 		});
		// 	});
		// });
		// executor.shutdown();

		// this.teams.forEach((team) -> {
		// 	this.teams.forEach((opponent) -> {
		// 		if (team.getName().equals(opponent.getName())) return;
		// 		team.startMatch(opponent);	
		// 	});
		// });
		Thread t1 = new Thread(new StartSeasonRunnable(this.teams, 0, 5));
		Thread t2 = new Thread(new StartSeasonRunnable(this.teams, 5, 10));
		Thread t3 = new Thread(new StartSeasonRunnable(this.teams, 10, 15));
		Thread t4 = new Thread(new StartSeasonRunnable(this.teams, 15, 20));
		t1.start();
		t2.start();
		t3.start();
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.teams.forEach((team) -> {
			if (team.getNoOfWin() + team.getNoOfDraw() + team.getNoOfLoss() != 38) {
				System.out.println("RACE CONDITION!");
			}
		});
	}
}