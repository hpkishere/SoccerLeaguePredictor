package target.application;

import java.util.*;
import target.model.*;
import target.dummy.*;

public class Main {
	public static void main (String[] args) {
		League premierLeague = new League(20, "English Premier League");
		try {
			premierLeague.insertData(EPLData.teamData);
			premierLeague.startSeason();
			premierLeague.printTable();	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}