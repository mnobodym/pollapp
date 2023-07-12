package com.example.poolapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login {
    private static final String TAG = "login";
    private final String url = "http://127.0.0.1:8000/user/login/";
    private final String username;
    private final String password;
    private final Context context;
    private String result;
    private boolean login_auth = true;

    public Login(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
    }

    public void post_user() {
        Log.i(TAG, "here");
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
            Log.i(TAG, "onResponse: " + response.toString());
            result = response.toString();
            login_auth = true;
        }, error -> {
            Log.i(TAG, "onErrorResponse: " + error.getMessage());
            Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(jsonObjRequest);

    }

    public String getResult() {
        return result;
    }

    public boolean isLogin_auth() {
        return login_auth;
    }
}
