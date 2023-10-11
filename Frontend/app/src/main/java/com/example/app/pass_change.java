/**
 * @author Tyler Atkinson & Michael Less
 * This screen allows one to change their account's password
 */
package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class pass_change extends AppCompatActivity {

    private EditText pass1;
    private EditText pass2;
    private TextView msgResponse;

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_change);

        Button back = findViewById(R.id.back_button);
        Button confirm=findViewById(R.id.confirm_button);
        pass1=(EditText)findViewById(R.id.new_pass1);
        pass2=(EditText)findViewById(R.id.new_pass2);
        msgResponse=(TextView)findViewById(R.id.response);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AccountDetails.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass1.getText().toString().equals(pass2.getText().toString())){
                    int error = 0;

                    if (pass1.getText().toString().trim().length() <= 0) {
                        error += 1;
                    }
                    else if (pass2.getText().toString().trim().length() <= 0) {
                        error += 1;
                    }
                    if (error != 0) {
                        msgResponse.setText("Error: Haven't filled out all necessary fields");
                        error = 0;
                    }
                    else {
                        putReq();
                    }
                }
            }
        });
    }

    /**
     * Volley PUT request. This request grabs the account information from the login public JSONObject
     * "profile", removes the password line, and puts the new password inside to be sent to the database.
     */
    private void putReq() {
        RequestQueue queue = Volley.newRequestQueue(pass_change.this);

        String url = "http://coms-309-013.class.las.iastate.edu:8080/users/"+login.userInput;

        try {
            login.profile.remove("password");
            login.profile.put("password", pass1.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String jsonString = login.profile.toString();
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msgResponse.setText("You have successfully changed your password!");
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                msgResponse.setText("It looks like something went wrong. Please try again");
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() {
                return jsonString.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        queue.add(request);
    }
}