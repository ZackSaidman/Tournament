package com.example.tournament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tournament.databinding.FragmentSecondBinding;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Field;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TournamentUI TUI;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        setDefault();

        MainActivity activity = (MainActivity) getActivity();
        TUI = activity.getMyData();

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
                TUI.generate();
                setDefault();
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


//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);

            }
        });
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}