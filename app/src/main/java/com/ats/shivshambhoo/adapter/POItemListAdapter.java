package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.POItems;

import java.util.ArrayList;

public class POItemListAdapter extends RecyclerView.Adapter<POItemListAdapter.MyViewHolder> {

    private ArrayList<POItems> itemList;
    private Context context;

    public POItemListAdapter(ArrayList<POItems> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvRate;
        public EditText edRemQty, edQty, edValue;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvRate = view.findViewById(R.id.tvRate);
            edRemQty = view.findViewById(R.id.edRemQty);
            edQty = view.findViewById(R.id.edQty);
            edValue = view.findViewById(R.id.edValue);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_po_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final POItems model = itemList.get(position);

        holder.tvName.setText(model.getItemName());
        holder.tvRate.setText("" + model.getPoRate() + "/-");
        holder.edRemQty.setText("" + model.getPoRemainingQty());
        holder.edQty.setText("" + model.getQty());

        float value = model.getQty() * model.getPoRate();
        model.setTotal(value);
        holder.edValue.setText("" + model.getTotal());

        holder.edQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    float qty = Float.parseFloat(charSequence.toString());
                    model.setQty(qty);

                    if (qty <= model.getPoRemainingQty()) {
                        model.setQty(qty);
                    } else {
                        holder.edQty.setError("qty exceeds than remaining qty");
                        holder.edQty.setText("0");
                        model.setQty(0);
                    }

                    float value = model.getQty() * model.getPoRate();
                    model.setTotal(value);
                    holder.edValue.setText("" + model.getTotal());

                } catch (Exception e) {
                    model.setQty(0);
                    model.setTotal(0);
                    holder.edValue.setText("" + model.getTotal());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
