package target.application;

import target.model.*;
import target.dummy.*;
import java.util.*;
import java.util.concurrent.*;

public class RunSeasonRunnable implements Runnable {
  private ConcurrentHashMap<String, Integer> results;
  private int lowerCount;
  private int upperCount;

  public RunSeasonRunnable(ConcurrentHashMap<String, Integer> results, int lowerCount, int upperCount) {
    this.results = results;
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

      if (this.results.get(champion.getName()) != null) {
       this.results.put(champion.getName(), this.results.get(champion.getName()) + 1);
      } else {
       this.results.put(champion.getName(), 1);
      }
    }
  }
}