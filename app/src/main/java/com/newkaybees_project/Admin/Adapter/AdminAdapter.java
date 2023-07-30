package com.newkaybees_project.Admin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newkaybees_project.Admin.DashBoarsFragments.AddTest;
import com.newkaybees_project.Admin.Model.TasteModel;
import com.newkaybees_project.EditTaste;
import com.newkaybees_project.R;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {
    ArrayList<TasteModel> mymodel = new ArrayList<>();
    ArrayList<String> keylist = new ArrayList<String>();
    Context mcontext;

    public AdminAdapter(ArrayList<TasteModel> mymodel, Context mcontext,ArrayList<String> keylist) {
        this.mymodel = mymodel;
        this.mcontext = mcontext;
        this.keylist = keylist;
    }

    @NonNull
    @Override
    public AdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.admin_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.ViewHolder holder, int position) {
  String name = mymodel.get(position).getTastename();
        String price = mymodel.get(position).getTasteprice();
        String  description = mymodel.get(position).getTastedescription();
        String quantity = mymodel.get(position).getTastequantity();
        String discountedprice = mymodel.get(position).getDiscountedprice();
        holder.name.setText(name);
        holder.price.setText(price);
        holder.description.setText(description);
        holder.quantity.setText(quantity);
        holder.discountedprice.setText(discountedprice);
        String key = keylist.get(position);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              databaseReference.child("taste").child(key).removeValue();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext,EditTaste.class);
                i.putExtra("name",name);
                i.putExtra("price",price);
                i.putExtra("description",description);
                i.putExtra("quantity",quantity);
                i.putExtra("discountedprice",discountedprice);
                i.putExtra("key",key);
                mcontext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mymodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, description, quantity, discountedprice;
        ImageView img;
        ImageView delete, edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            quantity = itemView.findViewById(R.id.quantity);
            discountedprice = itemView.findViewById(R.id.discountedprice);
            img = itemView.findViewById(R.id.img);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
