package com.newkaybees_project.Admin.DashBoarsFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newkaybees_project.CartModel.AddToCartModel;
import com.newkaybees_project.PendingOrderAdapter.PendingAdapter;
import com.newkaybees_project.R;

import java.util.ArrayList;
import java.util.Collections;

public class PendingOrder extends Fragment {
    RecyclerView pendingrecycle;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
  ArrayList<AddToCartModel> pendingmodel = new ArrayList<>();
  ArrayList<String> keys = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_order, container, false);
        pendingrecycle = view.findViewById(R.id.pendingrecycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        pendingrecycle.setLayoutManager(linearLayoutManager);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Add To Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pendingmodel.clear();
                keys.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    keys.add(dataSnapshot.getKey());
                    AddToCartModel addToCartModel = dataSnapshot.getValue(AddToCartModel.class);
                    pendingmodel.add(addToCartModel);
                }
                Collections.reverse(pendingmodel);
                Collections.reverse(keys);
                PendingAdapter pendingAdapter = new PendingAdapter(pendingmodel,keys,getContext());
                pendingrecycle.setAdapter(pendingAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}