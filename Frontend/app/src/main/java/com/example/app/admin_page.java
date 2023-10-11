/**
 * @author Tyler Atkinson
 * This screen displays options for those with varying levels of admin permissions. This page also
 * displays the user's current admin permission level.
 */
package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.adminfeatures.grant_admin;
import com.example.app.adminfeatures.vendor_info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class admin_page extends AppCompatActivity {

    private Button grant_admin;
    private Button back;
    private Button vendor_info;
    private Button schedule_info;
    private Button player_info;
    private TextView response;
    private TextView admin_level;

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        grant_admin = (Button) findViewById(R.id.grant_admin);
        admin_level = (TextView) findViewById(R.id.adminlevel);
        back = (Button) findViewById(R.id.back);
        vendor_info = (Button) findViewById(R.id.change_vendor);
        schedule_info = (Button) findViewById(R.id.change_schedule);
        player_info = (Button) findViewById(R.id.change_player);
        response = (TextView) findViewById(R.id.response);

        try { admin_level.setText("Current admin level: " + login.profile.getString("permLv")); }
        catch (JSONException e) { e.printStackTrace(); }

        grant_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (login.profile.getString("permLv").equals("Admin")) {
                        Intent intent = new Intent(admin_page.this, grant_admin.class);
                        startActivity(intent);
                    }
                    else {
                        response.setText("You don't have the right permission level");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        vendor_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (login.profile.getString("permLv").equals("Vendor") || login.profile.getString("permLv").equals("Admin")) {
                        Intent intent = new Intent(admin_page.this, vendor_info.class);
                        startActivity(intent);
                    }
                    else {
                        response.setText("You don't have the right permission level");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        schedule_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (login.profile.getString("permLv").equals("Information Maintainer") || login.profile.getString("permLb").equals("Admin")) {
                        Intent intent = new Intent(admin_page.this, activity_menu.class); //placeholder for when schedules are made
                        startActivity(intent);
                    }
                    else {
                        response.setText("You don't have the right permission level");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        player_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (login.profile.getString("permLv").equals("Information Maintainer") || login.profile.getString("permLb").equals("Admin")) {
                        Intent intent = new Intent(admin_page.this, activity_menu.class); //placeholder for when players are made
                        startActivity(intent);
                    }
                    else {
                        response.setText("You don't have the right permission level");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_page.this, activity_menu.class);
                startActivity(intent);
            }
        });
    }
}