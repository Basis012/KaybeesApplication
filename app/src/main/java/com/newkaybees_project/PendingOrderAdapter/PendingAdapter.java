package com.newkaybees_project.PendingOrderAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newkaybees_project.CartModel.AddToCartModel;
import com.newkaybees_project.EditTaste;
import com.newkaybees_project.R;
import com.newkaybees_project.UserDashboardFragments.MyHome;

import java.util.ArrayList;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {
    ArrayList<AddToCartModel> pendingmodel = new ArrayList<>();
    ArrayList<String> keys = new ArrayList<>();
    Context context;

    public PendingAdapter(ArrayList<AddToCartModel> pendingmodel, ArrayList<String> keys, Context context) {
        this.pendingmodel = pendingmodel;
        this.keys = keys;
        this.context = context;
    }


    @NonNull
    @Override
    public PendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pendingcart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAdapter.ViewHolder holder, int position) {
          String name = pendingmodel.get(position).getTitle();
          String price = pendingmodel.get(position).getPrice();
          String quantity = pendingmodel.get(position).getQuantity();
          holder.name.setText(name);
          holder.price.setText("Price "+price);
          holder.quantity.setText("Quantity "+quantity);
          String key = keys.get(position);
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
          holder.delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  databaseReference.child("Add To Cart").child(key).removeValue();
              }
          });
    }

    @Override
    public int getItemCount() {
        return pendingmodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
