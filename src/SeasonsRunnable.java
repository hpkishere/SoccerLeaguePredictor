package target.application;

import target.model.*;
import target.dummy.*;
import java.util.*;
import java.util.concurrent.*;

// Runnable class to simulate a specified number of seasons
public class SeasonsRunnable implements Runnable {
  public static HashMap<String, Integer> results = new HashMap<>();
  private int lowerCount;
  private int upperCount;

  public SeasonsRunnable(int lowerCount, int upperCount) {
    this.lowerCount = lowerCount;
    this.upperCount = upperCount;
  }
  
  @Override
  public void run() {
    for (int i = this.lowerCount; i < this.upperCount; i++) {
      League premierLeague = new League(20, "English Premier League");
      premierLeague.insertData(EPLData.teamData);
      premierLeague.startSeason();
      ArrayList<Team> teams = premierLeague.getTeams();
      Team champion = teams.get(0);

      // Lock the read/write access to HashMap to prevent race condition
      synchronized (results) {
        if (results.get(champion.getName()) != null) {
          results.put(champion.getName(), results.get(champion.getName()) + 1);
        } else {
          results.put(champion.getName(), 1);
        }
      }
    }
  }
}