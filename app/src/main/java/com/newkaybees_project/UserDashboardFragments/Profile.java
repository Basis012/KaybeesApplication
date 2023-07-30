package com.newkaybees_project.UserDashboardFragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.newkaybees_project.CheckOutModel.MyCheckOutModel;
import com.newkaybees_project.R;
import com.newkaybees_project.Screens.Signin_Screen;
import com.newkaybees_project.Signup_Model.ModelClass;

import java.util.ArrayList;

public class Profile extends Fragment {
    Button signout;
    TextView cname,cphonenumber, cemail;
    ImageView uploadimg;
    Button mybtn;
    private Uri imageUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Initialization(view);
        onClickListeners();
        sharedpreferences();
        return view;
    }

    private void sharedpreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("signupdata", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","failed");
        String email = sharedPreferences.getString("email","failed");
        String number = sharedPreferences.getString("number","failed");
        cname.setText(name);
        cphonenumber.setText(number);
        cemail.setText(email);
    }


    private void onClickListeners() {
        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
      signout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(getContext(),Signin_Screen.class));
              FirebaseAuth mAuth = FirebaseAuth.getInstance();
              mAuth.signOut();
          }
      });

    }

    private void Initialization(View view) {
        cname = view.findViewById(R.id.cname);
        uploadimg = view.findViewById(R.id.uploadimg);
        mybtn = view.findViewById(R.id.mybtn);
        cphonenumber = view.findViewById(R.id.cphonenumber);
        cemail = view.findViewById(R.id.cemail);
        signout = view.findViewById(R.id.signout);
    }

    private void choosePicture() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            uploadimg.setImageURI(imageUri);

        }
    }
}