package com.newkaybees_project.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.newkaybees_project.Admin.AdminDashboard;
import com.newkaybees_project.Consumer.UserDashboard;
import com.newkaybees_project.R;
import com.newkaybees_project.UtilsFile;

import soup.neumorphism.NeumorphButton;

public class Signin_Screen extends AppCompatActivity {
TextInputEditText email, password;
FirebaseAuth firebaseAuth;
NeumorphButton signinButton;
    LinearLayout mylayout;
    private Animation animation;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(getApplicationContext(),UserDashboard.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_screen);
        getSupportActionBar().hide();
        Initialization();
        onClickListeners();
    }

    private void onClickListeners() {
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                if (Email.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,"Please enter your email",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (!Email.contains("@gmail.com")){
                    Snackbar snackbar = Snackbar.make(view,"Please correct your email",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (Password.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,"Please enter your password",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (Password.length()<6){
                    Snackbar snackbar = Snackbar.make(view,"Your password length is less than 6 ",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (Email.equals("admin@gmail.com") && Password.equals("1234567")){
                    //savedData();
                    startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                    finish();
                    Snackbar snackbar = Snackbar.make(view,"Done ",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),UserDashboard.class));
                                finish();
                                Snackbar snackbar = Snackbar.make(view,"Dashboard page",Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar snackbar = Snackbar.make(view,e.getMessage().toString(),Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    });
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mylayout.setVisibility(View.VISIBLE);
                mylayout.setAnimation(animation);
            }
        },1000);



    }

    private void Initialization() {
        mylayout = findViewById(R.id.mylayout);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        email.addTextChangedListener(loginwacher);
        password.addTextChangedListener(loginwacher);
        firebaseAuth = FirebaseAuth.getInstance();
        signinButton = findViewById(R.id.signinButton);
        UtilsFile.blackIconStatusBar(Signin_Screen.this,R.color.lightbackground);
        animation = AnimationUtils.loadAnimation(Signin_Screen.this,R.anim.animationapp);
    }

    public void gotoSignup(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Screen.class));
        finish();
    }

    public void signinBtn(View view) {
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }
   private TextWatcher loginwacher = new PhoneNumberFormattingTextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            signinButton.setEnabled(!Email.equals("") && !Password.equals(""));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}