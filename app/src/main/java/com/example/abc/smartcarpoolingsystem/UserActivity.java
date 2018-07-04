package com.example.abc.smartcarpoolingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {
private Button btnuploaddata,btnviewdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btnuploaddata=(Button)findViewById(R.id.btnuploaddata);
        btnviewdata=(Button)findViewById(R.id.btnviewdata);
        btnuploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  finish();
                Intent i=new Intent(UserActivity.this,UploadDataActivity.class);
                startActivity(i);
            }
        });
        btnviewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent i=new Intent(UserActivity.this,ViewInformation.class);
                startActivity(i);
            }
        });
    }
}
