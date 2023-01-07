package com.project.retrofit.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.retrofit.Dao.JsonPlaceHolderApi;
import com.project.retrofit.Adapter.VilleAdapter1;
import com.project.retrofit.R;
import com.project.retrofit.map.MapsActivityPharmacy;
import com.project.retrofit.model.PharmacieGard;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPG extends AppCompatActivity {
    ListView recyclerView;
    TextView lat;
    TextView ville;
    int id;
    public static ArrayList<PharmacieGard> data = new ArrayList<>();
    String insertUrl = "http://192.168.10.148:8081/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(insertUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pg);
        //recyclerView = findViewById(R.id.rcv_ville);
        recyclerView = findViewById(R.id.rcv_ville);
        final AutoCompleteTextView textView =findViewById(R.id.garde);
        ArrayList<Object> villes = new ArrayList<Object>();
        villes.add("Jour");
        villes.add("Nuit");
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                android.R.layout.simple_dropdown_item_1line, villes);
        textView.setAdapter(adapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),(CharSequence) villes.get(i), Toast.LENGTH_LONG).show();
                if (villes.get(i).equals("Jour")){
                    id=1;
                }
                else{
                    id=2;
                }
                Call<ArrayList<PharmacieGard>> call = jsonPlaceHolderApi.allPGbygard(id);
                call.enqueue(new Callback<ArrayList<PharmacieGard>>() {
                    @Override
                    public void onResponse(Call<ArrayList<PharmacieGard>> call, Response<ArrayList<PharmacieGard>> response) {
                        System.out.println(call.request().url());
                        System.out.println(response.body());
                        if(response.body()!=null){
                            for (PharmacieGard e : response.body()) {
                                System.out.println( e.getGard().get(0).getType());
                            }
                            try {
                                data = (ArrayList<PharmacieGard>) response.body();
                                VilleAdapter1 adapter = new VilleAdapter1(MainActivityPG.this, data);
                                recyclerView.setAdapter(adapter);
                            /*for (int i = 0; i < jsonObject.length(); i++) {
                                JSONObject position = jsonObject.getJSONObject(i);
                                System.out.println(jsonObject.get(i));
                                JSONArray jo = position.getJSONArray("gard");
                                System.out.println(jo.getString(0));
                                System.out.println(jo.getString(0));*/


                                /*if (jo.getString(0).equals(villes.get(i))) {
                                    data = (ArrayList<PharmacieGard>) response.body();
                                    VilleAdapter1 adapter = new VilleAdapter1(MainActivityPG.this, data);
                                    recyclerView.setAdapter(adapter);

                                } else {
                                    adapter.clear();
                                }*/


                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ArrayList<PharmacieGard>> call, Throwable t) {

                    }


                });

            }
        });



        recyclerView.setClickable(true);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                builder.setIcon(R.drawable.ic_direction);
                CharSequence[] dialogItem = {"View Pharmacy","Pharmacy Localisation"};

                builder.setTitle(data.get(position).getPharmacie().get(0).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){

                            case 0:

                                startActivity(new Intent(MainActivityPG.this, DetailsActivity.class)
                                        .putExtra("position",position)
                                        .putExtra("name",(data.get(position).getPharmacie().get(0).getName()))
                                        .putExtra("lat",(data.get(position).getPharmacie().get(0).getLat()))
                                        .putExtra("lng",(data.get(position).getPharmacie().get(0).getLng()))
                                );

                                break;

                            case 1:
                                startActivity(new Intent(MainActivityPG.this, MapsActivityPharmacy.class)
                                        .putExtra("position",position)
                                        .putExtra("name",(data.get(position).getPharmacie().get(0).getName()))
                                        .putExtra("lat",(data.get(position).getPharmacie().get(0).getLat()))
                                        .putExtra("lng",(data.get(position).getPharmacie().get(0).getLng()))
                                );

                                break;

                            case 2:


                                break;
                        }
                    }
                });


                builder.create().show();


            }
        });





    }

}