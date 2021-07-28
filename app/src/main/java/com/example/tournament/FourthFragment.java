package com.example.tournament;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournament.databinding.FragmentFourthBinding;

import java.util.List;


public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;
    private TournamentUI TUI;
    private List<String> scores;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFourthBinding.inflate(inflater, container, false);

        MainActivity activity = (MainActivity) getActivity();
        TUI = activity.getMyData();
        scores = TUI.getScores();

        outputScores();

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FourthFragment.this)
                        .navigate(R.id.action_fourthFragment_to_thirdFragment);

            }
        });
    }

    public void outputScores() {
        int value;

        value = 14;
        if (scores.size() >= value) {
            binding.score1.setText(scores.get(value - 1));
        }
        value = 15;
        if (scores.size() >= value) {
            binding.score2.setText(scores.get(value - 1));
        }
        value = 16;
        if (scores.size() >= value) {
            binding.score3.setText(scores.get(value - 1));
        }
        value = 17;
        if (scores.size() >= value) {
            binding.score4.setText(scores.get(value - 1));
        }
        value = 18;
        if (scores.size() >= value) {
            binding.score5.setText(scores.get(value - 1));
        }
        value = 19;
        if (scores.size() >= value) {
            binding.score6.setText(scores.get(value - 1));
        }
        value = 20;
        if (scores.size() >= value) {
            binding.score7.setText(scores.get(value - 1));
        }
        value = 21;
        if (scores.size() >= value) {
            binding.score8.setText(scores.get(value - 1));
        }
        value = 22;
        if (scores.size() >= value) {
            binding.score9.setText(scores.get(value - 1));
        }
        value = 23;
        if (scores.size() >= value) {
            binding.score10.setText(scores.get(value - 1));
        }
        value = 24;
        if (scores.size() >= value) {
            binding.score11.setText(scores.get(value - 1));
        }
        value = 25;
        if (scores.size() >= value) {
            binding.score12.setText(scores.get(value - 1));
        }
        value = 26;
        if (scores.size() >= value) {
            binding.score13.setText(scores.get(value - 1));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}