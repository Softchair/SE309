/**
 * @author Tyler Atkinson
 * This screen allows one to choose whether to login or register their vendor profile
 */

package com.example.app.adminfeatures;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.AccountDetails;
import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.admin_page;
import com.example.app.login;
import com.example.app.register;

import org.json.JSONException;
import org.json.JSONObject;

public class vendor_info extends AppCompatActivity {
    private Button back;
    private Button login;
    private Button register;
    private EditText vendor_name;
    private EditText maintainer_username;
    public static JSONObject vendor;
    private TextView msgResponse;

    /**
     * Creates the vendor info screen based off of the .xml file that is associated with this activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_info);

        back = findViewById(R.id.back);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        vendor_name = findViewById(R.id.vendor_name);
        maintainer_username = findViewById(R.id.maintainer_username);
        msgResponse = findViewById(R.id.msgResponse);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), admin_page.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReq();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), vendor_register.class));
            }
        });
    }

    /**
     * Volley GET request. Gets the information from the database to add to a JSON object that is
     * accessed by the next screen to be able to display the information.
     */
    private void getReq() {
        vendor = new JSONObject();
        RequestQueue queue = Volley.newRequestQueue(vendor_info.this);
        String url = "http://coms-309-013.class.las.iastate.edu:8080/vendor/" + vendor_name.getText().toString();
        JSONObject json = new JSONObject();
        final String requestBody = json.toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("maintainerUsername").equals(maintainer_username.getText().toString())) {
                        vendor = response;
                        startActivity(new Intent(vendor_info.this, vendor_change.class));
                    }
                    else {
                        msgResponse.setText("This is not the correct maintainer username");
                    }
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