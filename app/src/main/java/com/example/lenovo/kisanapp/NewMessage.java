package com.example.lenovo.kisanapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;

public class NewMessage extends AppCompatActivity {

    public static String EXTRA_NAME = "name";
    public static String EXTRA_CONTACT = "contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        //to get the name and contact info from previous activity

        Intent intent = getIntent();
        final String name = intent.getExtras().getString(EXTRA_NAME);
        final String contact = intent.getExtras().getString(EXTRA_CONTACT);

        //generating 6 digit random otp
        Random rand = new Random();
        int rand_int1 = 100000+rand.nextInt(900000);
        final String r = Integer.toString(rand_int1);

        //developing message
        final String msg = String.format("Hi! Your OTP is %s",r);
        ((TextView)findViewById(R.id.sms)).setText(msg);

        //on click of send message button
        findViewById(R.id.sendsms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //used textlocal messaging service to send messages
                    // Construct data
                    String apiKey = "apikey=" + "pJkQb/5w0n4-nEZ8lYF7KllqOifIwyXVmLjCIwRgJV";
                    String message = "&message=" + msg;
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + contact;
                    String test = "&test=true";
                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender + test;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);

                    }
                    Log.e("sms",stringBuffer.toString());
                    rd.close();


                    JSONObject j = new JSONObject(stringBuffer.toString());
                    String status = j.getString("status");
                    if(status.equals("success")) {
                        Toast.makeText(getApplicationContext(), "The message is sent", Toast.LENGTH_LONG).show();
                        DatabaseHandler handler = new DatabaseHandler(NewMessage.this);

                        //getting current time and date and providing to word
                        Timestamp ts = new Timestamp(System.currentTimeMillis());
                        SimpleDateFormat t = new SimpleDateFormat("HH:mm");
                        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
                        Word messge = new Word(name,contact,r,t.format(ts),d.format(ts));
                        handler.addMessage(messge);
                    }
                    Intent intent = new Intent(NewMessage.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                    //return stringBuffer.toString();
                } catch (Exception e) {
                   // System.out.println("Error SMS "+e);
                    //return "Error "+e;
                }
            }
        });

        //allow certain activities
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
