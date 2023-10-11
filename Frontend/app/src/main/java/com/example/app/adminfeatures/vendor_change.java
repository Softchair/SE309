/**
 * @author Tyler Atkinson
 * This screen allows a vendor to choose options such as adding new information/menu items and changing
 * existing information/items.
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.MainActivity;
import com.example.app.adminfeatures.vendor_info;

import com.example.app.R;
import com.example.app.login;
import com.example.app.pass_change;
import com.example.app.register;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class vendor_change extends AppCompatActivity {

    private Button create;
    private Button add;
    private Button delete;
    private Button back;
    private TextView msgResponse;
    Random rand = new Random();
    int id;
    private RequestQueue queue;

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_change);
        queue = Volley.newRequestQueue(vendor_change.this);

        create = findViewById(R.id.create);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.back);
        msgResponse = findViewById(R.id.msgResponse);
        id = rand.nextInt(1000000000);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postReq();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), add_food.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), delete_food.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), vendor_info.class));
            }
        });
    }

    private void postReq() {
        String url = "http://coms-309-013.class.las.iastate.edu:8080/menu/create";
        String request1 = "?";
        try {
            request1 += "menuName=" + vendor_info.vendor.getString("name");
            request1 += "&menuId=" + id;
            request1 += "&menuDesc=description";
            request1 += "&vendorId=" + vendor_info.vendor.getString("vendorId");
        } catch(JSONException e) {
            e.printStackTrace();
        }
        final String request = request1;
        url += request;

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                putReq(); //currently does not work
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                msgResponse.setText("Looks like something went wrong");
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

    private void putReq() {
        String url = "http://coms-309-013.class.las.iastate.edu:8080/vendor/saveMenu/";
        JSONObject ven = new JSONObject();
        String end = "";
        try {
            end = vendor_info.vendor.getString("name") + "/" + id;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        url += end;
        final String jsonString = end;
        StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                msgResponse.setText("You have successfully created a menu! ID = " + id);
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