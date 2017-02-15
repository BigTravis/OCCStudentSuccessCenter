package edu.orangecoastcollege.cs273.kfrederick5tmorrissey1ischenck.occstudentsuccesscenter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * this activity displays the user's location and the location of the SSC
 */
public class LocationActivity extends NavDrawerActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int FINE_LOCATION_REQUEST_CODE = 101;
    private static final double SSC_LATITUDE = 33.669338;
    private static final double SSC_LONGITUDE = -117.910553;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location myLocation;
    private GoogleMap mMap;
    private boolean mIsSSCDisplayed = false;
    private MarkerOptions mMyLocationMarker;

    /**
     * Initializes all loaders
     * @param savedInstanceState last saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
        getLayoutInflater().inflate(R.layout.activity_location, contentFrameLayout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.locationMapFragment);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(1000);
    }

    /**
     * Initializes GoogleMap
     * @param googleMap google map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    /**
     * requests permissions and updates the user's location
     * @param bundle bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_REQUEST_CODE);
            return;
        }
        myLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LocationServices.FusedLocationApi
                .requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        if (myLocation != null)
            handleNewLocation(myLocation);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("OCC SSC", "Suspended connection from Google Play Services.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("OCC SSC", "Failed connection from Google Play Services.");
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    /**
     * clears the map and reloads it with user's new location and the SSC
     * @param newLocation new location
     */
    private void handleNewLocation(Location newLocation)
    {
        myLocation = newLocation;
        LatLng myCoordinate = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());


        mMap.clear();
        mMyLocationMarker = new MarkerOptions()
                .position(myCoordinate)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker));
        mMap.addMarker(mMyLocationMarker);

        LatLng coordinate = new LatLng(SSC_LATITUDE, SSC_LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(coordinate).title("Student Success Center"));

        if (!mIsSSCDisplayed) {
            CameraPosition cameraPosition =
                    new CameraPosition.Builder().target(coordinate).zoom(14.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.moveCamera(cameraUpdate);
            mIsSSCDisplayed = true;
        }
    }
}
