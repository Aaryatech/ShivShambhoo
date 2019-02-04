package com.ats.shivshambhoo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.GetItemTax;
import com.ats.shivshambhoo.model.GetQuotDetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.ats.shivshambhoo.fragment.EditQuotationFragment.openItemDialog;
import static com.ats.shivshambhoo.fragment.EditQuotationFragment.staticQuotationDetailList;

public class ItemDialogAdapter extends RecyclerView.Adapter<ItemDialogAdapter.MyViewHolder> {

    private ArrayList<GetItemTax> itemList;
    private Context context;
    private int quotHeadId;

    public ItemDialogAdapter(ArrayList<GetItemTax> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public ItemDialogAdapter(ArrayList<GetItemTax> itemList, Context context, int quotHeadId) {
        this.itemList = itemList;
        this.context = context;
        this.quotHeadId = quotHeadId;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemName;

        public MyViewHolder(View view) {
            super(view);
            tvItemName = view.findViewById(R.id.tvItemName);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item_dialog, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GetItemTax model = itemList.get(position);

        holder.tvItemName.setText(model.getItemName());

        holder.tvItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuantityDialog(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void showQuantityDialog(final GetItemTax item) {

        final Dialog openDialog = new Dialog(context);
        openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openDialog.setContentView(R.layout.dialog_quantity);

        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.x = 100;
        wlp.y = 100;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        TextView tvItemName = openDialog.findViewById(R.id.tvName);
        final EditText edQty = openDialog.findViewById(R.id.edQty);
        Button btnSubmit = openDialog.findViewById(R.id.btnSubmit);
        Button btnCancel = openDialog.findViewById(R.id.btnCancel);

        tvItemName.setText("" + item.getItemName());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edQty.getText().toString().isEmpty()) {
                    edQty.setError("required");
                } else {
                    edQty.setError(null);

                    float qty = Float.parseFloat(edQty.getText().toString());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    GetQuotDetail detail = new GetQuotDetail(0, quotHeadId, item.getItemId(), qty, item.getItemRate1(), 0, 0, item.getTaxId(), item.getSgst(), item.getCgst(), 11, 0, 0, 0, 0, 1, "NA", "NA", "NA", sdf.format(System.currentTimeMillis()), sdf.format(System.currentTimeMillis()), 0, qty, item.getUomId(), item.getIgst(), 0, 0, 0, 0, 0, 0, item.getRoyaltyRate(), 0, item.getItemName(), item.getUomName(), item.getFreightRate());

                    if (staticQuotationDetailList != null) {
                        boolean flag = false;
                        int pos = 0;
                        if (staticQuotationDetailList.size() > 0) {
                            for (int i = 0; i < staticQuotationDetailList.size(); i++) {
                                if (item.getItemId() == staticQuotationDetailList.get(i).getItemId()) {
                                    flag = true;
                                    pos = i;
                                    break;
                                } else {
                                    flag = false;
                                }
                            }
                        }

                        if (flag) {
                            staticQuotationDetailList.get(pos).setQuotQty(qty);
                        } else {
                            staticQuotationDetailList.add(detail);
                        }
                    }

                    openDialog.dismiss();
                    openItemDialog.dismiss();

                    Intent itemDataIntent = new Intent();
                    itemDataIntent.setAction("QUOTATION_ITEM_DATA");
                    LocalBroadcastManager.getInstance(context).sendBroadcast(itemDataIntent);

                }
            }
        });


        openDialog.show();

    }

}
