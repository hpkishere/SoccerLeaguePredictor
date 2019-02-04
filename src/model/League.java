package target.model;

import java.util.*;
import target.model.Team;

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
		// System.out.println("\nINSERTING DATA..");
		for (String name : data.keySet()) {
			this.addTeam(new Team(name, data.get(name)));

			if (name.length() >= this.teamNameMaxWidth) {
				this.teamNameMaxWidth = name.length();
			}
		}
		// System.out.println("\nDATA SUCCESSFULLY INSERTED");
	}

	public void printTable () {
		// System.out.println("\nPRINTING FINAL RESULTS..\n");
		Collections.sort(this.teams, (team1, team2) -> team1.compareTo(team2));
		String printFormat = "%-" + (this.teamNameMaxWidth) + "s %2s %2s %2s %2s%n";
		System.out.printf(printFormat, "Team", "W", "D", "L", "P");

		this.teams.forEach((team) -> {
			System.out.printf(printFormat, team.getName(), team.getNoOfWin(), team.getNoOfDraw(), team.getNoOfLoss(), team.getNoOfPoint());
		});
	}

	public void startSeason () {
		// System.out.println("\nSTARTING SEASON..");
		this.teams.forEach((team) -> {
			this.teams.forEach((opponent) -> {
				if (team.getName().equals(opponent.getName())) return;
				team.startMatch(opponent);
			});
		});
		// System.out.println("\nSEASON IS FINISHED!");
	}
}