package com.example.contactsexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
private TextView splash_tv;
private ImageView splash_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();



    }
private  void setAnimation(){
    splash_tv=findViewById(R.id.splash_tv);
    splash_img=findViewById(R.id.splash_img);

    Animation animation= AnimationUtils.loadAnimation(this,R.anim.fade_in);

    splash_img.setAnimation(animation);
    splash_tv.setAnimation(animation);

    Timer timer=new Timer();
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    timer.schedule(timerTask,4000);
}
}
