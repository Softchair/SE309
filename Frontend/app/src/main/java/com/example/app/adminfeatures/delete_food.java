package com.example.app.adminfeatures;

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
import com.example.app.R;

import org.json.JSONException;
import org.json.JSONObject;

public class delete_food extends AppCompatActivity {
    private EditText foodId;
    private EditText menuId;
    private TextView msgResponse;
    private Button back;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food);

        foodId = findViewById(R.id.foodid);
        menuId = findViewById(R.id.menuid);
        msgResponse = findViewById(R.id.msgResponse);
        back = findViewById(R.id.back);
        delete = findViewById(R.id.delete);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(delete_food.this, vendor_change.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReq();
            }
        });
    }

    private void deleteReq() {
        RequestQueue queue = Volley.newRequestQueue(delete_food.this);
        String url = "http://coms-309-013.class.las.iastate.edu:8080/item/delete/" + foodId.getText().toString();
        JSONObject json = new JSONObject();
        final String requestBody = json.toString();
        StringRequest req = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    msgResponse.setText("You have successfully deleted a food item!");
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
                return requestBody.getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        queue.add(req);
    }
}