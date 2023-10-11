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
import com.example.app.R;

import org.json.JSONException;
import org.json.JSONObject;

public class add_food extends AppCompatActivity {

    private EditText foodId;
    private EditText calories;
    private EditText name;
    private EditText price;
    private EditText menuId;
    private Button back;
    private Button add;
    private TextView msgResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodId = findViewById(R.id.foodid);
        calories = findViewById(R.id.calories);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        menuId = findViewById(R.id.menuid);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        msgResponse = findViewById(R.id.msgResponse);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postReq();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(add_food.this, vendor_change.class));
            }
        });

    }

    private void postReq() {
        RequestQueue queue = Volley.newRequestQueue(add_food.this);
        String url = "http://coms-309-013.class.las.iastate.edu:8080/item/create/" + menuId.getText().toString();
        JSONObject regDetails = new JSONObject();
        try {
            regDetails.put("foodId", Integer.parseInt(foodId.getText().toString()));
            regDetails.put("cal", Integer.parseInt(calories.getText().toString()));
            regDetails.put("name", name.getText().toString());
            regDetails.put("price", (long) Double.parseDouble(price.getText().toString()));
            regDetails.put("menuId", Integer.parseInt(menuId.getText().toString()));
            regDetails.put("stock", 0);
        } catch(JSONException e) {
            e.printStackTrace();
        }

        final String request = regDetails.toString();

        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    msgResponse.setText("You have successfully added a new food item!");
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