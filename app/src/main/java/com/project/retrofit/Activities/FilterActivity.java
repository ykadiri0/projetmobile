package com.project.retrofit.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.retrofit.Dao.JsonPlaceHolderApi;
import com.project.retrofit.List.ListFiltredActivity;
import com.project.retrofit.R;
import com.project.retrofit.model.Ville;
import com.project.retrofit.model.Zone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilterActivity extends AppCompatActivity {
    AutoCompleteTextView ville,zone;
    com.google.android.material.button.MaterialButton search;
    String text1,text2;
    String insertUrl = "http://10.0.2.2:8081/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(insertUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ville=findViewById(R.id.ville);
        zone=findViewById(R.id.zone);
        ArrayList<Object> villes = new ArrayList<Object>();
        ArrayList<String> ids = new ArrayList<String>();

        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                android.R.layout.simple_dropdown_item_1line, villes);
        ville.setAdapter(adapter);
        Call<ArrayList<Ville>> call = jsonPlaceHolderApi.allVille();
        call.enqueue(new Callback<ArrayList<Ville>>() {
            @Override
            public void onResponse(Call<ArrayList<Ville>> call, Response<ArrayList<Ville>> response) {
                System.out.println(response.body().toString());
                System.out.println(call.request().url());
                try {
                    JSONArray jsonObject = new JSONArray(new Gson().toJson(response.body()));
                    Object msg = jsonObject.getString(Integer.parseInt("0"));
                    System.out.println(msg);
                    System.out.println(jsonObject);
                    for(int i=0;i<jsonObject.length();i++){
                        JSONObject position=jsonObject.getJSONObject(i);
                        System.out.println( jsonObject.get(i));
                        double id=position.getDouble("id");
                        String name=position.getString("name");
                        ids.add(position.getString("id"));
                        villes.add(position.getString("name"));
                    }
                    System.out.println(ids);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(!response.isSuccessful()){

                    return;
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Ville>> call, Throwable t) {

            }

        });
        ville.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               System.out.println(ids.get(i));
               Toast.makeText(getApplicationContext(),(CharSequence) ids.get(i), Toast.LENGTH_LONG).show();
               ArrayList<Object> zones = new ArrayList<Object>();
               ArrayAdapter<Object> adapter1 = new ArrayAdapter<Object>(FilterActivity.this, android.R.layout.simple_dropdown_item_1line, zones);
               zone.setAdapter(adapter1);
               Call<ArrayList<Zone>> call = jsonPlaceHolderApi.allZone(Integer.valueOf(ids.get(i)));

               call.enqueue(new Callback<ArrayList<Zone>>() {
                   @Override
                   public void onResponse(Call<ArrayList<Zone>> call, Response<ArrayList<Zone>> response) {
                       //System.out.println(call.request().url());
                       try {
                           JSONArray jsonObject = new JSONArray(new Gson().toJson(response.body()));
                           Object msg = jsonObject.getString(Integer.parseInt("0"));
                           //System.out.println(msg);
                           //System.out.println(jsonObject);
                           for(int i=0;i<jsonObject.length();i++){
                               JSONObject position=jsonObject.getJSONObject(i);
                               //System.out.println( jsonObject.get(i));
                               zones.add(position.getString("name"));
                           }
                           System.out.println(zones);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                       if(!response.isSuccessful()){

                           return;
                       }
                   }
                   @Override
                   public void onFailure(Call<ArrayList<Zone>> call, Throwable t) {
                   }
               });

           }
       });
        search=findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //text1= String.valueOf(ville.getText());
                //text2= String.valueOf(zone.getText());
                //System.out.println(ville.getText()+" "+zone.getText());
                startActivity(new Intent(FilterActivity.this, ListFiltredActivity.class)
                        .putExtra("ville",ville.getText())
                        .putExtra("zone",zone.getText())


                );
            }
        });

    }

}