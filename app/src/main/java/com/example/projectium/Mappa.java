package com.example.projectium;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

import static com.example.projectium.Home.PERSON_DA_PASSARE_2;
import static com.example.projectium.Login.PERSON_DA_PASSARE;

public class Mappa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button back;
    public Persona persona;
    Intent intent;
    final int requestCode=100;


    public static final String NOME_CAMPO_DA_PASSARE = "package com.example.projectium.mappa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        back = findViewById(R.id.button_return_mappa);


        final Intent intent = getIntent();

        Serializable obj = intent.getSerializableExtra(PERSON_DA_PASSARE_2);

        if (obj instanceof Persona) {
            persona = (Persona) obj;
        } else {
            persona = new Persona();
        }


/*
        if (ContextCompat.checkSelfPermission(Mappa.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Mappa.this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(Mappa.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(Mappa.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHome = new Intent(Mappa.this, Home.class);
                //Inserisco la persona dentro l'intent

                //Inserisco la persona dentro l'intent
                //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
                showHome.putExtra(PERSON_DA_PASSARE, persona);
                //richiamo activity


                //richiamo activity
                startActivity(showHome);
                finish();
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap; // Inizializzazione mappa

        mMap.setMyLocationEnabled(true);

        // ----------------------------------------------------
        // Chiede a Run time il permesso per la posizione
        // ----------------------------------------------------

        // Fattore Zoom mappa ( 0 = Tutto il mondo -> 21 sara un metro per schermata)
        float zoomLevel = 13.5f; //This goes up to 21

        LatLng bonariaLocation = new LatLng(39.209651,9.126720);
        Marker bonaria = mMap.addMarker(new MarkerOptions().position(bonariaLocation).title("Bonaria").snippet("Campi Bonaria"));

        LatLng ossigenoLocation = new LatLng(39.212714,9.124624);
        Marker ossigeno = mMap.addMarker(new MarkerOptions().position(ossigenoLocation).title("Ossigeno").snippet("Campi Ossigeno"));

        LatLng montesantoLocation = new LatLng(39.236863,9.102035);
        Marker montesanto = mMap.addMarker(new MarkerOptions().position(montesantoLocation).title("Monte Santo").snippet("Campi Monte Santo"));

        LatLng terrapienoLocation = new LatLng(39.219123,9.117656);
        Marker terrapieno = mMap.addMarker(new MarkerOptions().position(terrapienoLocation).title("Terrapieno").snippet("Campi Terrapieno"));

        LatLng argonneLocation = new LatLng(39.233013,9.105147);
        Marker argonne = mMap.addMarker(new MarkerOptions().position(argonneLocation).title("Argonne").snippet("Campi Argonne"));

        LatLng biasiLocation = new LatLng(39.233054,9.123792);
        Marker biasi = mMap.addMarker(new MarkerOptions().position(biasiLocation).title("Biasi").snippet("Campi Via Biasi"));

        LatLng giovanniLocation = new LatLng(39.228087,9.125191);
        Marker giovanni = mMap.addMarker(new MarkerOptions().position(giovanniLocation).title("Giovanni XXIII").snippet("ampi Piazza Giovanni XXIII"));



        //Zoom Mappa Centrato sulla posizione del device al momento dell'apertura della pagina

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(39.223843,9.121661) , zoomLevel));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                // Da qua prendo il dato del marker e lo passo nellìintend, posso passare gio che voglio, anche un ID volendo
                String nomeCampo = marker.getTitle();


                AlertDialog diaBox = AskOption(nomeCampo);
                diaBox.show();


            }
        });



        // ----------------------------------------------------------------------------------------------------------------------------
        //TODO questo listener permette di inserire un marker tenendo premuto sulla mappa (in caso vogliamo mettere la posizione da account Admin )
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Your marker title")
                        .snippet("Your marker snippet"));
            }
        });
        // ----------------------------------------------------------------------------------------------------------------------------
    }

    @Override
    public void onBackPressed() {
        Intent showHome = new Intent(Mappa.this, Home.class);
        //Inserisco la persona dentro l'intent

        //Inserisco la persona dentro l'intent
        //ATTENZIONE ho messo person_da_passare e NON person_da_passare2 perchè il login va a pescare sul primo
        showHome.putExtra(PERSON_DA_PASSARE, persona);
        //richiamo activity


        //richiamo activity
        startActivity(showHome);
        finish();
    }

    private AlertDialog AskOption(final String marker)
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // finestra di conferma eliminazione
                .setTitle("Cosa vuoi fare?")
                .setMessage("Scegli se creare o partecipare a una partita ")


                .setPositiveButton("Nuova partita", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        Intent showField = new Intent(Mappa.this, Nuova_partita_da_mappa.class);
                        showField.putExtra(NOME_CAMPO_DA_PASSARE, marker);
                        showField.putExtra(PERSON_DA_PASSARE,persona);

                        startActivity(showField);
                        dialog.dismiss();
                        finish();
                    }

                })
                .setNegativeButton("Partecipa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String nomeCampo = marker;
                        Intent showField = new Intent(Mappa.this, Partecipa_partita.class);
                        showField.putExtra(NOME_CAMPO_DA_PASSARE, nomeCampo);
                        showField.putExtra(PERSON_DA_PASSARE,persona);

                        startActivity(showField);
                        dialog.dismiss();
                        finish();

                    }
                })
                .create();



        return myQuittingDialogBox;
    }

}

