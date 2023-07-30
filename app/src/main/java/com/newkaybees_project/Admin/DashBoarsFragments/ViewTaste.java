package com.newkaybees_project.Admin.DashBoarsFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newkaybees_project.Admin.Adapter.AdminAdapter;
import com.newkaybees_project.Admin.Model.TasteModel;
import com.newkaybees_project.R;

import java.util.ArrayList;
import java.util.Collections;

public class ViewTaste extends Fragment {

RecyclerView adminrecycle;
ArrayList<String> keylist = new ArrayList<String>();
ArrayList<TasteModel> mymodel = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_taste, container, false);
        adminrecycle = view.findViewById(R.id.adminrecycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adminrecycle.setLayoutManager(linearLayoutManager);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
       databaseReference.child("taste").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               mymodel.clear();
               keylist.clear();
               Log.e("data", String.valueOf(snapshot.getValue()));
               for (DataSnapshot dataSnapshot: snapshot.getChildren())
               {
                 keylist.add(dataSnapshot.getKey());
                   TasteModel tasteModel = dataSnapshot.getValue(TasteModel.class);
                   mymodel.add(tasteModel);
               }
               Collections.reverse(mymodel);
               Collections.reverse(keylist);
               AdminAdapter adminAdapter = new AdminAdapter(mymodel,getContext(),keylist);
               adminrecycle.setAdapter(adminAdapter);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

        return view;
    }
}