package com.newkaybees_project.Screens;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.newkaybees_project.R;
import com.newkaybees_project.Signup_Model.ModelClass;
import com.newkaybees_project.UtilsFile;

import java.util.HashMap;
import java.util.Map;

public class Signup_Screen extends AppCompatActivity {
TextInputEditText txt1, txt2, txt3, txt4, txt5;
DatabaseReference databaseReference;
FirebaseAuth firebaseAuth;
FirebaseFirestore firebaseFirestore;
LinearLayout mylayout;
private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        getSupportActionBar().hide();
        Initialization();
        onClickListeners();
        firebaseInstances();
    }

    private void firebaseInstances() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Kaybees Account");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void onClickListeners() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mylayout.setVisibility(View.VISIBLE);
                mylayout.setAnimation(animation);
            }
        },1000);


    }

    private void Initialization() {
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        mylayout = findViewById(R.id.mylayout);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        UtilsFile.blackIconStatusBar(Signup_Screen.this,R.color.lightbackground);
        animation = AnimationUtils.loadAnimation(Signup_Screen.this,R.anim.animationapp);
    }

    public void signupBtn(View view) {
        String name = txt1.getText().toString();
        String email = txt2.getText().toString();
        String phonenumber = txt3.getText().toString();
        String password = txt4.getText().toString();
        String confirmpassword = txt5.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("signupdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name);
        editor.putString("email",email);
        editor.putString("number",phonenumber);
        editor.apply();
        if (name.isEmpty()){
            Snackbar snackbar = Snackbar.make(view,"Please enter your name",Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        else if (email.isEmpty()){
            Snackbar snackbar = Snackbar.make(view,"Please enter your email",Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        else if (!email.contains("@gmail.com")){
            Snackbar snackbar = Snackbar.make(view,"Please correct your email",Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        else if (phonenumber.isEmpty()){
            Snackbar snackbar = Snackbar.make(view,"Please enter your phone number",Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        else if (password.isEmpty()){
            Snackbar snackbar = Snackbar.make(view,"please enter your password ",Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        else if (password.length()<6){
            Snackbar snackbar = Snackbar.make(view,"your password length is less than 6 ",Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        else if (!password.equals(confirmpassword)){
            Snackbar snackbar = Snackbar.make(view,"Your password is incorrect",Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        else if (
                confirmpassword.equals(password)){
            Snackbar snackbar = Snackbar.make(view,"Done",Snackbar.LENGTH_LONG);
            snackbar.show();
            DocumentReference documentReference = firebaseFirestore.collection("Kaybees Account").document();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        ModelClass modelClass = new ModelClass(name,email,phonenumber,password,confirmpassword);
                        databaseReference.push().setValue(modelClass);
                        Map<String,Object> myvalue = new HashMap<>();
                        myvalue.put("Name",name);
                        myvalue.put("Email",email);
                        myvalue.put("Phone number",phonenumber);
                        myvalue.put("Password",password);
                        myvalue.put("Confirm password",confirmpassword);
                        documentReference.set(myvalue);
                        Snackbar snackbar = Snackbar.make(view,"Your user created successfully",Snackbar.LENGTH_LONG);
                        snackbar.show();
                        txt1.setText("");
                        txt2.setText("");
                        txt3.setText("");
                        txt4.setText("");
                        txt5.setText("");
                        startActivity(new Intent(getApplicationContext(),Signin_Screen.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar snackbar = Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            });
        }
    }

    public void gotosingnin(View view) {
        startActivity(new Intent(getApplicationContext(),Signin_Screen.class));
        finish();
    }
}