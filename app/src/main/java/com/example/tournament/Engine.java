package com.example.tournament;

//import androidx.annotation.ArrayRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Engine {
    // inputs
    List<String> players = new ArrayList<>();
    int numOfCourts;

    // holder data (sql?)
    HashMap<String, List<String>> matchesMadeTeammate = new HashMap<>();
    HashMap<String, List<String>> matchesMadeOpponent = new HashMap<>();
    HashMap<String, Integer> playerGamesPlayed = new HashMap<>();

    // output
    List<List<String>> teams;
    List<List<List<String>>> currentMatches;

    public void process() {
        this.teams = new ArrayList<>();
        this.currentMatches = new ArrayList<>();
        this.createTeammates();
        this.createMatchups();
        this.updateDataset();
        System.out.println("Round Generated");

    }

    public void createTeammates() {
        List<String> assignedPlayers = new ArrayList<>();
        Map<String, Integer> sortedPlayers = sortByValue(this.playerGamesPlayed);
        for (String player : sortedPlayers.keySet()) {

            if (assignedPlayers.contains(player) || !this.players.contains(player)) {
                continue;
            }
            HashMap<String, Object> bestTeammate = new HashMap<>();
            bestTeammate.put("teammate", null);
            bestTeammate.put("matchesPlayedWith", Double.POSITIVE_INFINITY);

            for (String teammate : sortedPlayers.keySet()) {
                if (player.equals(teammate) || assignedPlayers.contains(teammate) || !this.players.contains(teammate)) {
                    continue;
                }

                double matchesPlayedWith = Collections.frequency(this.matchesMadeTeammate.get(player), teammate);


                if (bestTeammate.get("teammate") == null || matchesPlayedWith < (Double) bestTeammate.get("matchesPlayedWith")) {
                    bestTeammate.put("teammate", teammate);
                    bestTeammate.put("matchesPlayedWith", matchesPlayedWith);
                }
            }

            if (bestTeammate.get("teammate") != null) {
                List<String> combo = new ArrayList<>();
                combo.add(player);
                combo.add((String) bestTeammate.get("teammate"));
                this.teams.add(combo);
                assignedPlayers.add(player);
                assignedPlayers.add((String) bestTeammate.get("teammate"));
            }
        }
    }

    public void createMatchups() {
        List<List<String>> assignedTeams = new ArrayList<>();
        for (int i=0; i < this.teams.size(); i++) {
            List<String> team = this.teams.get(i);
            if (assignedTeams.contains(team)) {
                continue;
            }
            HashMap<String, Object> bestOpponent = new HashMap<>();
            bestOpponent.put("team", null);
            bestOpponent.put("cases", 0);

            for (int j=0; j < this.teams.size(); j++) {
                List<String> opponent = this.teams.get(j);
                if (team.equals(opponent) || assignedTeams.contains(opponent)) {
                    continue;
                }

                int cond1 = Collections.frequency(this.matchesMadeOpponent.get(team.get(0)), opponent.get(0));
                int cond2 = Collections.frequency(this.matchesMadeOpponent.get(team.get(0)), opponent.get(1));
                int cond3 = Collections.frequency(this.matchesMadeOpponent.get(team.get(1)), opponent.get(0));
                int cond4 = Collections.frequency(this.matchesMadeOpponent.get(team.get(1)), opponent.get(1));
                int conds = cond1 + cond2 + cond3 + cond4;

                if (bestOpponent.get("team") == null || conds < (int) bestOpponent.get("cases")) {
                    bestOpponent.put("team", opponent);
                    bestOpponent.put("cases", conds);
                }
            }


            if (bestOpponent.get("team") != null) {
                List<List<String>> combo = new ArrayList<>();
                combo.add(team);
                combo.add((List<String>) bestOpponent.get("team"));
                this.currentMatches.add(combo);
                assignedTeams.add(team);
                assignedTeams.add((List<String>) bestOpponent.get("team"));
            }

            if (this.currentMatches.size() == this.numOfCourts) {
                return;
            }
        }
    }

    public void updateDataset() {
        for (int i=0; i < this.currentMatches.size(); i++) {
            List<List<String>> match = this.currentMatches.get(i);
            for (int j=0; j < match.size(); j++) {
                List<String> team = match.get(j);
                List<String> opponents = match.get((match.size() - 1) - j);
                for (int k=0; k < team.size(); k++) {
                    String player = team.get(k);
                    this.matchesMadeOpponent.get(player).add(opponents.get(0));
                    this.matchesMadeOpponent.get(player).add(opponents.get(1));

                    String teammate =team.get((team.size() - 1) - k);
                    this.matchesMadeTeammate.get(player).add(teammate);

                    int oldValue = this.playerGamesPlayed.get(player);
                    this.playerGamesPlayed.put(player, oldValue + 1);

                }
            }
        }
    }


    public void initPlayers() {
        if (this.players.size() == 0) {
            System.out.println("Please add players");
        }
        if (this.numOfCourts == 0) {
            System.out.println("Please give a number of courts");
        }


        if ((this.players.size() == 0) || (this.numOfCourts == 0)) {
            return;
        }

        for (int i=0; i < this.players.size(); i++) {
            String player = this.players.get(i);
            this.matchesMadeTeammate.put(player, new ArrayList<String>());
            this.matchesMadeOpponent.put(player, new ArrayList<String>());
            this.playerGamesPlayed.put(player, 0);
        }

        System.out.println("Game initialized");
    }

    public void addPlayers(List<String> newPlayers) {
        boolean duplicateOccurred = false;
        for (int i=0; i < newPlayers.size(); i++) {
            String player = newPlayers.get(i);
            if (this.matchesMadeTeammate.containsKey(player)) {
                System.out.println(player + " already in the game. Please re-enter with new name");
                duplicateOccurred = true;
            }
            else {
                this.players.add(player);
                if (!this.matchesMadeTeammate.containsKey(player)) {
                    this.matchesMadeTeammate.put(player, new ArrayList<String>());
                }
                if (!this.matchesMadeOpponent.containsKey(player)) {
                    this.matchesMadeOpponent.put(player, new ArrayList<String>());
                }
                if (!this.playerGamesPlayed.containsKey(player)) {
                    this.playerGamesPlayed.put(player, 0);
                }
            }
        }

        if (duplicateOccurred) {
            System.out.println("All other names added to the game. Re-enter all duplicate names");
        }
        else {
            System.out.println("New players added");
        }
    }

    public void removePlayers(List<String> playersToRemove) {
        boolean duplicateOccurred = false;
        for (int i=0; i < playersToRemove.size(); i++) {
            String player = playersToRemove.get(i);
            if (this.players.contains(player)) {
                this.players.remove(player);
            }
            else {
                System.out.println(player + " not in the game. Please re-enter with new name");
                duplicateOccurred = true;
            }
        }

        if (duplicateOccurred) {
            System.out.println("All other names removed from the game. Re-enter all failed names");
        }
        else {
            System.out.println("Players removed");
        }
    }

    public void returningPlayers(List<String> returningPlayers) {
        boolean duplicateOccurred = false;
        for (int i=0; i < returningPlayers.size(); i++) {
            String player = returningPlayers.get(i);
            if (this.players.contains(player)) {
                System.out.println(player + " already in the game. Please re-enter with new name");
                duplicateOccurred = true;
            }
            else {
                this.players.add(player);
                if (!this.matchesMadeTeammate.containsKey(player)) {
                    this.matchesMadeTeammate.put(player, new ArrayList<String>());
                }
                if (!this.matchesMadeOpponent.containsKey(player)) {
                    this.matchesMadeOpponent.put(player, new ArrayList<String>());
                }
                if (!this.playerGamesPlayed.containsKey(player)) {
                    this.playerGamesPlayed.put(player, 0);
                }
            }
        }

        if (duplicateOccurred) {
            System.out.println("All other names re-admitted to the game.");
        }
        else {
            System.out.println("Players Re-Admitted");
        }
    }


    // function to sort hashmap by values
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public void setPlayers(List<String> newPlayers) {
        this.players.addAll(newPlayers);
    }

    public void setCourts(int courts) {
        this.numOfCourts = courts;
    }

}

