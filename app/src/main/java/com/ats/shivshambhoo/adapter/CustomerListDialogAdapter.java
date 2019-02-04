package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.activity.HomeActivity;
import com.ats.shivshambhoo.fragment.AddCustomerFragment;
import com.ats.shivshambhoo.model.Cust;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CustomerListDialogAdapter extends RecyclerView.Adapter<CustomerListDialogAdapter.MyViewHolder> {

    private ArrayList<Cust> custList;
    private Context context;

    public CustomerListDialogAdapter(ArrayList<Cust> custList, Context context) {
        this.custList = custList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvAddress;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvAddress = view.findViewById(R.id.tvAddress);
            linearLayout = view.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_customer_list_dialog, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Cust model = custList.get(position);

        holder.tvName.setText(model.getCustName());
        holder.tvAddress.setText(model.getCustAddress());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerDataIntent = new Intent();
                customerDataIntent.setAction("CUSTOMER_DATA");
                customerDataIntent.putExtra("name", model.getCustName());
                customerDataIntent.putExtra("id", model.getCustId());
                LocalBroadcastManager.getInstance(context).sendBroadcast(customerDataIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return custList.size();
    }

    public void updateList(ArrayList<Cust> list) {
        custList = list;
        notifyDataSetChanged();
    }

}
