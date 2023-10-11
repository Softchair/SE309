/**
 * @author Michael Less
 *   This screen gives a view of all the games in the schdule
 *
 *
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class schedule extends AppCompatActivity {

    private JSONArray games = new JSONArray();
    private ArrayList<TextView> locationList;
    private ArrayList<TextView> opponentList;
    private ArrayList<TextView> dateList;
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
        setContentView(R.layout.activity_schedule);
        games=new JSONArray();
        locationList=new ArrayList<>();
        opponentList = new ArrayList<>();
        dateList=new ArrayList<>();
        lay=findViewById(R.id.schedule_layout);

        back = findViewById(R.id.schedule_back);
        edit = findViewById(R.id.schedule_edit);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), activity_menu.class));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), schedule_edit.class));
            }
        });

        getGamesReq();
    }

    //SHOW SCHEDULE HERE

    /**
     * Volley GET request. This requests the schedule JSONArray from the backend. and then
     * puts it into a seperate array, then displays that Array by dynamically creating
     * TextViews
     */
    private void getGamesReq() {
        RequestQueue queue = Volley.newRequestQueue(schedule.this);

        String url = "http://coms-309-013.class.las.iastate.edu:8080/schedule";
        JSONObject json = new JSONObject();
        final String requestBody = json.toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //for all player objects, put it in response, then in the players array
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject responseObj=response.getJSONObject(i);
                        games.put(responseObj.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                int i;
                int y= 150;
                int x = 200;
                //int length= games.length();
                int length = 5;
                ConstraintSet con=new ConstraintSet();

                for (i=0;i<length*3;i+=3){
                    TextView opponent= (TextView) LayoutInflater.from(schedule.this).inflate(R.layout.textview, null);
                    TextView date = (TextView) LayoutInflater.from(schedule.this).inflate(R.layout.textview, null);
                    TextView location = (TextView) LayoutInflater.from(schedule.this).inflate(R.layout.textview, null);
                    opponent.setId(i);
                    date.setId(i+1);
                    location.setId(i+2);

//            try{
//                first.setText(players.getJSONObject((i+1)/3).getString("firstName"));
//                last.setText(players.getJSONObject((i+1)/3).getString("lastName"));
//                number.setText(players.getJSONObject((i+1)/3).getString("number"));
//                String temp0 = games.getJSONObject((i+1)/3).getString("opponent");
//                String temp1 = games.getJSONObject((i+1)/3).getString("date");
//                String temp2 = games.getJSONObject((i+1)/3).getString("location");
                    opponent.setText("Opponent: ");//+temp0);
                    date.setText("Date: ");//+temp1);
                    location.setText("Location: ");//+temp2);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }



                    opponentList.add(opponent);
                    dateList.add(date);
                    locationList.add(location);
                }


                for(i=0;i<opponentList.size();i++){
                    lay.addView(opponentList.get(i));
                    lay.addView(dateList.get(i));
                    lay.addView(locationList.get(i));
                }

                for (i = 0; i < opponentList.size(); i++) {
                    con.clone(lay);
                    con.connect(opponentList.get(i).getId(), ConstraintSet.LEFT, R.id.schedule_layout, ConstraintSet.LEFT, y);
                    con.connect(opponentList.get(i).getId(), ConstraintSet.TOP, R.id.schedule_layout, ConstraintSet.TOP, (x + (200 * i)));
                    con.applyTo(lay);
                    con.clone(lay);
                    con.connect(dateList.get(i).getId(), ConstraintSet.LEFT, R.id.schedule_layout, ConstraintSet.LEFT, y);
                    con.connect(dateList.get(i).getId(), ConstraintSet.TOP, R.id.schedule_layout, ConstraintSet.TOP, (x + 100 + (200 * i)));
                    con.applyTo(lay);
                    con.clone(lay);
                    con.connect(locationList.get(i).getId(), ConstraintSet.LEFT, R.id.schedule_layout, ConstraintSet.LEFT, y);
                    con.connect(locationList.get(i).getId(), ConstraintSet.TOP, R.id.schedule_layout, ConstraintSet.TOP, (x + 200 + (200 * i)));
                    con.applyTo(lay);
                    x+=100;
                }

                con.clone(lay);
                con.connect(back.getId(), ConstraintSet.RIGHT, R.id.schedule_layout, ConstraintSet.RIGHT, y);
                con.connect(back.getId(), ConstraintSet.LEFT, R.id.schedule_layout, ConstraintSet.LEFT, y);
                con.connect(back.getId(), ConstraintSet.TOP, locationList.get(length - 1).getId(), ConstraintSet.TOP, 200);
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