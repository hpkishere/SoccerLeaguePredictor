package target.application;

import java.util.*;
import java.util.concurrent.*;
import java.lang.Math;
import java.util.stream.*;
import target.model.*;
import target.dummy.*;
import target.helper.*;
import target.application.*;

public class Main {
	// No of times a season is going to be simulated
	public final static int SEASON_NUMBER = 1_000;
	// No of threads
	public final static int THREAD_NUMBER = 4;

	public static void main (String[] args) {
		// Start stopwatch
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		System.out.println("RUNNING A SIMULATION OF " + SEASON_NUMBER + " SEASONS...\n");

		// Create an executor service
	  ExecutorService exec = Executors.newFixedThreadPool(THREAD_NUMBER);

		for (int i = 0; i < THREAD_NUMBER; i++) {
			// For each thread, execute a SeasonsRunnable task
    	exec.submit(new SeasonsRunnable(0, SEASON_NUMBER/THREAD_NUMBER));
		}

		exec.shutdown();

		// Wait until all tasks are done
		try {
			exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		HashMap<String, Integer> results = SeasonsRunnable.results;

		// ---RACE CONDITION CHECK STARTS---
		// If the number of champions do not equal to the SEASON_NUMBER
		// output a print statement on console

		int total = 0;
		for (int championships : results.values()) {
			total += championships;
		}

		if (total != SEASON_NUMBER) {
			System.out.println("TOTAL IN HASHMAP: " + total);
			System.out.println("SUPPOSED TOTAL: " + SEASON_NUMBER);
		}

		// ---RACE CONDITION CHECK ENDS---

		System.out.println("DONE! GENERATING REPORT...\n");

		printTable(results);

		// End of application, get timing
		System.out.println("\nTime elapsed : " + stopWatch.toString());
	}

	// Helper method to print results in table form
	public static void printTable (HashMap<String, Integer> results) {
		// Sort HashMap by descending value
		HashMap<String, Integer> sortedResults = results
        .entrySet()
        .stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (team1, team2) -> team2,
                LinkedHashMap::new));

    int teamNameMaxWidth = 0;

    for (String teamName : sortedResults.keySet()) {
    	if (teamName.length() >= teamNameMaxWidth) {
				teamNameMaxWidth = teamName.length();
			}
    }

		String printFormat = "%-" + (teamNameMaxWidth) + "s %14s %4s%n";
		System.out.printf(printFormat, "Team", "Wins", "%");

		// Iterate through sorted HashMap to get the values
		sortedResults.forEach((teamName, championships) -> {
			System.out.printf(printFormat, teamName, championships, championships * 100.0/SEASON_NUMBER);
		});
	}
}