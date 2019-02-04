package com.ats.shivshambhoo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.adapter.PlantSelectionAdapter;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.util.CommonDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantSelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    PlantSelectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_selection);

        setTitle("Plant Selection");

        recyclerView = findViewById(R.id.recyclerView);

        getPlantList();

    }

    public void getPlantList() {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<Plant>> listCall = Constants.myInterface.getPlantList();
            listCall.enqueue(new Callback<ArrayList<Plant>>() {
                @Override
                public void onResponse(Call<ArrayList<Plant>> call, Response<ArrayList<Plant>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("PLANT LIST : ", " - " + response.body());

                            adapter = new PlantSelectionAdapter(response.body(), PlantSelectionActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PlantSelectionActivity.this);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);

                            commonDialog.dismiss();

                        } else {
                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Plant>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


}