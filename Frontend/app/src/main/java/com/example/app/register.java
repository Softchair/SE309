/**
 * @author Tyler Atkinson
 * This screen allows a user to register a new profile based on the information that they provide.
 */

package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class register extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private TextView msgResponse;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText phone;

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.passwordText);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        register = (Button) findViewById(R.id.register);
        msgResponse = (TextView) findViewById(R.id.msgResponse);

        register.setOnClickListener(this);
    }

    /**
     * Checks for NULL strings by looking at string length.
     * @param v
     */
    @Override
    public void onClick(View v) {
        int error = 0;

        if (username.getText().toString().trim().length() <= 0) {
            error += 1;
        }
        else if (password.getText().toString().trim().length() <= 0) {
            error += 1;
        }
        else if (email.getText().toString().trim().length() <= 0) {
            error += 1;
        }
        else if (phone.getText().toString().trim().length() <= 0) {
            error += 1;
        }

        if (error != 0) {
            msgResponse.setText("Error: Haven't filled out all necessary fields");
            error = 0;
        }
        else {
            postReq();
        }
    }

    /**
     * Volley POST request. Takes the information provided in the EditText boxes and puts them into
     * a JSONObject that is sent up to the database to be stored as a new profile.
     */
    private void postReq() {
        RequestQueue queue = Volley.newRequestQueue(register.this);
//        String url = "https://26ee0a9a-f41e-41c7-9e14-e30c8ccd3267.mock.pstmn.io/register/";
        String url = "http://coms-309-013.class.las.iastate.edu:8080/login/register/";
        JSONObject regDetails = new JSONObject();
        try {
            regDetails.put("username", username.getText().toString());
            regDetails.put("password", password.getText().toString());
            regDetails.put("email", email.getText().toString());
            regDetails.put("phoneNum", phone.getText().toString());
            regDetails.put("permLv", "User");
        } catch(JSONException e) {
            e.printStackTrace();
        }

        final String request = regDetails.toString();

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   msgResponse.setText("You have successfully created a new user!");
                   final Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           startActivity(new Intent(register.this, MainActivity.class));
                       }
                   }, 2000);
               } catch( Exception e) {
                   e.printStackTrace();
               }
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