package com.newkaybees_project.UsersAdapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newkaybees_project.Admin.Model.TasteModel;
import com.newkaybees_project.CartModel.AddToCartModel;
import com.newkaybees_project.ModelClasses.MyHome_FoodCards_ModelClass;
import com.newkaybees_project.R;
import com.newkaybees_project.UserDashboardFragments.MyCart;
import com.newkaybees_project.UserDashboardFragments.MyHome;

import java.util.ArrayList;

import soup.neumorphism.NeumorphButton;

public class Recycle_Adapter extends RecyclerView.Adapter<Recycle_Adapter.ViewHolder>{
    ArrayList<MyHome_FoodCards_ModelClass> recycleimg = new ArrayList<>();
    ArrayList<TasteModel> tasteModelArrayList = new ArrayList<>();
    ArrayList<String> keys = new ArrayList<String>();
    ArrayList<TasteModel> mylist = new ArrayList<>();
    ArrayList<TasteModel> filteredData = new ArrayList<>();
    Context context;

    int count = 0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int getPrice;
    int total;
    public Recycle_Adapter(ArrayList<MyHome_FoodCards_ModelClass> recycleimg, ArrayList<TasteModel> tasteModelArrayList, ArrayList<String> keys, ArrayList<TasteModel>mylist,ArrayList<TasteModel> filteredData,Context context ) {
        this.recycleimg = recycleimg;
        this.tasteModelArrayList = tasteModelArrayList;
        this.keys = keys;
        this.mylist = mylist;
        this.filteredData = filteredData;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycle_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_Adapter.ViewHolder holder, int position) {
        TasteModel tasteModel = filteredData.get(position);
//        String name = tasteModelArrayList.get(position).getTastename();
//        String price = tasteModelArrayList.get(position).getTasteprice();
//        String discountedprice = tasteModelArrayList.get(position).getDiscountedprice();
//        String quantity = tasteModelArrayList.get(position).getTastequantity();
//        String description  = tasteModelArrayList.get(position).getTastedescription();
        String name = tasteModel.getTastename();
        String price = tasteModel.getTasteprice();
        String discountedprice = tasteModel.getDiscountedprice();
        String quantity = tasteModel.getTastequantity();
        String description  = tasteModel.getTastedescription();
        holder.cprice.setPaintFlags(holder.cprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        String id = keys.get(position);
        holder.title.setText(name);
        holder.cprice.setText("Rs: "+price);
        holder.cdprice.setText(discountedprice);
        holder.quantity.setText("Qty "+quantity);

        Glide
                .with(context)
                .load(recycleimg.get(position).getFoodcardimages())
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_home_24)
                .into(holder.recycleimage);
        holder.recycleimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheet(name,quantity,price,discountedprice,id);
                System.out.println("title"+name);
                System.out.println("discountedprice"+discountedprice);
                System.out.println("id"+id);
            }
        });
        holder.recycleimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View view1 = inflater.inflate(R.layout.dialog_box, null);
                ImageView image = view1.findViewById(R.id.dialogimage);
                Glide.with(context)
                        .load(recycleimg.get(position).getFoodcardimages())
                        .into(image);
                TextView text = view1.findViewById(R.id.dialogtext);
                TextView text2 = view1.findViewById(R.id.dialodnewprice);
                text.setText(filteredData.get(position).getTastename());
                MaterialButton btn = view1.findViewById(R.id.dialogbtn);
                text2.setText("RS: " + filteredData.get(position).getDiscountedprice());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view1);
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        Fragment myFragment = new MyCart();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.today, myFragment).addToBackStack(null).commit();
                        dialog.dismiss();

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recycleimage;
        TextView title, quantity, cprice,cdprice;
        ImageView recycleimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recycleimage = itemView.findViewById(R.id.recycleimage);
            title = itemView.findViewById(R.id.title);
            recycleimg = itemView.findViewById(R.id.recycleimg);
            cprice = itemView.findViewById(R.id.cprice);
            cdprice = itemView.findViewById(R.id.cdprice);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
    public void openBottomSheet(String name,String quantity, String price,String discountedprice,String id){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet);
        bottomSheetDialog.show();
        ImageView plus =  bottomSheetDialog.findViewById(R.id.plus);
        ImageView minus =  bottomSheetDialog.findViewById(R.id.minus);
        TextView display = bottomSheetDialog.findViewById(R.id.display);
        TextView btitle = bottomSheetDialog.findViewById(R.id.btitle);
        TextView bprice = bottomSheetDialog.findViewById(R.id.bprice);
        TextView totalprice = bottomSheetDialog.findViewById(R.id.totalprice);
        String userId = FirebaseAuth.getInstance().getUid();

        NeumorphButton cart = bottomSheetDialog.findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mtotal = getPrice * count;
                System.out.println("quantity"+quantity);
                System.out.println("price"+price);
                System.out.println("discountedprice"+discountedprice);
                System.out.println("mtotal"+total);
//                AddToCartModel addToCartModel = new AddToCartModel(title,discountedprice,String.valueOf(count),String.valueOf(mtotal),id,userId,);
                AddToCartModel addToCartModel = new AddToCartModel(name,quantity,price,discountedprice,String.valueOf(count),userId,total);
                databaseReference.child("Add To Cart").push().setValue(addToCartModel);
                bottomSheetDialog.dismiss();
            }
        });
 btitle.setText(name);
 bprice.setText("Rs: "+price);
        bottomSheetDialog.findViewById(R.id.minus);
        bottomSheetDialog.findViewById(R.id.display);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count<=0){
                    count = 0;
                    totalprice.setText("Total Price");
                }
                else {
                    count --;
                    display.setText(count+"");
                    int getPrice = Integer.parseInt(price);
                    int total = getPrice * count;
                    totalprice.setText(total+"");
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       count ++;
       display.setText(count+"");
       int getPrice = Integer.parseInt(price);
       total = getPrice * count;
       totalprice.setText(total+"");
            }
        });
    }
}
