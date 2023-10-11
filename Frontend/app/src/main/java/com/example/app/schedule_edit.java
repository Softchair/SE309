/**
 * @author Michael Less
 *  This screen is used to direct the user to either deleting or adding a game to
 *  the schedule
 *
 */

package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class schedule_edit extends AppCompatActivity {


    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);


        Button back = findViewById(R.id.button7);
        Button add= findViewById(R.id.schedule_add);
        Button delete=findViewById(R.id.schedule_delete);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), schedule.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), schedule_add.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), schedule_delete.class));
            }
        });

    }
}