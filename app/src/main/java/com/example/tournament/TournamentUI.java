package com.example.tournament;

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
            this.tournament.setPlayers(listValue);
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

}
