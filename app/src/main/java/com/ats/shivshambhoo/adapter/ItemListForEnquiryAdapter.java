package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.ItemsByPlant;

import java.util.ArrayList;

public class ItemListForEnquiryAdapter extends RecyclerView.Adapter<ItemListForEnquiryAdapter.MyViewHolder> {

    private static ArrayList<ItemsByPlant> itemList;
    private ArrayList<String> uomNameList;
    private ArrayList<Integer> uomIdList;
    private Context context;

    public ItemListForEnquiryAdapter(ArrayList<ItemsByPlant> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public ItemListForEnquiryAdapter(ArrayList<ItemsByPlant> itemList, ArrayList<String> uomNameList, ArrayList<Integer> uomIdList, Context context) {
        this.itemList = itemList;
        this.uomNameList = uomNameList;
        this.uomIdList = uomIdList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public Spinner spUOM;
        public EditText edQty;
        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            spUOM = view.findViewById(R.id.spUOM);
            edQty = view.findViewById(R.id.edQty);
            linearLayout = view.findViewById(R.id.linearLayout);

            edQty.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try {
                        itemList.get(getAdapterPosition()).setQty(Integer.parseInt(charSequence.toString()));
                    } catch (Exception e) {
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_list_for_enquiry, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ItemsByPlant model = itemList.get(position);

        Log.e("ADAPTER : ", "          NAME : " + model.getItemName() + "                     QTY : " + model.getQty());

        if (position%2==0){
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
        }

        holder.tvName.setText("" + model.getItemName());
        holder.edQty.setText("" + model.getQty());

        Log.e(""+model.getItemName(),"----------------UOM----------------"+model.getUomName());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, uomNameList);
        holder.spUOM.setAdapter(adapter);

        if (uomIdList.size() > 0) {
            int pos = 0;
            for (int i = 0; i < uomIdList.size(); i++) {
                if (model.getUomNewId() == uomIdList.get(i)) {
                    pos = i;
                }
            }
            holder.spUOM.setSelection(pos);
        }

        holder.spUOM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemList.get(position).setUomNewId(uomIdList.get(holder.spUOM.getSelectedItemPosition()));
                Log.e("SELECTED VALUE", " ------- " + uomIdList.get(holder.spUOM.getSelectedItemPosition()) + "          " + uomNameList.get(holder.spUOM.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
