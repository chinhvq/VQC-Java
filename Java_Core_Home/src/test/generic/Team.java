package test.generic;

import java.util.ArrayList;

// T thể hiện generic và chỉ lấy giá trị có kiểu player (cầu thủ) các giá trị khác sẽ không chấp nhận
public class Team<T extends Player> implements Comparable<Team<T>> {
	private String sName;
	int nPlayed = 0;
	int nWon = 0;
	int nLost = 0;
	int nTied = 0;

	private ArrayList<T> arrayTeam = new ArrayList<>();

	protected Team(String sName) {
		this.sName = sName;
	}

	protected String getsName() {
		return sName;
	}

	protected void setsName(String sName) {
		this.sName = sName;
	}

	//T là player
	protected boolean addPlayer(T player) {
		if (arrayTeam.contains(player)) {
			System.out.println(player.getsName() + " is already exit in the team");
			return false;
		} else {
			arrayTeam.add(player);
			System.out.println(player.getsName() + " is added to the team " + this.sName);
			return true;
		}
	}

	protected int numPlayer() {
		return this.arrayTeam.size();
	}

	//Team<T> là team kiểu player -> đội chứa các cầu thủ
	protected void matchResult(Team<T> opponent, int nOurScore, int nTheirScore) {
		String sMessage;
		if (nOurScore > nTheirScore) {
			nWon++;
			sMessage = "win";
		} else if (nOurScore == nTheirScore) {
			nTied++;
			sMessage = "drew with";
		} else {
			nLost++;
			sMessage = "lost to";
		}
		nPlayed++;
		if (opponent != null) {
			System.out.println(this.getsName() + "\t" + sMessage + "\t" + opponent.getsName());
			matchResult(null, nTheirScore, nOurScore);
		}
	}

	protected int ranking() {
		return (nWon * 2) + nTied;
	}

	@Override
	public int compareTo(Team<T> compareTeam) {
		if (this.ranking() > compareTeam.ranking()) {
			return -1;
		} else if (this.ranking() < compareTeam.ranking()) {
			return 1;
		} else {
			return 0;
		}
	}

}
