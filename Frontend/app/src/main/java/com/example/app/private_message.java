package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;

public class private_message extends AppCompatActivity {
    private Button back;
    private TextView title;
    private TextView chat;
    private Button send;
    private EditText message;
//
    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_message);
        back = findViewById(R.id.back);
        title = findViewById(R.id.textView39);
        chat = findViewById(R.id.chat);
        send = findViewById(R.id.send);
        message = findViewById(R.id.message);
        String url = "";

        title.setText(friends_list.friend_name);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(private_message.this, friends_list.class));
            }
        });

        try {
            url = "http://coms-309-013.class.las.iastate.edu:8080/dm/" + login.profile.getString("username") + "/" + friends_list.friend_name;
        } catch (JSONException e) {e.printStackTrace();}

        Draft[] drafts = {
                new Draft_6455()
        };

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(url), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s = chat.getText().toString();
                    chat.setText(s + "\n" + message);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                    String s = chat.getText().toString();
                    chat.setText(s + "\nConnected");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                    String s = chat.getText().toString();
                    chat.setText(s + "\nDisconnected");
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Date date = new java.util.Date();
                cc.send(message.getText().toString() + "\n" + date);

                message.setText("");
            }
        });

    }
}