package com.arashad96.andoriddeveloperadvancedkit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Automatic_Message_sender extends AppCompatActivity {
    Button github;
    Button info;
    Button send_sms;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_automatic__message_sender);

        number = findViewById(R.id.number);
        send_sms = findViewById(R.id.send_sms);

        //Check and request permission to send sms
        if (ContextCompat.checkSelfPermission(Automatic_Message_sender.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Automatic_Message_sender.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        } else {
            //permission granted
            send_sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!number.getText().toString().equals("")) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(number.getText().toString(), null, "HERE IS THE MESSAGE CONTENT", null, null);
                        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Automatic_Message_sender.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        github = findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ARashad96/Automatic_Message_sender"));
                startActivity(intent);
            }
        });
        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new android.app.AlertDialog.Builder(Automatic_Message_sender.this)
                        .setIcon(R.drawable.profile)
                        .setTitle("App info")
                        .setMessage("This app is performing an automated message using textview, sms, button, toast, edittext and linearlayout.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }
}
