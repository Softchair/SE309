/**
 * @author Tyler Atkinson
 * This activity allows one to grant another user admin permissions of different levels if one
 * has the correct permissions.
 */

package com.example.app.adminfeatures;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.AccountDetails;
import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.activity_menu;
import com.example.app.admin_page;
import com.example.app.login;
import com.example.app.pass_change;
import com.example.app.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class grant_admin extends AppCompatActivity {

    private Spinner privileges;
    private Button grant_privileges;
    private TextView msgResponse;
    private EditText username;
    private Button back;
    private JSONObject info;
    private String user;
    private String perms;
    private RequestQueue queue;

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grant_admin);
        info = new JSONObject();
        queue = Volley.newRequestQueue(grant_admin.this);

        privileges = (Spinner) findViewById(R.id.privileges);
        msgResponse = (TextView) findViewById(R.id.msgResponse);
        username = (EditText) findViewById(R.id.username);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.privileges, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        privileges.setAdapter(adapter);

        grant_privileges = (Button) findViewById(R.id.grant_privileges);
        back = (Button) findViewById(R.id.back);

        grant_privileges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (login.profile.getString("permLv").equals("Admin")) {
                        putReq();
                    }
                    else {
                        msgResponse.setText("You do not have the permission to do this");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(grant_admin.this, admin_page.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Volley PUT request for granting admin permissions. Calls getReq() to get the information
     * needed from the database to change it and send it back to the database.
     */
    private void putReq() {
        perms = privileges.getSelectedItem().toString();
        user = username.getText().toString();
        String url = "http://coms-309-013.class.las.iastate.edu:8080/users/"+user;

        getReq();

        try {
            info.remove("permLv");
            info.put("permLv", perms);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String jsonString = info.toString();
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msgResponse.setText("You have successfully granted permissions!");
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

    /**
     * Volley GET request. Gets information from the database as a JSON object to be used by putReq().
     */
    private void getReq() {
        String url = "http://coms-309-013.class.las.iastate.edu:8080/users/" + user;
        JSONObject json = new JSONObject();
        try {
            json.put("username", user);
            json.put("permLv", perms);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = json.toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    info.put("username", response.getString("username"));
                    info.put("phoneNum", response.getString("phoneNum"));
                    info.put("email", response.getString("email"));
                    info.put("password", response.getString("password"));
                    info.put("permLv", response.getString("permLv"));
                } catch (JSONException e) {e.printStackTrace();}
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
}