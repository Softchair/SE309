package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class add_friend extends AppCompatActivity {
    private Button add;
    private Button back;
    private TextView msgResponse;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        add = findViewById(R.id.add);
        back = findViewById(R.id.back);
        msgResponse = findViewById(R.id.msgResponse);
        name = findViewById(R.id.name);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(add_friend.this, friends_list.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putReq();
            }
        });
    }

    private void putReq() {
        RequestQueue queue = Volley.newRequestQueue(add_friend.this);
        String url = "http://coms-309-013.class.las.iastate.edu:8080/friend/";
        String end = "";
        try {
            end += login.profile.getString("username") + "/" + name.getText().toString();
        } catch(JSONException e) {
            e.printStackTrace();
        }
        url += end;
        final String request = end;
        StringRequest req = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msgResponse.setText("You have successfully added a new friend!");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                msgResponse.setText("Looks like something went wrong. Please try again");
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() {
                return request.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        queue.add(req);
    }
}
