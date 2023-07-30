package com.newkaybees_project.UsersAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newkaybees_project.Admin.Model.TasteModel;
import com.newkaybees_project.CartModel.AddToCartModel;
import com.newkaybees_project.CheckOutActivity;
import com.newkaybees_project.R;

import java.util.ArrayList;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {
    ArrayList<AddToCartModel> addToCartModels = new ArrayList<>();
    ArrayList<String> keylist = new ArrayList<String>();
    Context mcontext;

    public CartItemsAdapter(ArrayList<AddToCartModel> addToCartModels, ArrayList<String> keylist, Context mcontext) {
        this.addToCartModels = addToCartModels;
        this.keylist = keylist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public CartItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemsAdapter.ViewHolder holder, int position) {
        String title = addToCartModels.get(position).getTitle();
        String discountedprice = addToCartModels.get(position).getDiscountedprice();
        int totalprice = addToCartModels.get(position).getMtotal();
        String quantity = addToCartModels.get(position).getQuantity();
        holder.cctitle.setText(title);
        holder.cctotalprice.setText("Rs :"+totalprice);
        holder.ccquantity.setText("Quantity :"+quantity);
        String key = keylist.get(position);
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        holder.ccbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, CheckOutActivity.class);
                i.putExtra("title",title);
                i.putExtra("totalprice",totalprice);
                i.putExtra("quantity",quantity);
                mcontext.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Add To Cart").child(key).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return addToCartModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cctitle,ccquantity, cctotalprice;
        Button ccbutton;
        ImageView delete, edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cctitle = itemView.findViewById(R.id.cctitle);
            ccquantity = itemView.findViewById(R.id.ccquantity);
            cctotalprice = itemView.findViewById(R.id.cctotalprice);
            ccbutton = itemView.findViewById(R.id.ccbutton);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
