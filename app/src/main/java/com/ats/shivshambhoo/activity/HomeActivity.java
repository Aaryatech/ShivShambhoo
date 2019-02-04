package com.ats.shivshambhoo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.fragment.AddCustomerFragment;
import com.ats.shivshambhoo.fragment.AddEnquiryFragment;
import com.ats.shivshambhoo.fragment.AddOrderFragment;
import com.ats.shivshambhoo.fragment.AllQuotationListFragment;
import com.ats.shivshambhoo.fragment.CustomerListFragment;
import com.ats.shivshambhoo.fragment.EditQuotationFragment;
import com.ats.shivshambhoo.fragment.EnquiryListFragment;
import com.ats.shivshambhoo.fragment.HomeFragment;
import com.ats.shivshambhoo.fragment.OrderListFragment;
import com.ats.shivshambhoo.fragment.QuotationListFragment;
import com.ats.shivshambhoo.fragment.UserFragment;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.model.User;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.ats.shivshambhoo.util.PermissionsUtil;
import com.google.gson.Gson;

import java.io.File;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    User user;

    File folder = new File(Environment.getExternalStorageDirectory() + File.separator, "ShivShambhu" + File.separator + "Quotation");
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (PermissionsUtil.checkAndRequestPermissions(this)) {
        }

        createFolder();

        String userStr = CustomSharedPreference.getString(this, CustomSharedPreference.KEY_USER);
        Gson gson = new Gson();
        user = gson.fromJson(userStr, User.class);
        Log.e("HOME_ACTIVITY : ", "--------USER-------" + user);

        if (user == null) {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();

        } else {
            int plantId = CustomSharedPreference.getInt(this, CustomSharedPreference.KEY_PLANT_ID);
            if (plantId == 0) {
                startActivity(new Intent(HomeActivity.this, PlantSelectionActivity.class));
                finish();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        TextView tvName = header.findViewById(R.id.tvUser);
        TextView tvMobile = header.findViewById(R.id.tvMobile);
        TextView tvPlantName = header.findViewById(R.id.tvPlantName);

        if (user != null) {
            tvName.setText("" + user.getUsrName());
            tvMobile.setText("" + user.getUsrMob());
        }

        try {
            String plantStr = CustomSharedPreference.getString(this, CustomSharedPreference.KEY_PLANT);
            Gson gsonPlant = new Gson();
            Plant plant = gsonPlant.fromJson(plantStr, Plant.class);
            tvPlantName.setText("" + plant.getPlantName());
        } catch (Exception e) {
        }

        int type = getIntent().getIntExtra("type", 0);

        if (type == 0) {
            if (savedInstanceState == null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new HomeFragment(), "Exit");
                ft.commit();
            }

        } else if (type == 1) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new EnquiryListFragment(), "HomeFragment");
            ft.commit();
        } else if (type == 2) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new QuotationListFragment(), "HomeFragment");
            ft.commit();
        } else if (type == 3) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new AllQuotationListFragment(), "HomeFragment");
            ft.commit();
        }


    }

    public void createFolder() {
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    @Override
    public void onBackPressed() {

        Fragment exit = getSupportFragmentManager().findFragmentByTag("Exit");
        Fragment homeFragment = getSupportFragmentManager().findFragmentByTag("HomeFragment");
        Fragment customerListFragment = getSupportFragmentManager().findFragmentByTag("CustomerListFragment");
        Fragment quotationListFragment = getSupportFragmentManager().findFragmentByTag("QuotationListFragment");
        Fragment allQuotationListFragment = getSupportFragmentManager().findFragmentByTag("AllQuotationListFragment");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (exit instanceof HomeFragment && exit.isVisible()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogTheme);
            builder.setMessage("Exit Application ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (homeFragment instanceof AddCustomerFragment && homeFragment.isVisible() ||
                homeFragment instanceof CustomerListFragment && homeFragment.isVisible() ||
                homeFragment instanceof AddEnquiryFragment && homeFragment.isVisible() ||
                homeFragment instanceof EnquiryListFragment && homeFragment.isVisible() ||
                homeFragment instanceof QuotationListFragment && homeFragment.isVisible() ||
                homeFragment instanceof AddOrderFragment && homeFragment.isVisible() ||
                homeFragment instanceof OrderListFragment && homeFragment.isVisible() ||
                homeFragment instanceof AllQuotationListFragment && homeFragment.isVisible() ||
                homeFragment instanceof UserFragment && homeFragment.isVisible()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new HomeFragment(), "Exit");
            ft.commit();
        } else if (customerListFragment instanceof AddCustomerFragment && customerListFragment.isVisible()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
            ft.commit();
        } else if (quotationListFragment instanceof EditQuotationFragment && quotationListFragment.isVisible()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new QuotationListFragment(), "HomeFragment");
            ft.commit();
        } else if (allQuotationListFragment instanceof EditQuotationFragment && allQuotationListFragment.isVisible()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new AllQuotationListFragment(), "HomeFragment");
            ft.commit();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

        } else if (id == R.id.nav_plant_selection) {
            startActivity(new Intent(HomeActivity.this, PlantSelectionActivity.class));
            finish();

        } else if (id == R.id.nav_add_customer) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new AddCustomerFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_customer_list) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_add_enquiry) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new AddEnquiryFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_enquiry_list) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new EnquiryListFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_quotation_list) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new QuotationListFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_all_quotation_list) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new AllQuotationListFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_order_add) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new AddOrderFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_order_list) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new OrderListFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_user) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new UserFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogTheme);
            builder.setTitle("Logout");
            builder.setMessage("Are you sure you want to logout?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    CustomSharedPreference.deletePreference(HomeActivity.this);
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
