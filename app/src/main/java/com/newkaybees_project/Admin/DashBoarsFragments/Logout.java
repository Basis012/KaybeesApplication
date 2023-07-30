package com.newkaybees_project.Admin.DashBoarsFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.newkaybees_project.R;
import com.newkaybees_project.Screens.Signin_Screen;

public class Logout extends Fragment {
Button btn;
FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_logout, container, false);
       btn = view.findViewById(R.id.btn);
       firebaseAuth = FirebaseAuth.getInstance();
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               firebaseAuth.signOut();
               Intent i = new Intent(getActivity(),Signin_Screen.class);
               i.putExtra("some","some data");
               startActivity(i);
           }
       });
       return view;
    }


}