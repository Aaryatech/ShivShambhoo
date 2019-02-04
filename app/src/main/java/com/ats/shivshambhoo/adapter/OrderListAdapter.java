package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.activity.OrderDetailActivity;
import com.ats.shivshambhoo.model.GetOrder;
import com.google.gson.Gson;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {

    private ArrayList<GetOrder> orderList;
    private Context context;

    public OrderListAdapter(ArrayList<GetOrder> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCustName, tvOrderNo, tvDate, tvProject, tvDeliveryDate, tvTotal, tvStatus;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            tvCustName = view.findViewById(R.id.tvCustName);
            tvOrderNo = view.findViewById(R.id.tvOrderNo);
            tvDate = view.findViewById(R.id.tvDate);
            tvProject = view.findViewById(R.id.tvProject);
            tvDeliveryDate = view.findViewById(R.id.tvDeliveryDate);
            tvTotal = view.findViewById(R.id.tvTotal);
            cardView = view.findViewById(R.id.cardView);
            tvStatus = view.findViewById(R.id.tvStatus);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_order_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GetOrder model = orderList.get(position);

        holder.tvCustName.setText(model.getCustName());
        holder.tvOrderNo.setText("Order No : " + model.getOrderNo());
        holder.tvDate.setText(model.getOrderDate());
        holder.tvProject.setText(model.getProjName());
        holder.tvDeliveryDate.setText("Delivery Date : " + model.getDeliveryDate());
        holder.tvTotal.setText("" + model.getTotal() + "/-");

        if (model.getStatus() == 0) {
            holder.tvStatus.setText("Pending");
        } else if (model.getStatus() == 1) {
            holder.tvStatus.setText("Chalan Generated");
        }


        if (position % 2 == 0) {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new Gson();
                String json = gson.toJson(model);

                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("model", json);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

}
