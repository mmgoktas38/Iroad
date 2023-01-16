package com.kogo.iroad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kogo.iroad.databinding.ActivityMapsBinding;
import com.kogo.iroad.databinding.ActivitySharingBinding;

public class SharingActivity extends AppCompatActivity {

    private ActivitySharingBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySharingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        double locationLat=getIntent().getDoubleExtra("locationLat",0.0);
        double locationLng=getIntent().getDoubleExtra("locationLng",0.0);

        String currentTempC = getIntent().getStringExtra("currentTempC");
        String wind_kph = getIntent().getStringExtra("wind_kph");
        String humidity = getIntent().getStringExtra("humidity");
        String cloud = getIntent().getStringExtra("cloud");


        binding.editTextLocation.setText(locationLat+","+locationLng);
        binding.editTextTemp.setText(currentTempC);
        binding.editTextWind.setText(wind_kph);
        binding.editTextHumidity.setText(humidity);
        binding.editTextCloud.setText(cloud);

        binding.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String uri = "https://www.google.com/maps/search/?api=1&query="+locationLat+"%2C"+locationLng;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                if(binding.editTextAddNote.getText().toString().isEmpty())
                {
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,  "\t\t *--------INFORMATIONS--------* \n\n"+"*My Location :* " + Uri.parse(uri) + "\n*Current Temp °C :* " + currentTempC + "\n*Wind Kph :* " + wind_kph + "\n*Humadity :* " + humidity + "\n*Cloud :* "+ cloud);
                }
                else
                {
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "\t\t *--------INFORMATIONS--------* \n\n"+"*My Location :* " + Uri.parse(uri) + "\n*Current Temp °C :* " + currentTempC + "\n*Wind Kph :* " + wind_kph + "\n*Humadity :* " + humidity + "\n*Cloud :* "+ cloud+ "\n*Note !!! :* "+binding.editTextAddNote.getText().toString());
                }

                try {
                    startActivity(Intent.createChooser(sharingIntent, "Share in..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    // ToastHelper.MakeShortText("Whatsapp have not been installed.");
                }
                          }
        });

        binding.imageViewBack.setOnClickListener(view -> {

            onBackPressed();
        });


    }
}