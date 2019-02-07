package target.application;

import java.util.*;
import java.util.concurrent.*;
import target.model.*;
import target.dummy.*;
import target.helper.*;
import target.application.*;

public class Main {
	public final static int LOOP_NUMBER = 1_000;
	public final static int THREAD_NUMBER = 4;

	public static void main (String[] args) {
		// Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		// HashMap<String, Integer> results = new HashMap<>();
	  // ArrayList<Future> futures = new ArrayList<>();
	 //  ExecutorService exec = Executors.newFixedThreadPool(THREAD_NUMBER);

		// for (int i = 0; i < 4; i++) {
  //   	RunSeasonRunnable runnableTask = new RunSeasonRunnable(results, 0, 250);
		// 	// RunSeasonCallable callableTask = new RunSeasonCallable();
  //   	exec.submit(runnableTask);
		// 	// Future<Team> future = exec.submit(callableTask);
		// 	// try {
		// 	// 	Team champion = future.get(); 

		// 	// 	if (results.get(champion.getName()) != null) {
		// 	// 		results.replace(champion.getName(), results.get(champion.getName()) + 1);
		// 	// 	} else {
		// 	// 		results.put(champion.getName(), 1);
		// 	// 	} 
		// 	// } catch (InterruptedException | ExecutionException e) {
		// 	// 	e.printStackTrace();
		// 	// }

		// }

		// exec.shutdown();

		// try {
		// 	exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		// } catch (InterruptedException e) {
		// 	e.printStackTrace();
		// }

		for (int i = 0; i < LOOP_NUMBER; i++) {
			League premierLeague = new League(20, "English Premier League");
			premierLeague.insertData(EPLData.teamData);
			premierLeague.startSeason();
			// ArrayList<Team> teams = premierLeague.getTeams();

			// Team champion = teams.get(0);

			// if (results.get(champion.getName()) != null) {
			// 	results.replace(champion.getName(), results.get(champion.getName()) + 1);
			// } else {
			// 	results.put(champion.getName(), 1);
			// }
		} 
		// int total = 0;
		// for (int championships : results.values()) {
		// 	total += championships;
		// }

		// if (total != LOOP_NUMBER) {
		// 	System.out.println("TOTAL IN HASHMAP: " + total);
		// 	System.out.println("SUPPOSED TOTAL: " + LOOP_NUMBER);
		// }

		// System.out.println(results);

		// End of application, get timing
		System.out.println("\nTime elapsed : " + stopWatch.toString());
	}
}