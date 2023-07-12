package com.example.poolapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_page extends Fragment {

    Button login_btn;
    EditText username_edt, password_edt;
    TextView result_txtView;

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
        result_txtView = view.findViewById(R.id.textView2);

        login_btn.setOnClickListener(view1 -> {
            result_txtView.setText(R.string.loading);
            Login login = new Login(getContext(), username_edt.getText().toString(), password_edt.getText().toString());
            login.post_user();
            if (login.isLogin_auth())
                Navigation.findNavController(view1).navigate(R.id.action_login_page_to_home_page);
            else
                result_txtView.setText(login.getResult());
        });
    }
}