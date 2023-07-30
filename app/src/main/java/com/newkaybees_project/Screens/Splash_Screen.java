package com.newkaybees_project.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.utils.Utils;
import com.newkaybees_project.Admin.AdminDashboard;
import com.newkaybees_project.MainActivity;
import com.newkaybees_project.R;
import com.newkaybees_project.UtilsFile;

public class Splash_Screen extends AppCompatActivity {
    private ImageView logo, logobottom;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
UtilsFile.blackIconStatusBar(Splash_Screen.this,R.color.lightbackground);
        text = findViewById(R.id.text);
        logo = findViewById(R.id.logo);
        getSupportActionBar().hide();
        logobottom = findViewById(R.id.logobottom);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_Screen.this,Signin_Screen.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Splash_Screen.this,
                        Pair.create(logo,"logo"),
                        Pair.create(logobottom,"img_tree"),
                        Pair.create(text,"logo_text"));
                startActivity(i,activityOptions.toBundle());


              /* if (getSharedPreferences()==true){
                    getSharedPreferences();
                     startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                     finish();
                }
                else {*/

                }

           // }
        },3000);
    }
  /*public boolean getSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefrences", Context.MODE_PRIVATE);
        boolean islogin = sharedPreferences.getBoolean("islogin",false);
        return islogin;
    }*/
}