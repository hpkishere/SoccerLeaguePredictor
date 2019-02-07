package target.model;

import target.model.*;
import java.util.*;
import java.util.concurrent.*;

public class StartSeasonRunnable implements Runnable {
  // private Team team;
	private ArrayList<Team> teams;
	private int lowerCount;
	private int upperCount;

	public StartSeasonRunnable(ArrayList<Team> teams, int lowerCount, int upperCount) {
    // this.team = team;
		this.teams = teams;
		this.lowerCount = lowerCount;
		this.upperCount = upperCount;
	}

	@Override
	public void run() {
		for (int i = lowerCount; i < upperCount; i ++) {
			Team team = teams.get(i);	
			for (int z = 0; z < teams.size(); z++) {
				Team opponent = teams.get(z);
				if (team.getName().equals(opponent.getName())) continue;
				team.startMatch(opponent);  
			}
		}
  //  this.teams.forEach((team) -> {
    //  this.teams.forEach((opponent) -> {
    //    if (team.getName().equals(opponent.getName())) return;
    //    team.startMatch(opponent);  
    //  });
    // });
	}
}