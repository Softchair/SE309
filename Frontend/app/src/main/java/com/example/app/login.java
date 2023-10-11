/**
 * @author Tyler Atkinson
 * This screen allows a user to login if they have an existing account.
 */

package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private Button login;
    private TextView msgResponse;
    private EditText username;
    private EditText password;
    private Button back;
    public static JSONObject profile;

    public static String userInput;
    public static String passInput;

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login);
        msgResponse = (TextView) findViewById(R.id.msgResponse);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReq();
//                Intent intent = new Intent(login.this, activity_menu.class);
//                startActivity(intent);
            }
        });
    }

    /**
     * Volley GET request. This method pulls the information of a user's profile from the database
     * and stores it in a public state JSONObject to be used later, as well as takes the user to the
     * next screen if their profile exists.
     */
    private void getReq() {
        RequestQueue queue = Volley.newRequestQueue(login.this);

        userInput = username.getText().toString();
        passInput = password.getText().toString();

//        String url = "https://26ee0a9a-f41e-41c7-9e14-e30c8ccd3267.mock.pstmn.io/object/";
        String url = "http://coms-309-013.class.las.iastate.edu:8080/users/" + userInput;
        JSONObject json = new JSONObject();
        profile = new JSONObject();
        try {
            json.put("username", userInput);
            json.put("password", passInput);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = json.toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String correctUser = response.getString("username");
                    String correctPass = response.getString("password");
                    if (correctUser.equals(userInput) && correctPass.equals(passInput)) {
                        String email = response.getString("email");
                        String phone = response.getString("phoneNum");
                        String perms = response.getString("permLv");
                        try {
                            profile.put("username", correctUser);
                            profile.put("password", correctPass);
                            profile.put("email", email);
                            profile.put("phoneNum", phone);
                            profile.put("permLv", perms);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(login.this, activity_menu.class);
                        startActivity(intent);
                    }
                    else {
                        msgResponse.setText("Username or Password is wrong");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //msgResponse.setText(error.toString());
                msgResponse.setText("Username or Password is wrong");
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
