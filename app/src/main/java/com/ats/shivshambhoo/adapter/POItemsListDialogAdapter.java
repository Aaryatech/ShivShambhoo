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

public class POItemsListDialogAdapter extends RecyclerView.Adapter<POItemsListDialogAdapter.MyViewHolder> {

    private ArrayList<POItems> itemList;
    private Context context;

    public POItemsListDialogAdapter(ArrayList<POItems> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public EditText edQty, edRemQty;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            edQty = view.findViewById(R.id.edQty);
            edRemQty = view.findViewById(R.id.edRemQty);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_po_items_list_dialog, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final POItems model = itemList.get(position);

        holder.tvName.setText(model.getItemName());
        holder.edRemQty.setText("" + model.getPoRemainingQty());
        holder.edQty.setText("" + model.getQty());

        holder.edQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {
                    float qty = Float.parseFloat(charSequence.toString());
                    if (qty <= model.getPoRemainingQty()) {
                        model.setQty(qty);
                    } else {
                        holder.edQty.setError("qty exceeds than remaining qty");
                        holder.edQty.setText("0");
                        model.setQty(0);
                    }
                } catch (Exception e) {
                    model.setQty(0);
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
