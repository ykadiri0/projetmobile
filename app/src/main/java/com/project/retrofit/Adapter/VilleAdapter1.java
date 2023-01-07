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

import com.bumptech.glide.Glide;
import com.project.retrofit.R;
import com.project.retrofit.model.Pharmacie;
import com.project.retrofit.model.PharmacieGard;

import java.util.List;

public class VilleAdapter1 extends ArrayAdapter<PharmacieGard>  {
    List<PharmacieGard> villes;
    Context context;
    public VilleAdapter1(@NonNull Context context, List<PharmacieGard> villes) {
        super(context, R.layout.activity_main_pg,villes);

        this.context=context;
        this.villes = villes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView name;
        TextView date_debut;
        TextView date_fin;
        TextView zone;
        ImageView image;

        View view = LayoutInflater.from(context).inflate(R.layout.item_pg,parent,false);
        name=view.findViewById(R.id.nom_ville);
        date_debut=view.findViewById(R.id.lat);
        date_fin=view.findViewById(R.id.fin);
        zone=view.findViewById(R.id.zone);
        image=view.findViewById(R.id.img);


        Glide.with(context).load(villes.get(position).getPharmacie().get(0).getPhoto().split("://")[0]+"s://"+villes.get(position).getPharmacie().get(0).getPhoto().split("://")[1]).into(image);
        name.setText(villes.get(position).getPharmacie().get(0).getName());
        date_debut.setText(villes.get(position).getDate_debut().toString());
        date_fin.setText(villes.get(position).getDate_fin().toString());
        zone.setText((CharSequence) villes.get(position).getPharmacie().get(0).getZone().getName());

        return view;
    }


}
