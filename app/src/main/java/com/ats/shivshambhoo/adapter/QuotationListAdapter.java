package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.activity.HomeActivity;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.fragment.AddCustomerFragment;
import com.ats.shivshambhoo.fragment.CustomerListFragment;
import com.ats.shivshambhoo.fragment.EditQuotationFragment;
import com.ats.shivshambhoo.fragment.QuotationListFragment;
import com.ats.shivshambhoo.model.GetQuotDetail;
import com.ats.shivshambhoo.model.Info;
import com.ats.shivshambhoo.model.QuotationHeaderDisp;
import com.ats.shivshambhoo.util.CommonDialog;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotationListAdapter extends RecyclerView.Adapter<QuotationListAdapter.MyViewHolder> {

    private ArrayList<QuotationHeaderDisp> quoteList;
    private Context context;

    public QuotationListAdapter(ArrayList<QuotationHeaderDisp> quoteList, Context context) {
        this.quoteList = quoteList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCustName, tvQuoteNo, tvDate, tvRemark;
        public RecyclerView recyclerView;
        public CardView cardView;
        public ImageView ivEdit;

        public MyViewHolder(View view) {
            super(view);
            tvCustName = view.findViewById(R.id.tvCustName);
            tvQuoteNo = view.findViewById(R.id.tvQuoteNo);
            tvDate = view.findViewById(R.id.tvDate);
            tvRemark = view.findViewById(R.id.tvRemark);
            recyclerView = view.findViewById(R.id.recyclerView);
            cardView = view.findViewById(R.id.cardView);
            ivEdit = view.findViewById(R.id.ivEdit);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_quotation_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final QuotationHeaderDisp model = quoteList.get(position);

        holder.tvCustName.setText(model.getCustName());
        holder.tvQuoteNo.setText(model.getQuotNo());
        holder.tvDate.setText(model.getQuotDate());
        holder.tvRemark.setText(model.getOtherRemark1());

        if (model.getStatus() > 1) {
            holder.ivEdit.setVisibility(View.GONE);
        } else {
            holder.ivEdit.setVisibility(View.VISIBLE);
        }


        if (model.getGetQuotDetailList() != null) {
            ArrayList<GetQuotDetail> detailList = new ArrayList<>();
            for (int i = 0; i < model.getGetQuotDetailList().size(); i++) {
                detailList.add(model.getGetQuotDetailList().get(i));
            }

            QuotationDetailListAdapter adapter = new QuotationDetailListAdapter(detailList, context);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            holder.recyclerView.setLayoutManager(mLayoutManager);
            holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
            holder.recyclerView.setAdapter(adapter);
        }

       /* if (position % 2 == 0) {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorLight));
        }*/

      /*  holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new Gson();
                String json = gson.toJson(model);

                HomeActivity activity = (HomeActivity) context;

                Fragment adf = new EditQuotationFragment();
                Bundle args = new Bundle();
                args.putString("model", json);
                adf.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, adf, "QuotationListFragment").commit();


            }
        });*/

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_quotation, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_gen_quote) {

                            Gson gson = new Gson();
                            String json = gson.toJson(model);

                            HomeActivity activity = (HomeActivity) context;

                            Fragment adf = new EditQuotationFragment();
                            Bundle args = new Bundle();
                            args.putString("model", json);
                            args.putInt("type", 1);
                            adf.setArguments(args);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, adf, "QuotationListFragment").commit();

                        }if (menuItem.getItemId() == R.id.action_gen_quote_email) {

                            Gson gson = new Gson();
                            String json = gson.toJson(model);

                            HomeActivity activity = (HomeActivity) context;

                            Fragment adf = new EditQuotationFragment();
                            Bundle args = new Bundle();
                            args.putString("model", json);
                            args.putInt("type", 1);
                            args.putInt("email", 1);
                            adf.setArguments(args);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, adf, "QuotationListFragment").commit();

                        } else if (menuItem.getItemId() == R.id.action_edit) {

                            Gson gson = new Gson();
                            String json = gson.toJson(model);

                            HomeActivity activity = (HomeActivity) context;

                            Fragment adf = new EditQuotationFragment();
                            Bundle args = new Bundle();
                            args.putString("model", json);
                            args.putInt("type", 0);
                            adf.setArguments(args);
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, adf, "QuotationListFragment").commit();

                        } else if (menuItem.getItemId() == R.id.action_delete) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                            builder.setTitle("Confirm Action");
                            builder.setMessage("Do you want to delete quotation?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteQuotation(model.getQuotHeadId());
                                    dialog.dismiss();
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
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public void deleteQuotation(int headerId) {
        if (Constants.isOnline(context)) {
            final CommonDialog commonDialog = new CommonDialog(context, "Loading", "Please Wait...");
            commonDialog.show();

            Call<Info> listCall = Constants.myInterface.deleteQuotation(headerId);
            listCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("DELETE QUOTATION: ", " - " + response.body());

                            if (!response.body().isError()) {

                                HomeActivity activity = (HomeActivity) context;

                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_frame, new QuotationListFragment(), "HomeFragment");
                                ft.commit();


                            } else {
                                Toast.makeText(context, "Unable to process", Toast.LENGTH_SHORT).show();
                            }

                            commonDialog.dismiss();

                        } else {
                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                            Toast.makeText(context, "Unable to process", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        Toast.makeText(context, "Unable to process", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    Toast.makeText(context, "Unable to process", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(context, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

}
