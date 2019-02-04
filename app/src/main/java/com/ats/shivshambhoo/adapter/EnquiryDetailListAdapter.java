package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.EnquiryHeaderDisp;
import com.ats.shivshambhoo.model.GetEnqDetail;

import java.util.ArrayList;

public class EnquiryDetailListAdapter extends RecyclerView.Adapter<EnquiryDetailListAdapter.MyViewHolder> {

    private ArrayList<GetEnqDetail> detailList;
    private Context context;

    public EnquiryDetailListAdapter(ArrayList<GetEnqDetail> detailList, Context context) {
        this.detailList = detailList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvQty, tvUOM;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvQty = view.findViewById(R.id.tvQty);
            tvUOM = view.findViewById(R.id.tvUOM);
            linearLayout = view.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_enquiry_detail_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GetEnqDetail model = detailList.get(position);

        holder.tvName.setText(model.getItemName());
        holder.tvQty.setText("" + model.getItemQty());
        holder.tvUOM.setText(model.getUomName());

        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
        }

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
}
