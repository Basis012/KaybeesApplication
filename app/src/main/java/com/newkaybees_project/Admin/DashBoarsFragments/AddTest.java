package com.newkaybees_project.Admin.DashBoarsFragments;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.newkaybees_project.Admin.Model.TasteModel;
import com.newkaybees_project.R;
public class AddTest extends Fragment {
    MaterialButton uploadBtn;
    ImageView testimage;
    Button imgbtn;
    TextInputEditText tastename, tastedescription, tasteprice, discountedprice, tastequantity;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Product_items");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_test, container, false);
        uploadBtn = view.findViewById(R.id.uploadBtn);
        tastename = view.findViewById(R.id.tastename);
        tastedescription = view.findViewById(R.id.tastedescription);
        imgbtn = view.findViewById(R.id.imgbtn);
        testimage = view.findViewById(R.id.testimage);
        tasteprice = view.findViewById(R.id.tasteprice);
        discountedprice = view.findViewById(R.id.discountedprice);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
        tastequantity = view.findViewById(R.id.tastequantity);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TasteName = tastename.getText().toString();
                String TasteDescription = tastedescription.getText().toString();
                String TastePrice = tasteprice.getText().toString();
                String DiscountedPrice = discountedprice.getText().toString();
                String TasteQuantity = tastequantity.getText().toString();
                testimage = view.findViewById(R.id.testimage);
                imgbtn = view.findViewById(R.id.imgbtn);

                if (TasteName.equals("")){
                    Snackbar snackbar = Snackbar.make(view,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
               else if (TasteDescription.equals("")){
                    Snackbar snackbar = Snackbar.make(view,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TastePrice.equals("")){
                    Snackbar snackbar = Snackbar.make(view,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (DiscountedPrice.equals("")){
                    Snackbar snackbar = Snackbar.make(view,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if (TasteQuantity.equals("")){
                    Snackbar snackbar = Snackbar.make(view,"Please fill field",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                    TasteModel tasteModel = new TasteModel(TasteName, TasteDescription, TastePrice, DiscountedPrice, TasteQuantity);
                    databaseReference.child("taste").push().setValue(tasteModel);
                    Snackbar snackbar = Snackbar.make(view, "Your taste uploaded successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    tastename.setText("");
                    tastedescription.setText("");
                    tasteprice.setText("");
                    discountedprice.setText("");
                    tastequantity.setText("");
                }

            }
        });
        return  view;
    }
    public  void choosePicture(){
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
            testimage.setImageURI(imageUri);

        }

    }

}