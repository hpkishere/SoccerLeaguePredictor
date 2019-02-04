package target.application;

import java.util.*;
import target.model.*;
import target.dummy.*;
import target.helper.*;

public class Main {
	public final static int LOOP_NUMBER = 1_000_000;
	public static HashMap<String, Integer> results = new HashMap<>();

	public static void main (String[] args) {
		StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    for (int i = 0; i < LOOP_NUMBER; i++) {
    	League premierLeague = new League(20, "English Premier League");
			premierLeague.insertData(EPLData.teamData);
			premierLeague.startSeason();
			ArrayList<Team> teams = premierLeague.getTeams();

			Team champion = teams.get(0);

			if (results.get(champion.getName()) != null) {
				results.replace(champion.getName(), results.get(champion.getName()) + 1);
			} else {
				results.put(champion.getName(), 1);
			}
    }	

    System.out.println(results);

		System.out.println("\nTime elapsed : " + stopWatch.toString());
	}
}