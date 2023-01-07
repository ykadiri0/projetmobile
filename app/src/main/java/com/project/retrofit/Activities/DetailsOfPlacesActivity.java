package com.project.retrofit.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.project.retrofit.R;

public class DetailsOfPlacesActivity implements GoogleMap.InfoWindowAdapter{
    private Context context;
    public DetailsOfPlacesActivity(Context context) {
        this.context = context.getApplicationContext();
    }
    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    @Override
    public View getInfoContents(Marker arg0) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.custommarker, null);

        LatLng latLng = arg0.getPosition();
        ImageView image=v.findViewById(R.id.img);

        TextView tvLat = (TextView) v.findViewById(R.id.lat);
        TextView tvLng = (TextView) v.findViewById(R.id.lng);
        Glide.with(context).load("https://media.istockphoto.com/id/1325914490/fr/photo/pharmacie-moderne-pharmacie-avec-des-%C3%A9tag%C3%A8res-pleines-de-paquets-pleins-de-m%C3%A9decine-moderne.jpg?s=612x612&w=0&k=20&c=fQvAXkOAt5rbVdQZtpZ0daX6dzaKgHUA90JN2xi5hgQ=").into(image);

        tvLat.setText("Latitude:" + latLng.latitude);
        tvLng.setText("Longitude:"+ latLng.longitude);
        return v;
    }
}
