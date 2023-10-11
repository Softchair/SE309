/**
 * @author Michael Less
 *  This screen actually changes the information of a player on the backened
 *  given the ID from the previous page (roster_update)
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class roster_update_inputs extends AppCompatActivity {

    private TextView msgResponse;
    private EditText firstName;
    private EditText lastName;
    private EditText number;

    private String lastNameString;
    private String firstNameString;
    private String numberString;
    private JSONObject player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_update_inputs);

        Button back = findViewById(R.id.edit_roster_button);
        firstName=(EditText)findViewById(R.id.edit_firstName);
        lastName=(EditText)findViewById(R.id.edit_lastName);
        number=(EditText)findViewById(R.id.edit_number);
        Button submit = findViewById(R.id.edit_submit);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), roster_update.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putReq();
            }
        });

        getReq();




    }

    private void getReq() {
        RequestQueue queue = Volley.newRequestQueue(roster_update_inputs.this);


        String url = "http://coms-309-013.class.las.iastate.edu:8080/players/ID/"+roster_update.IDint;
        JSONObject json = new JSONObject();
        player=new JSONObject();
        final String requestBody = json.toString();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                    try {
                        player.put("firstName", response.getString("firstName"));
                        player.put("lastName", response.getString("lastName"));
                        player.put("number", response.getString("number"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    private void putReq() {
        RequestQueue queue = Volley.newRequestQueue(roster_update_inputs.this);

        String url = "http://coms-309-013.class.las.iastate.edu:8080/players/update/"+roster_update.IDint;

        try {
            player.remove("firstName");
            player.put("firstName", firstName.getText().toString());
            player.remove("lastName");
            player.put("lastName", lastName.getText().toString());
            player.remove("number");
            player.put("number", number.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String jsonString = player.toString();
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msgResponse.setText("You have successfully updated this player!");
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