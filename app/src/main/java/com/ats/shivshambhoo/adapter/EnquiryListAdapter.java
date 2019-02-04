package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.EnquiryHeader;
import com.ats.shivshambhoo.model.EnquiryHeaderDisp;
import com.ats.shivshambhoo.model.GetEnqDetail;

import java.util.ArrayList;

public class EnquiryListAdapter extends RecyclerView.Adapter<EnquiryListAdapter.MyViewHolder> {

    private ArrayList<EnquiryHeaderDisp> enquiryList;
    private Context context;

    public EnquiryListAdapter(ArrayList<EnquiryHeaderDisp> enquiryList, Context context) {
        this.enquiryList = enquiryList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCustName, tvEnqNo, tvDate, tvRemark;
        public RecyclerView recyclerView;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            tvCustName = view.findViewById(R.id.tvCustName);
            tvEnqNo = view.findViewById(R.id.tvEnqNo);
            tvDate = view.findViewById(R.id.tvDate);
            tvRemark = view.findViewById(R.id.tvRemark);
            recyclerView = view.findViewById(R.id.recyclerView);
            cardView = view.findViewById(R.id.cardView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_enquiry_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final EnquiryHeaderDisp model = enquiryList.get(position);

        holder.tvCustName.setText(model.getCustName());
        holder.tvDate.setText(model.getEnqDate());
        holder.tvEnqNo.setText("Enquiry No. : " + model.getEnqNo());
        holder.tvRemark.setText(model.getEnqHRemark());

        if (model.getEnqDetailList() != null) {
            ArrayList<GetEnqDetail> detailList = new ArrayList<>();
            for (int i = 0; i < model.getEnqDetailList().size(); i++) {
                detailList.add(model.getEnqDetailList().get(i));
            }

            EnquiryDetailListAdapter adapter = new EnquiryDetailListAdapter(detailList, context);
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


    }

    @Override
    public int getItemCount() {
        return enquiryList.size();
    }

}
