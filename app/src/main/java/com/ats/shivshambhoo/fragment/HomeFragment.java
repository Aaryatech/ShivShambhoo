package com.ats.shivshambhoo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.google.gson.Gson;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout llEnquiry, llQuotation, llPO, llOrder;
    private TextView tvPlantName;
    Plant plant;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("Dashboard");

        llEnquiry = view.findViewById(R.id.llEnquiry);
        llQuotation = view.findViewById(R.id.llQuotation);
        llPO = view.findViewById(R.id.llPO);
        llOrder = view.findViewById(R.id.llOrder);
        tvPlantName = view.findViewById(R.id.tvPlantName);

        llEnquiry.setOnClickListener(this);
        llQuotation.setOnClickListener(this);
        llPO.setOnClickListener(this);
        llOrder.setOnClickListener(this);

        try {
            String plantStr = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_PLANT);
            Gson gsonPlant = new Gson();
            plant = gsonPlant.fromJson(plantStr, Plant.class);
            tvPlantName.setText("" + plant.getPlantName());
        } catch (Exception e) {
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.llEnquiry) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new EnquiryListFragment(), "HomeFragment");
            ft.commit();
        } else if (view.getId() == R.id.llQuotation) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new QuotationListFragment(), "HomeFragment");
            ft.commit();
        } else if (view.getId() == R.id.llPO) {

        } else if (view.getId() == R.id.llOrder) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new OrderListFragment(), "HomeFragment");
            ft.commit();
        }
    }
}
