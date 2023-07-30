package com.newkaybees_project.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.newkaybees_project.Admin.DashBoarsFragments.AddTest;
import com.newkaybees_project.Admin.DashBoarsFragments.CompleteOrder;
import com.newkaybees_project.Admin.DashBoarsFragments.Logout;
import com.newkaybees_project.Admin.DashBoarsFragments.PendingOrder;
import com.newkaybees_project.Admin.DashBoarsFragments.ViewTaste;
import com.newkaybees_project.R;

public class AdminDashboard extends AppCompatActivity {
DrawerLayout admindrawer;
NavigationView adminnav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        admindrawer = findViewById(R.id.admindrawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        adminnav = findViewById(R.id.adminnav);
        adminnav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.addtest){
                    goFragment(new AddTest());
                }
               else if (item.getItemId()==R.id.viewtaste){
                   goFragment(new ViewTaste());
                }
               else if (item.getItemId()==R.id.pendingorder){
                   goFragment(new PendingOrder());
                }
               else if (item.getItemId()==R.id.completeorder){
                   goFragment(new CompleteOrder());
                }
                else if (item.getItemId()==R.id.logout){
                    goFragment(new Logout());
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            if (admindrawer.isOpen()){
                admindrawer.close();
            }
            else {
                admindrawer.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void goFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
        admindrawer.close();
    }

}