package com.newkaybees_project.UserDashboardFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newkaybees_project.Admin.Model.TasteModel;
import com.newkaybees_project.CartModel.AddToCartModel;
import com.newkaybees_project.R;
import com.newkaybees_project.Screens.Signin_Screen;
import com.newkaybees_project.UsersAdapter.CartItemsAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class MyCart extends Fragment {
RecyclerView cartrecycle;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
    ArrayList<String> keylist = new ArrayList<String>();
    ArrayList<AddToCartModel> addToCartModels = new ArrayList<>();
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
       Initialization(view);
        fetchCard();
       return view;
    }

    private void Initialization(View view) {
        cartrecycle = view.findViewById(R.id.cartrecycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        cartrecycle.setLayoutManager(linearLayoutManager);
    }

    public  void fetchCard(){
        String userId = FirebaseAuth.getInstance().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Add To Cart");
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("wait");
        progressDialog.setMessage("Food Fetching.........");
        progressDialog.setCancelable(false);
        progressDialog.show();
        databaseReference.orderByChild("userId").equalTo(userId).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                addToCartModels.clear();
                keylist.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    keylist.add(dataSnapshot.getKey());
                    AddToCartModel addToCartModel = dataSnapshot.getValue(AddToCartModel.class);
                    addToCartModels.add(addToCartModel);
                }
                Collections.reverse(addToCartModels);
                Collections.reverse(keylist);
                CartItemsAdapter cartItemsAdapter = new CartItemsAdapter(addToCartModels,keylist,getContext());
                cartrecycle.setAdapter(cartItemsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}