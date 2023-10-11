package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class friends_list extends AppCompatActivity {
    private ArrayList<Button> buttons;
    private Button back;
    private ArrayList<JSONObject> friends = new ArrayList<>();
    public static String friend_name;
    private ConstraintLayout lin;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        buttons = new ArrayList<>();
        back = (Button) findViewById(R.id.back);
        getReq();

        lin = findViewById(R.id.friends);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(friends_list.this, add_friend.class));
            }
        });
    }

    private void getReq() {
        RequestQueue queue = Volley.newRequestQueue(friends_list.this);
        String url = "http://coms-309-013.class.las.iastate.edu:8080/get/friend/";
        String end = "";
        try {
            end += login.profile.getString("username");
        } catch (JSONException e) {e.printStackTrace();}
        url += end;
        final String requestBody = end;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);
                        friends.add(responseObj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                int y = 400;
                int x = 300;
                int i;
                int count = friends.size();
                ConstraintSet constraint = new ConstraintSet();

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(friends_list.this, AccountDetails.class));
                    }
                });

                for (i = 0; i < count; i++) {
                    Button n = (Button) LayoutInflater.from(friends_list.this).inflate(R.layout.button, null);
                    n.setId(i + 1);
                    n.setTag(i + 1);
                    try {
                        String[] index = friends.get(i).getString("friendShip").split("\\+" + login.profile.getString("username"));
                        String a = "";
                        if (index.length == 0) {
                            a = index[0];
                        }
                        else {
                            for (int j = 0; j < index.length; j++) {
                                a += index[j];
                            }
                        }
                        n.setText(a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    n.setTextColor(getResources().getColor(R.color.white));
                    n.setBackgroundColor(getResources().getColor(R.color.purple_500));
                    n.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            friend_name = n.getText().toString();
                            startActivity(new Intent(friends_list.this, private_message.class));
                        }
                    });
                    buttons.add(n);
                }

                if (count != 0) {
                    for (i = 0; i < count; i++) {
                        lin.addView(buttons.get(i));
                    }
                }

                for (i = 0; i < count; i++) {
                    constraint.clone(lin);
                    constraint.connect(buttons.get(i).getId(), ConstraintSet.RIGHT, R.id.concessions, ConstraintSet.RIGHT, y);
                    constraint.connect(buttons.get(i).getId(), ConstraintSet.LEFT, R.id.concessions, ConstraintSet.LEFT, y);
                    constraint.connect(buttons.get(i).getId(), ConstraintSet.TOP, R.id.concessions, ConstraintSet.TOP, (x + (200 * i)));
                    constraint.applyTo(lin);
                }

                constraint.clone(lin);
                constraint.connect(back.getId(), ConstraintSet.RIGHT, R.id.concessions, ConstraintSet.RIGHT, y);
                constraint.connect(back.getId(), ConstraintSet.LEFT, R.id.concessions, ConstraintSet.LEFT, y);
                constraint.connect(back.getId(), ConstraintSet.TOP, buttons.get(count - 1).getId(), ConstraintSet.TOP, 200);
                constraint.applyTo(lin);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public byte[] getBody() {
                return requestBody.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        queue.add(request);
    }
}