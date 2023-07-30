package com.newkaybees_project.Consumer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.newkaybees_project.R;
import com.newkaybees_project.UserDashboardFragments.MyCart;
import com.newkaybees_project.UserDashboardFragments.MyHome;
import com.newkaybees_project.UserDashboardFragments.Profile;

public class UserDashboard extends AppCompatActivity {
    private ChipNavigationBar nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        getSupportActionBar().hide();
        Initialization();
        onClickListeners();
        defaultShowFragment();
    }

    private void defaultShowFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.today,new MyHome());
        ft.commit();
    }

    private void onClickListeners() {
        nav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if (i==R.id.page_1){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.today,new MyHome());
                    ft.commit();
                }
                else if (i==R.id.page_2){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.today,new MyCart());
                    ft.commit();
                }
                else if (i==R.id.page_3){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.today,new Profile());
                    ft.commit();
                }
            }
        });
    }

    private void Initialization() {
        nav = findViewById(R.id.nav);

    }
}