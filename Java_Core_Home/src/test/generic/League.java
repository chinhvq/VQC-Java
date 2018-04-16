package test.generic;

import java.util.ArrayList;
import java.util.Collections;

//sử dụng generic kiểu team -> lọc theo kiểu đội bóng -> đội bóng đá không thể tham gia giải bóng chày
public class League<T extends Team> {
	private String sName;
	ArrayList<T> arrayLeague = new ArrayList<>();

	protected League(String sName) {
		this.sName = sName;
	}

	protected String getsName() {
		return sName;
	}

	protected boolean addTeam(T newTeam) {
		if (arrayLeague.contains(newTeam)) {
			System.out.println("\nTeam " + newTeam + " already in the league. NOT ADDED");
			return false;
		} else {
			arrayLeague.add(newTeam);
			System.out.println("\nTeam " + newTeam + " was added into the league.");
			return true;
		}
	}

	// show team in league table in order of ranking based on Comparable in Team
	// Class
	@SuppressWarnings("unchecked")
	protected void showLeague() {
		Collections.sort(arrayLeague);
		for (T t : arrayLeague) {
			System.out.println("\nTeam\t" + t.getsName() + "\tRanking\t" + t.ranking());
		}
	}
}
