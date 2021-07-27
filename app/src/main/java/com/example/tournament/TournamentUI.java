package com.example.tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TournamentUI {
    Engine tournament = new Engine();

    public void generate() {
        tournament.process();
    }

    public void init() {
        tournament.initPlayers();
    }

    public void startPlayers(String returnValue) {
        try {
            returnValue = returnValue.replace(" ", "");
            List<String> listValue = Arrays.asList(returnValue.split(","));
            this.tournament.addPlayers(listValue);
            System.out.println("Players set");
        }
        catch (Exception ee) {
            System.out.println("format unrecognized");
        }

    }

    public void startCourts(String returnValue) {
        try {
            int intValue = Integer.parseInt(returnValue);
            this.tournament.setCourts(intValue);
            System.out.println("Courts set");
        }
        catch (Exception ee){
            System.out.println("format unrecognized");
        }
    }

    public void addPlayers(String returnValue) {
        try {
            returnValue = returnValue.replace(" ", "");
            List<String> listValue = Arrays.asList(returnValue.split(","));
            this.tournament.addPlayers(listValue);
            System.out.println("Players added");
        }
        catch (Exception ee) {
            System.out.println("format unrecognized");
        }
    }

    public void removePlayers(String returnValue) {
        try {
            returnValue = returnValue.replace(" ", "");
            List<String> listValue = Arrays.asList(returnValue.split(","));
            this.tournament.removePlayers(listValue);
            System.out.println("Players removed");
        }
        catch (Exception ee) {
            System.out.println("format unrecognized");
        }
    }

    public void returningPlayers(String returnValue) {
        try {
            returnValue = returnValue.replace(" ", "");
            List<String> listValue = Arrays.asList(returnValue.split(","));
            this.tournament.returningPlayers(listValue);
            System.out.println("Players re-admitted");
        }
        catch (Exception ee) {
            System.out.println("format unrecognized");
        }
    }

    public boolean newWinners(String winners) {
        try {
            winners = winners.replace(" ", "");
            List<String> winnersList = Arrays.asList(winners.split(","));

            if (winnersList.contains("None")) {
                return false;
            }

            if (((double) winnersList.size() / (double) 2) == (double) tournament.courtsInUse) {
                tournament.updateScore(winnersList);
                return true;
            }
            else {
                System.out.println("Submit all scores");
            }
        }
        catch (Exception ee) {
            System.out.println("format unrecognized");
        }

        return false;
    }

    public List<String> getScores() {
        List<String> score = new ArrayList<>();

        for (String player : this.tournament.scores.keySet()) {
            int wins = this.tournament.scores.get(player);
            int totalGames = this.tournament.playerGamesPlayed.get(player);
            int losses = totalGames - wins;
            score.add(player + " - W:" + wins + " L:" + losses);
        }
        return score;

    }

}
