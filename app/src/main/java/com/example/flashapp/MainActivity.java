package com.example.flashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Boolean powerState = false;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn = findViewById(R.id.imageButton);

        TextView textView = findViewById(R.id.textView);

        http http = new http(textView);
        http.execute();


        btn.setOnClickListener(
                new View.OnClickListener(){
                   @Override
                   public void onClick(View view) {
                       if(powerState) {
                           powerState = false;
                           btn.setImageResource(R.drawable.power_off);
                           try {
                               controlFlash(powerState);
                           } catch (CameraAccessException e) {
                               throw new RuntimeException(e);
                           }
                       }else{

                           powerState = true;
                           btn.setImageResource(R.drawable.power_on);
                           try {
                               controlFlash(powerState);
                           } catch (CameraAccessException e) {
                               throw new RuntimeException(e);
                           }
                       }
                   }
                }
        );

    }

    public void controlFlash(Boolean mode) throws CameraAccessException {

        CameraManager cameraM = (CameraManager)getSystemService(Context.CAMERA_SERVICE);

        String cameraIDs = cameraM.getCameraIdList()[0];

        cameraM.setTorchMode(cameraIDs, mode);

    }

}