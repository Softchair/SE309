/**
 * @author Michael Less
 * This screen is used to update the information of a player on the backend.
 * This is an intermediate screen to grab the ID of the player they want to
 * edit
 */

package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class roster_update extends AppCompatActivity {

    private EditText ID;
    public static String IDint;


    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster_update);

        Button back = findViewById(R.id.button7);
        Button edit=findViewById(R.id.button8);
        ID= (EditText) findViewById(R.id.player_ID);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), edit_roster.class));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDint=ID.getText().toString();
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });


        //   /players/update/{ID}
    }
}