package target.thread;

import target.model.*;
import java.util.*;
import java.util.concurrent.*;

public class StartSeasonRunnable implements Runnable {
	private ArrayList<Team> teams;
	private int lowerCount;
	private int upperCount;

	public StartSeasonRunnable(ArrayList<Team> teams, int lowerCount, int upperCount) {
		this.teams = teams;
		this.lowerCount = lowerCount;
		this.upperCount = upperCount;
	}

  // Runnable run() function to play 38 matches for 5 teams each thread
	@Override
	public void run() {
    for (int i = lowerCount; i < upperCount; i ++) {
      Team team = teams.get(i); 
      for (int z = 0; z < teams.size(); z++) {
        Team opponent = teams.get(z);
        // Team A can't play against Team A, so continue.
        if (team.getName().equals(opponent.getName())) continue;
        team.startMatch(opponent);  
      }
    }
  }
}