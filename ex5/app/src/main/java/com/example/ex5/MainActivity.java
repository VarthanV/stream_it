package com.example.ex5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mp=new MediaPlayer();
       Button playBtn = (Button)findViewById(R.id.play_btn);
       Button pauseBtn = (Button)findViewById(R.id.pause_btn);
       TextView playStatusText = findViewById(R.id.play_status);
       TextView mText = findViewById(R.id.duration);

        try{
            //you can change the path, here path is external directory(e.g. sdcard)

            mp.setDataSource(Environment.getExternalStorageDirectory().getPath()+"/Music/main.mp3");
            mp.prepare();
        }catch(Exception e){e.printStackTrace();}

        playBtn.setOnClickListener(v -> {
            mp.start();
            playStatusText.setText("Playing.....");
            new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    mText.setText("seconds remaining: " + millisUntilFinished / 1000);
                    //here you can have your logic to set text to edittext
                }

                public void onFinish() {
                    mText.setText("done!");
                }

            }.start();
        });
        pauseBtn.setOnClickListener(v -> {
            mp.pause();
            playStatusText.setText("Paused....");
        });

    }
}