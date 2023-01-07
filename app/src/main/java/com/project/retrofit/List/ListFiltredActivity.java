package com.project.retrofit.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.project.retrofit.Dao.JsonPlaceHolderApi;
import com.project.retrofit.Activities.DetailsActivity;
import com.project.retrofit.Adapter.VilleAdapter;
import com.project.retrofit.R;
import com.project.retrofit.map.MapsActivityPharmacy;
import com.project.retrofit.model.Pharmacie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListFiltredActivity extends AppCompatActivity {
    ListView recyclerView;
    TextView lat;
    TextView ville;
    public static ArrayList<Pharmacie> data = new ArrayList<>();
    String insertUrl = "http://10.0.2.2:8081/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(insertUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_filtred);
        //recyclerView = findViewById(R.id.rcv_ville);
        recyclerView = findViewById(R.id.rcv_ville);

        VilleAdapter adapter = new VilleAdapter(this, data);
        recyclerView.setAdapter(adapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Call<ArrayList<Pharmacie>> call = jsonPlaceHolderApi.allPositions(bundle.get("zone").toString());
        call.enqueue(new Callback<ArrayList<Pharmacie>>() {

            @Override
            public void onResponse(Call<ArrayList<Pharmacie>> call, Response<ArrayList<Pharmacie>> response) {

                VilleAdapter adapter = new VilleAdapter(ListFiltredActivity.this, response.body());
                recyclerView.setAdapter(adapter);
                data=response.body();


                /* JSONArray jsonObject = new JSONArray(new Gson().toJson(response.body()));
                 Object msg = jsonObject.getString(Integer.parseInt("0"));

                 for (int i = 0; i < jsonObject.length(); i++) {
                     JSONObject position = jsonObject.getJSONObject(i);
                     JSONObject jo = position.getJSONObject("zone");
                     JSONObject city = jo.getJSONObject("ville");


                     if (jo.getString("name").equals(bundle.get("zone").toString()) && city.getString("name").equals(bundle.get("ville").toString())) {
                         data = (ArrayList<Pharmacie>) response.body();
                         VilleAdapter adapter = new VilleAdapter(ListFiltredActivity.this, data);
                         recyclerView.setAdapter(adapter);
                     } else {
                         adapter.clear();
                     }


                 }*/
                if (!response.isSuccessful()) {
                    return;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pharmacie>> call, Throwable t) {

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

                builder.setTitle(data.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){

                            case 0:

                                startActivity(new Intent(ListFiltredActivity.this, DetailsActivity.class)
                                        .putExtra("position",position)
                                        .putExtra("name",(data.get(position).getName()))
                                        .putExtra("lat",(data.get(position).getLat()))
                                        .putExtra("lng",(data.get(position).getLng()))
                                );

                                break;

                            case 1:
                                startActivity(new Intent(ListFiltredActivity.this, MapsActivityPharmacy.class)
                                        .putExtra("position",position)
                                        .putExtra("name",(data.get(position).getName()))
                                        .putExtra("lat",(data.get(position).getLat()))
                                        .putExtra("lng",(data.get(position).getLng()))
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