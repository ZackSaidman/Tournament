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
        MainActivity activity = (MainActivity) getActivity();
        TUI = activity.getMyData();

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TUI.generate();

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
                if (TUI.tournament.currentMatches.size() >= 3) {
                    binding.player9.setText(TUI.tournament.currentMatches.get(2).get(0).get(0));
                    binding.player10.setText(TUI.tournament.currentMatches.get(2).get(0).get(1));
                    binding.player11.setText(TUI.tournament.currentMatches.get(2).get(1).get(0));
                    binding.player12.setText(TUI.tournament.currentMatches.get(2).get(1).get(1));
                }
                if (TUI.tournament.currentMatches.size() >= 4) {
                    binding.player13.setText(TUI.tournament.currentMatches.get(3).get(0).get(0));
                    binding.player14.setText(TUI.tournament.currentMatches.get(3).get(0).get(1));
                    binding.player15.setText(TUI.tournament.currentMatches.get(3).get(1).get(0));
                    binding.player16.setText(TUI.tournament.currentMatches.get(3).get(1).get(1));
                }


//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}