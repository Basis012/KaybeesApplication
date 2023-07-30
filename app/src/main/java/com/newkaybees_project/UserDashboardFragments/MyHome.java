package com.newkaybees_project.UserDashboardFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newkaybees_project.Admin.Model.TasteModel;
import com.newkaybees_project.CartModel.AddToCartModel;
import com.newkaybees_project.ImageSliderAdapter.NewAdapter;
import com.newkaybees_project.ModelClasses.MyHome_FoodCards_ModelClass;
import com.newkaybees_project.ModelClasses.MyHome_ImageSlider_ModelClass;
import com.newkaybees_project.R;
import com.newkaybees_project.Screens.Signin_Screen;
import com.newkaybees_project.Signup_Model.ModelClass;
import com.newkaybees_project.UsersAdapter.Recycle_Adapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class MyHome extends Fragment {
    ArrayList<TasteModel> mylist = new ArrayList<>();
    TextView show;
    ArrayList<AddToCartModel> addToCartModels = new ArrayList<>();
    EditText searchview;
SliderView slider;
ArrayList<MyHome_FoodCards_ModelClass> recycleimg = new ArrayList<>();
ArrayList<MyHome_ImageSlider_ModelClass> saqib = new ArrayList<>();
RecyclerView recycle;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
ArrayList<TasteModel> tasteModelArrayList = new ArrayList<>();
ArrayList<String> keys = new ArrayList<String>();
ArrayList<TasteModel> filteredData = new ArrayList<>();
    ProgressDialog progressDialog;
    Recycle_Adapter recycle_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_home, container, false);
        Initialization(view);
        firebaseReferences();
        imageSliderData();
        foodCardsData();
        fetchCard();
        return view;
    }

    private void foodCardsData() {
        MyHome_FoodCards_ModelClass foodCards_modelClass1 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1457460866886-40ef8d4b42a0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzB8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass2 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1619881590738-a111d176d906?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Njh8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass3 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1618219877704-18411f61719d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTh8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass4 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1556710986-4a70434a76c0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTB8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass5 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1585159183446-0fc0eaf9e57c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDd8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass6 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1489528792647-46ec39027556?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDh8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass7 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1621334953222-c60c19143b0a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDZ8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass8 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1457460866886-40ef8d4b42a0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzB8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass9 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1619881590738-a111d176d906?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Njh8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass10 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1618219877704-18411f61719d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTh8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass12 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1556710986-4a70434a76c0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTB8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass13 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1585159183446-0fc0eaf9e57c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDd8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass14 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1489528792647-46ec39027556?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDh8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass15 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1621334953222-c60c19143b0a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDZ8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass16 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1561758033-563f9666b8c8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDN8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass17 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1602192103300-47e66756152e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzZ8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass18 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1532987620153-4a864cb90fed?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzh8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass19 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzV8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass20 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzV8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_FoodCards_ModelClass foodCards_modelClass21 = new MyHome_FoodCards_ModelClass("https://images.unsplash.com/photo-1598515213692-5f252f75d785?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzJ8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        recycleimg.add(foodCards_modelClass1);
        recycleimg.add(foodCards_modelClass2);
        recycleimg.add(foodCards_modelClass3);
        recycleimg.add(foodCards_modelClass4);
        recycleimg.add(foodCards_modelClass5);
        recycleimg.add(foodCards_modelClass6);
        recycleimg.add(foodCards_modelClass7);
        recycleimg.add(foodCards_modelClass8);
        recycleimg.add(foodCards_modelClass9);
        recycleimg.add(foodCards_modelClass10);
        recycleimg.add(foodCards_modelClass12);
        recycleimg.add(foodCards_modelClass13);
        recycleimg.add(foodCards_modelClass14);
        recycleimg.add(foodCards_modelClass15);
        recycleimg.add(foodCards_modelClass16);
        recycleimg.add(foodCards_modelClass17);
        recycleimg.add(foodCards_modelClass18);
        recycleimg.add(foodCards_modelClass19);
        recycleimg.add(foodCards_modelClass20);
        recycleimg.add(foodCards_modelClass21);
    }

    private void imageSliderData() {
        NewAdapter newAdapter = new NewAdapter(saqib,getContext());
        MyHome_ImageSlider_ModelClass imagesliderdata1 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1512152272829-e3139592d56f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZmFzdCUyMGZvb2R8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata2 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1561758033-d89a9ad46330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZmFzdCUyMGZvb2R8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata3 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1552611052-d59a0d9741bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Zm9vZCUyMGltYWdlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata4 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1598214886806-c87b84b7078b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Zm9vZCUyMGltYWdlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata5 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1552526881-721ce8509abb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8Zm9vZCUyMGltYWdlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata6 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1551638059-d1fb82606c4a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fGZvb2QlMjBpbWFnZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata7 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1554587235-9dfc8f5f7af9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTF8fGZvb2QlMjBpbWFnZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata8 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1610614819513-58e34989848b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata9 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1598679253544-2c97992403ea?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata10 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1552611052-d59a0d9741bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Zm9vZCUyMGltYWdlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata11 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1598214886806-c87b84b7078b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Zm9vZCUyMGltYWdlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata12 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1552526881-721ce8509abb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8Zm9vZCUyMGltYWdlc3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata13 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1551638059-d1fb82606c4a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fGZvb2QlMjBpbWFnZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata14 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1554587235-9dfc8f5f7af9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NTF8fGZvb2QlMjBpbWFnZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata15 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1610614819513-58e34989848b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata16 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1598679253544-2c97992403ea?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata17 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1624153064067-566cae78993d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjB8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata18 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1565299507177-b0ac66763828?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata19 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1617364066121-8a26d640130f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzN8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        MyHome_ImageSlider_ModelClass imagesliderdata20 = new MyHome_ImageSlider_ModelClass("https://images.unsplash.com/photo-1555072956-7758afb20e8f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NDl8fGZhc3QlMjBmb29kfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        saqib.add(imagesliderdata1);
        saqib.add(imagesliderdata2);
        saqib.add(imagesliderdata3);
        saqib.add(imagesliderdata4);
        saqib.add(imagesliderdata5);
        saqib.add(imagesliderdata6);
        saqib.add(imagesliderdata7);
        saqib.add(imagesliderdata8);
        saqib.add(imagesliderdata9);
        saqib.add(imagesliderdata10);
        saqib.add(imagesliderdata11);
        saqib.add(imagesliderdata12);
        saqib.add(imagesliderdata13);
        saqib.add(imagesliderdata14);
        saqib.add(imagesliderdata15);
        saqib.add(imagesliderdata16);
        saqib.add(imagesliderdata17);
        saqib.add(imagesliderdata18);
        saqib.add(imagesliderdata19);
        saqib.add(imagesliderdata20);
        slider.setSliderAdapter(newAdapter);
        slider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        slider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        slider.startAutoCycle();
    }

    private void firebaseReferences() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("taste");
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("wait");
        progressDialog.setMessage("Food Fetching.........");
        progressDialog.setCancelable(false);
        progressDialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tasteModelArrayList.clear();
                progressDialog.dismiss();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    TasteModel tasteModel = dataSnapshot.getValue(TasteModel.class);
                    tasteModelArrayList.add(tasteModel);
                    keys.add(dataSnapshot.getKey());
                }
                recycle_adapter = new Recycle_Adapter(recycleimg,tasteModelArrayList,keys,mylist,filteredData,getContext());
                recycle.setLayoutManager(new GridLayoutManager(getContext(),2));
                recycle.setAdapter(recycle_adapter);
                filteredData.addAll(tasteModelArrayList);
                recycle_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });
    }


    private void Initialization(View view) {
        show = view.findViewById(R.id.show);
        recycle = view.findViewById(R.id.recycle);
        slider = view.findViewById(R.id.slider);
        searchview = view.findViewById(R.id.searchview);
        searchview.clearFocus();
        filteredData = new ArrayList<>();
        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase().trim();
                if (searchText.isEmpty()) {
                    // if search field is empty, show full data
                    filteredData.clear();
                    filteredData.addAll(tasteModelArrayList);
                    recycle_adapter.notifyDataSetChanged();
                } else {
                    // filter data based on search text
                    filteredData.clear();
                    for (TasteModel item : tasteModelArrayList) {
                        if (item.getTastename().toLowerCase().contains(searchText)) {
                            filteredData.add(item);
                        }
                    }
                    recycle_adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        searchview.addTextChangeListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String searchText = s.toString().toLowerCase().trim();
//
//                if (searchText.isEmpty()) {
//                    // if search field is empty, show full data
//                    filteredData.clear();
//                    filteredData.addAll(tasteModelArrayList);
//                    recycle_adapter.notifyDataSetChanged();
//                } else {
//                    // filter data based on search text
//                    filteredData.clear();
//                    for (TasteModel item : tasteModelArrayList) {
//                        if (item.getTastename().toLowerCase().contains(searchText)) {
//                            filteredData.add(item);
//                        }
//                    }
//                    recycle_adapter.notifyDataSetChanged();
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });


    }


    public void fetchCard(){
       databaseReference = firebaseDatabase.getReference("Add To Cart");
       String userId = FirebaseAuth.getInstance().getUid();
       databaseReference.orderByChild("userId").equalTo(userId).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               addToCartModels.clear();
               for (DataSnapshot dataSnapshot: snapshot.getChildren()){

AddToCartModel abc = dataSnapshot.getValue(AddToCartModel.class);
addToCartModels.add(abc);
               }
               show.setText(addToCartModels.size()+"");
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }



}