/**
 * @author Michael Less
 *  This screen is used to add games to the schedule
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

public class schedule_add extends AppCompatActivity {

    private Button submit;
    private EditText location;
    private EditText opponent;
    private EditText date;
    private TextView response;


    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_add);

        Button back=findViewById(R.id.schedule_add_back);
        response = (TextView)findViewById(R.id.msg_response2);
        location = (EditText)findViewById(R.id.schedule_add_location);
        opponent=(EditText)findViewById(R.id.schedule_add_opponent);
        date=(EditText)findViewById(R.id.schedule_add_date);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), schedule_edit.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int error = 0;

                if (location.getText().toString().trim().length() <= 0) {
                    error += 1;
                }
                else if (opponent.getText().toString().trim().length() <= 0) {
                    error += 1;
                }
                else if (date.getText().toString().trim().length() <= 0) {
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
     * This is a POST request to add the information inputted into a game object, then
     * submit it to the backend.
     */
    private void postReq() {
        RequestQueue queue = Volley.newRequestQueue(schedule_add.this);
        //TODO ADD CORRECT URL
        String url = "http://coms-309-013.class.las.iastate.edu:8080/schedule/new";
        JSONObject gameDetails = new JSONObject();
        try {
            gameDetails.put("opponent", opponent.getText().toString());
            gameDetails.put("location", location.getText().toString());
            gameDetails.put("date", date.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String request = gameDetails.toString();

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String msgResponse) {
                try {
                    response.setText("You have successfully created a new game!");
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