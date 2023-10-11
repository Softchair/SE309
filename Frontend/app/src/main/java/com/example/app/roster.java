/**
 * @author Michael Less
 *  This screen is a view of all player objects in the roster array.
 *  It shows this by creating text boxes for each player's first, last and number
 */

package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class roster extends AppCompatActivity {

    private JSONArray players = new JSONArray();
    private ArrayList<TextView> firstName;
    private ArrayList<TextView> lastName;
    private ArrayList<TextView> numberList;
    private String id;
    private ConstraintLayout lay;
    private Button back;
    private Button edit;


    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);
        players=new JSONArray();
        firstName=new ArrayList<>();
        lastName=new ArrayList<>();
        numberList=new ArrayList<>();
        lay=findViewById(R.id.roster_layout);
        back = findViewById(R.id.back_button_roster);
        edit = findViewById(R.id.edit_roster_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), activity_menu.class));
            }
        });

        //TODO make only maintainers can edit
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), edit_roster.class));
            }
        });

        getPlayersReq();
    }


    /**
     * Volley GET request. This gets the information for the roster in the form of a JSON
     * ARRAY, it will store this in a specific array created earlier in the class
     * It also creates text boxes to display the data received
     */
    private void getPlayersReq() {
        RequestQueue queue = Volley.newRequestQueue(roster.this);


        String url = "http://coms-309-013.class.las.iastate.edu:8080/players";
        JSONObject json = new JSONObject();
        final String requestBody = json.toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //for all player objects, put it in response, then in the players array
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject responseObj=response.getJSONObject(i);
                        players.put(responseObj.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                int i;
                int y= 150;
                int x = 200;
                //int length= players.length();
                int length = 5;
                ConstraintSet con=new ConstraintSet();

                for (i=0;i<length*3;i+=3){
                    TextView first = (TextView) LayoutInflater.from(roster.this).inflate(R.layout.textview, null);
                    TextView last = (TextView) LayoutInflater.from(roster.this).inflate(R.layout.textview, null);
                    TextView number = (TextView) LayoutInflater.from(roster.this).inflate(R.layout.textview, null);
                    first.setId(i);
                    last.setId(i+1);
                    number.setId(i+2);

//            try{
//                first.setText(players.getJSONObject((i+1)/3).getString("firstName"));
//                last.setText(players.getJSONObject((i+1)/3).getString("lastName"));
//                number.setText(players.getJSONObject((i+1)/3).getString("number"));
//                String temp0 = players.getJSONObject((i+1)/3).getString("firstName");
//                String temp1 = players.getJSONObject((i+1)/3).getString("lastName");
//                String temp2 = players.getJSONObject((i+1)/3).getString("number");
                    first.setText("First Name: ");//+temp0);
                    last.setText("Last Name: ");//+temp1);
                    number.setText("Number: ");//+temp2);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }



                    firstName.add(first);
                    lastName.add(last);
                    numberList.add(number);
                }


                for(i=0;i<firstName.size();i++){
                    lay.addView(firstName.get(i));
                    lay.addView(lastName.get(i));
                    lay.addView(numberList.get(i));
                }

                for (i = 0; i < firstName.size(); i++) {
                    con.clone(lay);
                    con.connect(firstName.get(i).getId(), ConstraintSet.LEFT, R.id.roster_layout, ConstraintSet.LEFT, y);
                    con.connect(firstName.get(i).getId(), ConstraintSet.TOP, R.id.roster_layout, ConstraintSet.TOP, (x + (200 * i)));
                    con.applyTo(lay);
                    con.clone(lay);
                    con.connect(lastName.get(i).getId(), ConstraintSet.LEFT, R.id.roster_layout, ConstraintSet.LEFT, y);
                    con.connect(lastName.get(i).getId(), ConstraintSet.TOP, R.id.roster_layout, ConstraintSet.TOP, (x + 100 + (200 * i)));
                    con.applyTo(lay);
                    con.clone(lay);
                    con.connect(numberList.get(i).getId(), ConstraintSet.LEFT, R.id.roster_layout, ConstraintSet.LEFT, y);
                    con.connect(numberList.get(i).getId(), ConstraintSet.TOP, R.id.roster_layout, ConstraintSet.TOP, (x + 200 + (200 * i)));
                    con.applyTo(lay);
                    x+=100;
                }

                con.clone(lay);
                con.connect(back.getId(), ConstraintSet.RIGHT, R.id.roster_layout, ConstraintSet.RIGHT, y);
                con.connect(back.getId(), ConstraintSet.LEFT, R.id.roster_layout, ConstraintSet.LEFT, y);
                con.connect(back.getId(), ConstraintSet.TOP, numberList.get(length - 1).getId(), ConstraintSet.TOP, 200);
                con.applyTo(lay);
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