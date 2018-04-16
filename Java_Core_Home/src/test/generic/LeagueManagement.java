package test.generic;

public class LeagueManagement {

	public static void main(String[] args) {
		League<Team<FootballPlayer>> footballLeague = new League<>("AFL");
        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        Team<FootballPlayer> hawthorn= new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle= new Team<>("Fremantle");
        Team<BasedBallPlayer> baseballTeam = new Team<>("Chicago Cubs");

        hawthorn.matchResult(fremantle, 1, 0);
        hawthorn.matchResult(adelaideCrows, 3, 8);

        adelaideCrows.matchResult(fremantle, 2, 1);

        footballLeague.addTeam(adelaideCrows);
        footballLeague.addTeam(melbourne);
        footballLeague.addTeam(hawthorn);
        footballLeague.addTeam(fremantle);

//        footballLeague.add(baseballTeam);
        footballLeague.showLeague();

        BasedBallPlayer pat = new BasedBallPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");
        Team rawTeam = new Team("Raw Team");
        rawTeam.addPlayer(beckham); // unchecked warning
        rawTeam.addPlayer(pat);     // unchecked warning

        footballLeague.addTeam(rawTeam);     // unchecked warning

        League<Team> rawLeague = new League<>("Raw");
        rawLeague.addTeam(adelaideCrows);     // no warning
        rawLeague.addTeam(baseballTeam);    // no warning
        rawLeague.addTeam(rawTeam);         // no warning

        League reallyRaw = new League<Team>("Really raw");
        reallyRaw.addTeam(adelaideCrows);     // unchecked warning
        reallyRaw.addTeam(baseballTeam);    // unchecked warning
        reallyRaw.addTeam(rawTeam);         // unchecked warning
	}

}
