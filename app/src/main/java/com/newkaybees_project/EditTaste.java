package com.newkaybees_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newkaybees_project.Admin.Model.TasteModel;

public class EditTaste extends AppCompatActivity {
    TextInputEditText tastename, tastedescription, tasteprice, discountedprice, tastequantity;
    MaterialButton uploadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_taste);
        uploadBtn = findViewById(R.id.uploadBtn);
        tastename = findViewById(R.id.tastename);
        tastedescription =findViewById(R.id.tastedescription);
        tasteprice = findViewById(R.id.tasteprice);
        discountedprice =findViewById(R.id.discountedprice);
        tastequantity = findViewById(R.id.tastequantity);
        String  Name = getIntent().getExtras().getString("name");
        String  Price = getIntent().getExtras().getString("price");
        String  DiscountedPrice = getIntent().getExtras().getString("discountedprice");
        String  Description = getIntent().getExtras().getString("description");
        String  Quantity = getIntent().getExtras().getString("quantity");
        String  Key = getIntent().getExtras().getString("key");
        tastename.setText(Name);
        tastedescription.setText(Description);
        tasteprice.setText(Price);
        discountedprice.setText(DiscountedPrice);
        tastequantity.setText(Quantity);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TasteName = tastename.getText().toString();
                String TasteDescription = tastedescription.getText().toString();
                String TastePrice = tasteprice.getText().toString();
                String DiscountedPrice = discountedprice.getText().toString();
                String TasteQuantity = tastequantity.getText().toString();

                if (TasteName.equals("")){
                    Snackbar snackbar = Snackbar.make(v,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TasteDescription.equals("")){
                    Snackbar snackbar = Snackbar.make(v,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TastePrice.equals("")){
                    Snackbar snackbar = Snackbar.make(v,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (DiscountedPrice.equals("")){
                    Snackbar snackbar = Snackbar.make(v,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TasteQuantity.equals("")){
                    Snackbar snackbar = Snackbar.make(v,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                TasteModel tasteModel = new TasteModel(TasteName,TasteDescription,TastePrice,DiscountedPrice,TasteQuantity);
                databaseReference.child("taste").child(Key).setValue(tasteModel);
                Snackbar snackbar = Snackbar.make(v,"Your taste uploaded successfully",Snackbar.LENGTH_LONG);
                snackbar.show();
                tastename.setText("");
                tastedescription.setText("");
                tasteprice.setText("");
                discountedprice.setText("");
                tastequantity.setText("");

            }
        });

    }
}