package com.example.poolapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends Fragment {

    private static final String TAG = "LoginPage";

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

        try {
            login_btn.setOnClickListener(view1 -> {
                result_txtView.setText(R.string.loading);
                post_user(username_edt.getText().toString(), password_edt.getText().toString());
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "error: " + e, Toast.LENGTH_SHORT).show();
        }


    }

    public void post_user(String username, String password) {
        String url = "http://172.30.112.211/user/login/";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("message");
                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_login_page_to_home_page);
                } catch (Exception e) {
                    Log.e(TAG, "onResponse: ", e);
                    Toast.makeText(getContext(), "error: " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
                result_txtView.setText("username or password is not valid!");
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };
        requestQueue.add(request);
    }
}