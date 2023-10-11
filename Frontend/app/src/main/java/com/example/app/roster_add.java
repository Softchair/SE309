/**
 * @author Michael Less
 * This class is used for adding new players into the database for the roster
 *
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class roster_add extends AppCompatActivity {

    private Button submit;
    private EditText firstName;
    private EditText lastName;
    private EditText number;
    private TextView response;


    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_add);

        response = (TextView)findViewById(R.id.response_msg);
        Button back = findViewById(R.id.roster_add_back);
        lastName = (EditText)findViewById(R.id.input_postion);
        number = (EditText)findViewById(R.id.input_number);
        firstName = (EditText)findViewById(R.id.input_name);
        submit = (Button)findViewById(R.id.roster_add_submit);

        //submit.setOnClickListener(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), edit_roster.class));
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int error = 0;

                if (firstName.getText().toString().trim().length() <= 0) {
                    error += 1;
                }
                else if (lastName.getText().toString().trim().length() <= 0) {
                    error += 1;
                }
                else if (number.getText().toString().trim().length() <= 0) {
                    error += 1;
                }

                if (error != 0) {
                    response.setText("Error: Left fields empty!");
                    error = 0;
                }
                else {
                    postReq();
                }
            }
        });


    }

    /**
     * Volley POST request that will take the inputted first name, last name, and number
     * and post a new player to the backend
     */
        private void postReq() {
            RequestQueue queue = Volley.newRequestQueue(roster_add.this);
            String url = "http://coms-309-013.class.las.iastate.edu:8080/players/new";
            JSONObject playerDetails = new JSONObject();
            try {
                playerDetails.put("firstName", firstName.getText().toString());
                playerDetails.put("lastName", lastName.getText().toString());
                playerDetails.put("number", number.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final String request = playerDetails.toString();

            StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String msgResponse) {
                    try {
                        response.setText("You have successfully created a new player!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    response.setText("Looks like something went wrong. Please try again");
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
