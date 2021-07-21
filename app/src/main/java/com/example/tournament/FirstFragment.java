package com.example.tournament;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tournament.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private TournamentUI TUI;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        MainActivity activity = (MainActivity) getActivity();
        TUI = activity.getMyData();

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.startingPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.startingPlayers.setText("");
            }
        });

        binding.numberOfCourts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.numberOfCourts.setText("");
            }
        });

        binding.submitPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startingPlayers = binding.startingPlayers.getText().toString();
                TUI.startPlayers(startingPlayers);
            }
        });

        binding.submitCourts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startingCourts = binding.numberOfCourts.getText().toString();
                TUI.startCourts(startingCourts);
            }
        });



        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TUI.init();
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}