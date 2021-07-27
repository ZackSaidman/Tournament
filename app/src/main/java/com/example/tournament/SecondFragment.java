package com.example.tournament;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tournament.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TournamentUI TUI;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        MainActivity activity = (MainActivity) getActivity();
        TUI = activity.getMyData();

        setPlayers();

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.addPlayers.setText("");
            }
        });

        binding.removePlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.removePlayers.setText("");
            }
        });

        binding.returningPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.returningPlayers.setText("");
            }
        });

        binding.addPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startingPlayers = binding.addPlayers.getText().toString();
                TUI.addPlayers(startingPlayers);
            }
        });

        binding.removePlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startingPlayers = binding.removePlayers.getText().toString();
                TUI.removePlayers(startingPlayers);
            }
        });

        binding.returningPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startingCourts = binding.returningPlayers.getText().toString();
                TUI.returningPlayers(startingCourts);
            }
        });

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean readyToGenerate = true;
                if (!binding.player1.getText().toString().equals("None")) {
                    readyToGenerate = TUI.newWinners(binding.winTextOutput.getText().toString());
                }

                if (readyToGenerate) {
                    TUI.generate();
                    setDefault();
                    setPlayers();
                }

            }
        });

        binding.scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);

            }
        });

        binding.win1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1 = binding.player1.getText().toString();
                String player2 = binding.player2.getText().toString();
                String player3 = binding.player3.getText().toString();
                String player4 = binding.player4.getText().toString();
                updateWinnerText(player1, player2, player3, player4);
            }
        });

        binding.win2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1 = binding.player1.getText().toString();
                String player2 = binding.player2.getText().toString();
                String player3 = binding.player3.getText().toString();
                String player4 = binding.player4.getText().toString();
                updateWinnerText(player3, player4, player1, player2);
            }
        });

        binding.win3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player5 = binding.player5.getText().toString();
                String player6 = binding.player6.getText().toString();
                String player7 = binding.player7.getText().toString();
                String player8 = binding.player8.getText().toString();
                updateWinnerText(player5, player6, player7, player8);
            }
        });

        binding.win4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player5 = binding.player5.getText().toString();
                String player6 = binding.player6.getText().toString();
                String player7 = binding.player7.getText().toString();
                String player8 = binding.player8.getText().toString();
                updateWinnerText(player7, player8, player5, player6);
            }
        });
    }

    public void updateWinnerText(String winner1, String winner2, String loser1, String loser2) {
        String output = "";
        String line = binding.winTextOutput.getText().toString();

        if (line.contains(winner1) && line.contains(winner2)) {
            return;
        }

        if (line.equals("None") || binding.winTextOutput.getText().toString().equals("")) {
            output = winner1 + "," + winner2;
        }
        else {
            List<String> listLine = new ArrayList<>(Arrays.asList(line.split(",")));
            listLine.remove(loser1);
            listLine.remove(loser2);
            if (!winner1.equals("None") && !winner2.equals("None")) {
                listLine.add(winner1);
                listLine.add(winner2);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                output = String.join(",", listLine);
            }

        }
        binding.winTextOutput.setText(output);
    }

    public void setDefault() {
        String DEFAULT = "None";
        binding.player1.setText(DEFAULT);
        binding.player2.setText(DEFAULT);
        binding.player3.setText(DEFAULT);
        binding.player4.setText(DEFAULT);
        binding.player5.setText(DEFAULT);
        binding.player6.setText(DEFAULT);
        binding.player7.setText(DEFAULT);
        binding.player8.setText(DEFAULT);
        binding.winTextOutput.setText(DEFAULT);
    }

    public void setPlayers() {
        if (TUI.tournament.currentMatches != null) {
            if (TUI.tournament.currentMatches.size() >= 1) {
                binding.player1.setText(TUI.tournament.currentMatches.get(0).get(0).get(0));
                binding.player2.setText(TUI.tournament.currentMatches.get(0).get(0).get(1));
                binding.player3.setText(TUI.tournament.currentMatches.get(0).get(1).get(0));
                binding.player4.setText(TUI.tournament.currentMatches.get(0).get(1).get(1));
            }
            if (TUI.tournament.currentMatches.size() >= 2) {
                binding.player5.setText(TUI.tournament.currentMatches.get(1).get(0).get(0));
                binding.player6.setText(TUI.tournament.currentMatches.get(1).get(0).get(1));
                binding.player7.setText(TUI.tournament.currentMatches.get(1).get(1).get(0));
                binding.player8.setText(TUI.tournament.currentMatches.get(1).get(1).get(1));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}