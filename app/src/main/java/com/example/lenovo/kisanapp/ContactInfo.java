package com.example.lenovo.kisanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
//display contact name and phone no
public class ContactInfo extends AppCompatActivity {

    public static String EXTRA_NAME = "name";
    public static String EXTRA_CONTACT = "contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        //to get the name and contact info from previous activity
        Intent intent = getIntent();
        final String name = intent.getExtras().getString(EXTRA_NAME);
        final String contact = intent.getExtras().getString(EXTRA_CONTACT);

        //assigning values
        ((TextView)findViewById(R.id.name)).setText(name);
        ((TextView)findViewById(R.id.contact_no)).setText(contact);
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactInfo.this,NewMessage.class);

                //variable extra earlier declared to send to next activity

                intent.putExtra(NewMessage.EXTRA_NAME,name);
                intent.putExtra(NewMessage.EXTRA_CONTACT,contact);
                startActivity(intent);
                finish();
            }
        });

    }
}
