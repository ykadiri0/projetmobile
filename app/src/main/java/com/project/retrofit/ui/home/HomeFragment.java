package com.project.retrofit.ui.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.project.retrofit.Adapter.VilleAdapter;
import com.project.retrofit.Controller.ApiController;
import com.project.retrofit.Activities.DetailsActivity;
import com.project.retrofit.map.MapsActivityPharmacy;
import com.project.retrofit.R;
import com.project.retrofit.databinding.FragmentHomeBinding;
import com.project.retrofit.model.Pharmacie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    VilleAdapter villeadapter;

    Button fab,fab2,fab3;
    ListView recyclerView;
    private FragmentHomeBinding binding;
    public static ArrayList<Pharmacie> data = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView= root.findViewById(R.id.rcv_ville);
        VilleAdapter adapter = new VilleAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        binding.rcvVille.setClickable(true);
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

                                startActivity(new Intent(getContext(),DetailsActivity.class)
                                        .putExtra("position",position)
                                        .putExtra("name",(data.get(position).getName()))
                                        .putExtra("lat",(data.get(position).getLat()))
                                        .putExtra("lng",(data.get(position).getLng()))
                                );

                                break;

                            case 1:
                                startActivity(new Intent(getContext(), MapsActivityPharmacy.class)
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


        //retrofit
        processData();
        return root;


    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void processData(){
        Call<List<Pharmacie>> call = ApiController.getInstance()
                                                .getapi()
                                                .getAllVilles();
        call.enqueue(new Callback<List<Pharmacie>>() {

            @Override
            public void onResponse(Call<List<Pharmacie>> call, Response<List<Pharmacie>> response) {
                data = (ArrayList<Pharmacie>) response.body();
                VilleAdapter adapter = new VilleAdapter(getContext(),data);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Pharmacie>> call, Throwable t) {
                Toast.makeText(getContext().getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();

            }
        });
     }
}