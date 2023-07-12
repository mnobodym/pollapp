package com.example.poolapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateFieldsPage extends Fragment {

    private static final String TAG = "First_page";

    Button add_btn, go_btn;
    EditText input_indexes_edtxt;
    ListView list_listview;
    ArrayList<String> inputs;
    ArrayAdapter<String> adapterOfListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_fields_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add_btn = view.findViewById(R.id.addBtn);
        go_btn = view.findViewById(R.id.goBtn);
        input_indexes_edtxt = view.findViewById(R.id.textBox);
        list_listview = view.findViewById(R.id.listViewXML);
        inputs = new ArrayList<>();
        adapterOfListView = new ArrayAdapter<>(getContext(), R.layout.fragment_field_adapter_item, R.id.nameTextView, inputs);

        list_listview.setAdapter(adapterOfListView);

        add_btn.setOnClickListener(view1 -> {
            if (input_indexes_edtxt.getText().toString().equals("")) {
                Toast.makeText(getContext(), "Insert one field in Box", Toast.LENGTH_SHORT).show();
                return;
            }

            String input = input_indexes_edtxt.getText().toString();
            for (int i = 0; i < inputs.size(); i++) {
                if (input.equalsIgnoreCase(inputs.get(i))) {
                    Toast.makeText(getContext(), "this input in exist.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            inputs.add(input);
            addToShardPerformance(input);
            adapterOfListView.notifyDataSetChanged();
            input_indexes_edtxt.getText().clear();
        });

        go_btn.setOnClickListener(view12 -> {
            if (inputs.size() <= 1) {
                Toast.makeText(getContext(), "Enter two or more field...", Toast.LENGTH_SHORT).show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("inputs", inputs);
            Navigation.findNavController(view12).navigate(R.id.action_first_page_to_second_page, bundle);
        });
    }

    private void addToShardPerformance(String s) {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("saveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString(s, s);
        editor.putInt(s, 0);
        editor.apply();
    }
}