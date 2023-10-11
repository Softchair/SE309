/**
 * @author Tyler Atkinson & Michael Less
 * This screen allows one to view their account information and can choose to change their password
 * which will take them to a new screen.
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

public class AccountDetails extends AppCompatActivity {

    //private TextView msgResponse;
    private TextView username;
    private TextView password;
    private TextView number;
    private TextView email;
    private Button friends;

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        username = (TextView) findViewById(R.id.name_here);
        number = (TextView) findViewById(R.id.number_here);
        email = (TextView) findViewById(R.id.email_here);
        password = (TextView) findViewById(R.id.password_here);
        Button back = findViewById((R.id.back_menu));
        Button change_pass = findViewById((R.id.button_change_pass));
        friends = findViewById(R.id.friends);

        getReq();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), activity_menu.class));
            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), pass_change.class));
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), friends_list.class));
            }
        });
    }

    /**
     * Volley GET request. This method gets the user's information from the database and sets the
     * text boxes to display the information on the screen.
     */
    private void getReq() {
        RequestQueue queue = Volley.newRequestQueue(AccountDetails.this);

//        String url = "https://26ee0a9a-f41e-41c7-9e14-e30c8ccd3267.mock.pstmn.io/object/";
        String url = "http://coms-309-013.class.las.iastate.edu:8080/users/" + login.userInput;
        JSONObject json = new JSONObject();
        final String requestBody = json.toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    username.setText(response.getString("username"));
                    number.setText(response.getString("phoneNum"));
                    email.setText(response.getString("email"));
                    password.setText(response.getString("password"));
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
}