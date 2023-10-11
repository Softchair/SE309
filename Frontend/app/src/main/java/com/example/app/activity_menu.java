/**
 * @author Tyler Atkinson & Michael Less
 * This is the main menu screen. It displays different features that a user can access (depending
 * on their admin level).
 */

package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;

public class activity_menu extends AppCompatActivity {

    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button page1 = findViewById(R.id.button);
        Button page2 = findViewById(R.id.button2);
        Button page3 = findViewById(R.id.button3);
        Button page4 = findViewById(R.id.button4);
        Button page5 = findViewById(R.id.button5);
        Button page6 = findViewById(R.id.button6);
        Button page7=findViewById(R.id.button9);
        TextView response = findViewById(R.id.response);

        Button logout = findViewById(R.id.button_logout);

        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), roster.class));
            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), concessions.class));
            }
        });

        page3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), schedule.class));
            }
        });

        page4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), map.class));
                //startActivity(new Intent(view.getContext(), game_chat.class));

            }
        });

        page7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), game_chat.class));
                //startActivity(new Intent(view.getContext(), game_chat.class));

            }
        });

        page5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (login.profile.getString("permLv").equals("Admin") || login.profile.getString("permLv").equals("Vendor")
                    || login.profile.getString("permLv").equals("Information Maintainer") || login.profile.getString("permLv").equals("Moderator")) {
                        startActivity(new Intent(view.getContext(), admin_page.class));
                    }
                    else {
                        response.setText("You don't have the permissions to do that");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        page6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AccountDetails.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });


    }
}