package com.newkaybees_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newkaybees_project.CartModel.AddToCartModel;
import com.newkaybees_project.CheckOutModel.MyCheckOutModel;

import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;

public class CheckOutActivity extends AppCompatActivity {
RecyclerView checkoutrecycle;
  NeumorphButton checkoutbtn;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
//ArrayList<AddToCartModel> addToCartModels = new ArrayList<>();
ArrayList<String> keys = new ArrayList<>();
TextInputEditText address, phonenumber;
    AddToCartModel addToCartModel;
    TextView name, price1, quantity1,subtotal;
    TextView totalprice1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        getSupportActionBar().hide();
        address = findViewById(R.id.address);
        name = findViewById(R.id.name);
        price1 = findViewById(R.id.price);
        totalprice1 = findViewById(R.id.totalprice);
        phonenumber = findViewById(R.id.phonenumber);
        quantity1 = findViewById(R.id.quantity);
        checkoutbtn = findViewById(R.id.checkoutbtn);
        Intent data = getIntent();
       String title = data.getStringExtra("title");
       Integer totalprice =  data.getIntExtra("totalprice",0);
       String quantity =  data.getStringExtra("quantity");
       name.setText(title);
       price1.setText("RS: "+String.valueOf(totalprice));
       totalprice1.setText("RS: "+String.valueOf(totalprice));
       quantity1.setText(quantity);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Address = address.getText().toString();
                String PhoneNumber = phonenumber.getText().toString();
                MyCheckOutModel myCheckOutModel = new MyCheckOutModel(Address,PhoneNumber);
                databaseReference.child("CheckoutDetails").push().setValue(myCheckOutModel);
                address.setText("");
                phonenumber.setText("");
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Add To Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                keys.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    keys.add(dataSnapshot.getKey());
                  addToCartModel = dataSnapshot.getValue(AddToCartModel.class);
                    //addToCartModels.add(addToCartModel);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CheckOutActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}