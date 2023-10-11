package com.example.app;

import static com.example.app.login.userInput;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class game_chat extends AppCompatActivity {

    Button back, send, connect;
    EditText message;
    TextView chat;


    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_chat);


        back = (Button) findViewById(R.id.back_button_gamechat);
        send = (Button) findViewById(R.id.button13);
        connect = (Button) findViewById(R.id.connect_button);
        message = (EditText) findViewById(R.id.gamechat_message_input);
        chat = (TextView) findViewById(R.id.chatroom_text);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), activity_menu.class));
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Draft[] drafts = {
                        new Draft_6455()
                };
                /**
                 * If running this on an android device, make sure it is on the same network as your
                 * computer, and change the ip address to that of your computer.
                 * If running on the emulator, you can use localhost.
                 */
                //URL + username
//                String w = "ws://10.0.2.2:8080/websocket/" + userInput;
               String w = "http://coms-309-013.class.las.iastate.edu:8080/chat/global/"+ login.userInput;


                try {
                    Log.d("Socket:", "Trying socket");
                    cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                        @Override
                        public void onMessage(String message) {
                            long millis = System.currentTimeMillis();
                            java.util.Date date = new java.util.Date(millis);
                            Log.d("", "run() returned: " + message);

                            String level = "";
                            try {
                                if (login.profile.getString("permLv").equals("Admin")) {
                                    level+="(ADMIN)";
                                }
                                if (login.profile.getString("permLv").equals("Maintainer")) {
                                    level+="(MAINTAINER)";
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String s = chat.getText().toString();
                            chat.setText(s + "\n" +level+ message+"\n"+ date);
                        }

                        @Override
                        public void onOpen(ServerHandshake handshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("CLOSE", "onClose() returned: " + reason);
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
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(message.getText().toString());
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });
    }
}