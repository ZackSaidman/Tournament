package com.example.tournament;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tournament.databinding.FragmentThirdBinding;

import java.util.List;


public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    private TournamentUI TUI;
    private List<String> scores;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);

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
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_SecondFragment);

            }
        });

        binding.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_fourthFragment);

            }
        });
    }

    public void outputScores() {
        int value;

        value = 1;
        if (scores.size() >= value) {
            binding.score1.setText(scores.get(value - 1));
        }
        value = 2;
        if (scores.size() >= value) {
            binding.score2.setText(scores.get(value - 1));
        }
        value = 3;
        if (scores.size() >= value) {
            binding.score3.setText(scores.get(value - 1));
        }
        value = 4;
        if (scores.size() >= value) {
            binding.score4.setText(scores.get(value - 1));
        }
        value = 5;
        if (scores.size() >= value) {
            binding.score5.setText(scores.get(value - 1));
        }
        value = 6;
        if (scores.size() >= value) {
            binding.score6.setText(scores.get(value - 1));
        }
        value = 7;
        if (scores.size() >= value) {
            binding.score7.setText(scores.get(value - 1));
        }
        value = 8;
        if (scores.size() >= value) {
            binding.score8.setText(scores.get(value - 1));
        }
        value = 9;
        if (scores.size() >= value) {
            binding.score9.setText(scores.get(value - 1));
        }
        value = 10;
        if (scores.size() >= value) {
            binding.score10.setText(scores.get(value - 1));
        }
        value = 11;
        if (scores.size() >= value) {
            binding.score11.setText(scores.get(value - 1));
        }
        value = 12;
        if (scores.size() >= value) {
            binding.score12.setText(scores.get(value - 1));
        }
        value = 13;
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