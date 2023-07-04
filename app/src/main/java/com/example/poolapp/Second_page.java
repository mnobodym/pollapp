package com.example.poolapp;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Second_page extends Fragment {

    ArrayList<String> inputs;
    RecyclerView inputsRecycleView;
    Second_page_Adapter recycleViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputs = getArguments().getStringArrayList("inputs");
        inputsRecycleView = view.findViewById(R.id.itemRecy);
        recycleViewAdapter = new Second_page_Adapter(getContext(), inputs);

        inputsRecycleView.setAdapter(recycleViewAdapter);
        inputsRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}