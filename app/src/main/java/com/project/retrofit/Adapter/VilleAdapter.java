package com.project.retrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.retrofit.R;
import com.project.retrofit.model.Pharmacie;

import java.util.List;

public class VilleAdapter extends ArrayAdapter<Pharmacie> {
    List<Pharmacie> villes;
    Context context;

    public VilleAdapter(@NonNull Context context, List<Pharmacie> villes) {
        super(context, R.layout.fragment_home,villes);

        this.context=context;
        this.villes = villes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView lat;
        TextView ville;
        TextView v;
        ImageView image;


        View view = LayoutInflater.from(context).inflate(R.layout.item_ville,parent,false);
        ville=view.findViewById(R.id.nom_ville);
        lat=view.findViewById(R.id.lat);
        v=view.findViewById(R.id.ville);
image=view.findViewById(R.id.img);

        Glide.with(context).load(villes.get(position).getPhoto().split("://")[0]+"s://"+villes.get(position).getPhoto().split("://")[1]).into(image);
        ville.setText(villes.get(position).getName());
        lat.setText(villes.get(position).getZone().getName());
        v.setText(villes.get(position).getZone().getVille().getName());

        return view;
    }


}
