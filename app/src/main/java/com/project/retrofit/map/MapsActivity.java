package com.project.retrofit.map;


import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_BLUE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Callback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import android.location.LocationListener;

import com.project.retrofit.Dao.JsonPlaceHolderApi;
import com.project.retrofit.R;
import com.project.retrofit.databinding.ActivityMapsBinding;
import com.project.retrofit.model.Pharmacie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int METERS_100 = 8000;
    private GoogleApiClient googleClient;
    private Location lastLocation;
    private LocationRequest locationRequest;
    private Marker currentLocationMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    private GoogleMap mMap;
    LocationManager lm;
    public static ArrayList<Object> data = new ArrayList<>();

    private static final int INTERVAL = 1800;
    private static final int FASTEST_INTERVAL = 60000;

    private ActivityMapsBinding binding;
    String insertUrl = "http://10.0.2.2:8081/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(insertUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    protected synchronized void buildGoogleApiClient() {
        GoogleApiClient googleClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(LocationServices.API)
                .build();
        googleClient.connect();

    }

    @SuppressLint("MissingPermission")
    void startLocationUpdates(LocationManager lm) {
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, MapsActivity.this);

    }
    private BitmapDescriptor bitmapDescriptorFromVector (Context context, int vectorResId) {
        Drawable vectorDrawable =ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas =new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    public void onLocationChanged(Location location) {
        lastLocation = location;

        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }

        LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title("your current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(HUE_BLUE));

        currentLocationMarker = mMap.addMarker(markerOptions);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 16.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }

    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            return false;
        }
        return true;
    }

    public void onConnectionSuspended(int i) {

    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static double calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        float[] results = new float[3];
        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
        return results[0];
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        final String TAG = "addPosition";

        mMap = googleMap;
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            startLocationUpdates(lm);
        }

        Call<ArrayList<Pharmacie>> call = jsonPlaceHolderApi.allPositions();
        call.enqueue(new Callback<ArrayList<Pharmacie>>() {

            @Override
            public void onResponse(Call<ArrayList<Pharmacie>> call, Response<ArrayList<Pharmacie>> response) {
                System.out.println(response.body().toString());
                System.out.println(call.request().url());
                try {
                    JSONArray jsonObject = new JSONArray(new Gson().toJson(response.body()));
                    Object msg = jsonObject.getString(Integer.parseInt("0"));
                    System.out.println(msg);
                    System.out.println(jsonObject);
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Criteria criteria = new Criteria();
                    Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject position = jsonObject.getJSONObject(i);
                        System.out.println(jsonObject.get(i));
                        double lat = position.getDouble("lat");
                        double lng = position.getDouble("lon");
                        String name = position.getString("name");
                        data.add(jsonObject.get(i));
                        System.out.println("my location is");
                        System.out.println("helooo" + calculateDistance(12, 12, lat, lng));
                        if (calculateDistance(location.getLatitude(), location.getLongitude(), lat, lng) < METERS_100) {
                            MarkerOptions markerOptions  = new MarkerOptions().position(new
                                    LatLng(lat, lng)).title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                            mMap.addMarker(markerOptions );
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (!response.isSuccessful()) {
                    Log.d(TAG, response.code() + "");
                    return;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pharmacie>> call, Throwable t) {
                Log.d(TAG, t.getMessage());

            }


        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

        }


    }


}