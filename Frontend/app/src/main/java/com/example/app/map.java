/**
 * @author Michael Less
 * This screen implements the google map API, allowing the user to view the map
 * and create their own pins with inputted Lat/Lng
 */

package com.example.app;

import androidx.fragment.app.FragmentActivity;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.app.databinding.ActivityMapBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    private Button addPin;
    private Button back;
    private EditText latitude;
    private EditText longitude;
    private EditText markerName;
    private TextView response;
    private ArrayList<JSONObject> pins = new ArrayList<>();


    /**
     * Creates the screen based off of the .xml file associated with the activity and adds logic for
     * things like button presses and other functions.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getReq();
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       back =findViewById(R.id.map_back);
       addPin = findViewById(R.id.map_add_pin);
       latitude=(EditText)findViewById(R.id.latitude_input);
       longitude=(EditText)findViewById(R.id.longitude_input);
       markerName=(EditText)findViewById(R.id.pin_name);
       response=(TextView)findViewById(R.id.add_pin_response);

       //getReq();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), activity_menu.class));
            }
        });

        addPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               postReq();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng jackTrice = new LatLng(42.013983, -93.635753);
        mMap.addMarker(new MarkerOptions().position(jackTrice).title("Jack Trice Stadium"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jackTrice,17f));

//        for (int i=0;i<=pins.size();i++){
//            JSONObject mapPin = new JSONObject();
//            mapPin= pins.get(i);
//            try {
//                double latitude2 = mapPin.getDouble("latitude");
//                double longitude2 = mapPin.getDouble("longitude");
//                String name = mapPin.getString("name");
//
//                LatLng newPin = new  LatLng(latitude2,longitude2);
//                mMap.addMarker(new MarkerOptions().position(newPin).title(name));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void postReq() {
        RequestQueue queue = Volley.newRequestQueue(map.this);
        //String url = "http://coms-309-013.class.las.iastate.edu:8080/login/register/";
        String url = "http://coms-309-013.class.las.iastate.edu:8080/pins/new";
//        JSONObject regDetails = new JSONObject();
        JSONObject mapPin = new JSONObject();
        try {
            mapPin.put("username", login.userInput);
            mapPin.put("latitude", latitude.getText().toString());
            mapPin.put("longitude", longitude.getText().toString());
            mapPin.put("pinName", markerName.getText().toString());
        } catch(JSONException e) {
            e.printStackTrace();
        }

        final String request = mapPin.toString();
//msgResponse.setText("Successfully created a new pin! Refresh!");
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String msgResponse) {
                try {
                    response.setText("Successfully created a new pin! Refresh!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                response.setText("Something went wrong!");
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

    private void getReq() {
        RequestQueue queue = Volley.newRequestQueue(map.this);

        String url = "http://coms-309-013.class.las.iastate.edu:8080/pins/" + login.userInput;

        JSONObject json = new JSONObject();
        final String requestBody = json.toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);
                        pins.add(responseObj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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