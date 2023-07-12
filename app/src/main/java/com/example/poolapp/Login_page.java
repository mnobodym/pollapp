package com.example.poolapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_page extends Fragment {

    Button login_btn;
    EditText username_edt, password_edt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login_btn = view.findViewById(R.id.buttonLogin);
        username_edt = view.findViewById(R.id.editeTextUsername);
        password_edt = view.findViewById(R.id.editTextPassword);

        login_btn.setOnClickListener(view1 -> {
            Login login = new Login(getContext(), username_edt.getText().toString(), password_edt.getText().toString());
            login.post_user();
            Toast.makeText(getContext(), login.getResult(), Toast.LENGTH_SHORT).show();
        });
    }
}