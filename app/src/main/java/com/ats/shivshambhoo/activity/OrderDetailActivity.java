package com.ats.shivshambhoo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.adapter.OrderDetailListAdapter;
import com.ats.shivshambhoo.adapter.OrderListAdapter;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.model.GetOrder;
import com.ats.shivshambhoo.model.GetOrderDetail;
import com.ats.shivshambhoo.model.User;
import com.ats.shivshambhoo.util.CommonDialog;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView tvOrderNo, tvDate, tvCustomer, tvProject, tvDeliveryDate, tvTotal;
    private RecyclerView recyclerView;

    GetOrder orderHeader;

    ArrayList<GetOrderDetail> orderDetailList = new ArrayList<>();
    OrderDetailListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        setTitle("Order Detail");

        tvOrderNo = findViewById(R.id.tvOrderNo);
        tvDate = findViewById(R.id.tvDate);
        tvCustomer = findViewById(R.id.tvCustomer);
        tvProject = findViewById(R.id.tvProject);
        tvDeliveryDate = findViewById(R.id.tvDeliveryDate);
        tvTotal = findViewById(R.id.tvTotal);

        recyclerView = findViewById(R.id.recyclerView);

        String strJson = getIntent().getStringExtra("model");
        Gson gson = new Gson();
        orderHeader = gson.fromJson(strJson, GetOrder.class);

        if (orderHeader != null) {

            tvOrderNo.setText("Order No : " + orderHeader.getOrderNo());
            tvDate.setText("" + orderHeader.getOrderDate());
            tvProject.setText("" + orderHeader.getProjName());
            tvDeliveryDate.setText("Delivery Date : " + orderHeader.getDeliveryDate());
            tvTotal.setText("" + orderHeader.getTotal());

            getOrderDetailList(orderHeader.getOrderId());
        }

    }


    public void getOrderDetailList(int headerId) {

        Log.e("PARAMETERS : ", "     ORDER HEADER ID : " + headerId);

        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(OrderDetailActivity.this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<GetOrderDetail>> listCall = Constants.myInterface.getOrderDetailList(headerId);
            listCall.enqueue(new Callback<ArrayList<GetOrderDetail>>() {
                @Override
                public void onResponse(Call<ArrayList<GetOrderDetail>> call, Response<ArrayList<GetOrderDetail>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("ORDER LIST : ", " - " + response.body());
                            orderDetailList.clear();
                            orderDetailList = response.body();

                            adapter = new OrderDetailListAdapter(orderDetailList, OrderDetailActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(OrderDetailActivity.this);
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
                public void onFailure(Call<ArrayList<GetOrderDetail>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(OrderDetailActivity.this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
