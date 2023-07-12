package com.example.poolapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class PollPage extends Fragment {

    ArrayList<String> inputs;
    RecyclerView inputsRecycleView;
    PollPageAdapter recycleViewAdapter;
    Button save_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_poll_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputs = getArguments().getStringArrayList("inputs");
        inputsRecycleView = view.findViewById(R.id.itemRecy);
        recycleViewAdapter = new PollPageAdapter(getContext(), inputs);

        inputsRecycleView.setAdapter(recycleViewAdapter);
        inputsRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        save_btn = view.findViewById(R.id.save_button);
        try {
            save_btn.setOnClickListener(view1 -> {
                SavePoll savePoll = new SavePoll();

            });
        }
        catch (Exception e){
            Toast.makeText(getContext(), "error: " + e, Toast.LENGTH_SHORT).show();
        }
    }
}