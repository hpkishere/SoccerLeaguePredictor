package target.application;

import target.model.*;
import target.dummy.*;
import java.util.*;
import java.util.concurrent.*;

public class RunSeasonCallable implements Callable <Team> {
  public Team call() {
    League premierLeague = new League(20, "English Premier League");
    premierLeague.insertData(EPLData.teamData);
    premierLeague.startSeason();
    ArrayList<Team> teams = premierLeague.getTeams();
    Team champion = teams.get(0);

    return champion;
  }
}