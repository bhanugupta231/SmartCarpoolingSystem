package com.example.abc.smartcarpoolingsystem;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadDataActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button btnlogout,btnsave,btnview;
    private EditText etdepartureplace,etdestinationplace,etdate,ettime,etwhethercar,etnumber;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload_data);
        firebaseAuth=FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("data");


        init();
    }
    private void init(){
        btnlogout=(Button)findViewById(R.id.btnuserlogout);
        btnsave=(Button)findViewById(R.id.btnsave);
        btnview=(Button)findViewById(R.id.btnview);
        etdepartureplace=(EditText)findViewById(R.id.etdepartureplace);
        etdestinationplace=(EditText)findViewById(R.id.etdestinationplace);
        etdate=(EditText)findViewById(R.id.etdate);
        ettime=(EditText)findViewById(R.id.ettimeofdeparture);
        etwhethercar=(EditText)findViewById(R.id.etwhethercar);
        etnumber=(EditText)findViewById(R.id.etnumber);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String departureplace=etdepartureplace.getText().toString().trim();
                String arrivalplace=etdestinationplace.getText().toString().trim();
                String date=etdate.getText().toString().trim();
                String time=ettime.getText().toString().trim();
                String whethercar=etwhethercar.getText().toString().trim();
                String number=etnumber.getText().toString().trim();
                String id=databaseReference.push().getKey();
                if(TextUtils.isEmpty(departureplace)){
                    Toast.makeText(UploadDataActivity.this,"Please enter the departure place",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(arrivalplace)){
                    Toast.makeText(UploadDataActivity.this,"Please enter the destination place",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(date)){
                    Toast.makeText(UploadDataActivity.this,"Please enter the date",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(time)){
                    Toast.makeText(UploadDataActivity.this,"Please enter the time",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(whethercar)){
                    Toast.makeText(UploadDataActivity.this,"Please enter Yes/No",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(number)){
                    Toast.makeText(UploadDataActivity.this,"Please enter contact number",Toast.LENGTH_LONG).show();
                    return;
                }
                DataBean dataBean=new DataBean(id,departureplace,arrivalplace,date,time,whethercar,number);
                databaseReference.child(id).setValue(dataBean);

                Toast.makeText(UploadDataActivity.this,"saved",Toast.LENGTH_LONG).show();
                etdestinationplace.setText("");
                etdepartureplace.setText("");
                etdate.setText("");
                ettime.setText("");
                etwhethercar.setText("");
                etnumber.setText("");
            }
        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UploadDataActivity.this,ViewInformation.class);
                startActivity(i);
            }
        });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent i=new Intent(UploadDataActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }

}
