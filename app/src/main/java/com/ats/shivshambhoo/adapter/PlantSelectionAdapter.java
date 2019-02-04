package com.ats.shivshambhoo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.activity.HomeActivity;
import com.ats.shivshambhoo.activity.LoginActivity;
import com.ats.shivshambhoo.activity.PlantSelectionActivity;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PlantSelectionAdapter extends RecyclerView.Adapter<PlantSelectionAdapter.MyViewHolder> {

    private ArrayList<Plant> plantList;
    private Context context;

    public PlantSelectionAdapter(ArrayList<Plant> plantList, Context context) {
        this.plantList = plantList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvCompany, tvAddress;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvCompany = view.findViewById(R.id.tvCompany);
            tvAddress = view.findViewById(R.id.tvAddress);
            linearLayout = view.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_plant_selection, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Plant model = plantList.get(position);

        holder.tvName.setText(model.getPlantName());
        holder.tvCompany.setText(model.getCompName());
        holder.tvAddress.setText(model.getPlantAddress1());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new Gson();
                String json = gson.toJson(model);

                CustomSharedPreference.putString(context, CustomSharedPreference.KEY_PLANT, json);
                CustomSharedPreference.putInt(context, CustomSharedPreference.KEY_PLANT_ID, model.getPlantId());

                Intent intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                //PlantSelectionActivity activity
                ((Activity) context).finish();

            }
        });


    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }


}
