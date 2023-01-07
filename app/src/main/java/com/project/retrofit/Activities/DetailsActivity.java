package com.project.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.project.retrofit.R;

public class DetailsActivity extends AppCompatActivity {
TextView email,lat,lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        email =findViewById(R.id.email);
        lat =findViewById(R.id.lat);
        lng =findViewById(R.id.lng);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            String name =(String) bundle.get("name");
            Double latitude =(Double) bundle.get("lat");
            Double longitude =(Double) bundle.get("lng");
            email.setText(name);
            lat.setText(latitude.toString());
            lng.setText(longitude.toString());

        }
    }
}