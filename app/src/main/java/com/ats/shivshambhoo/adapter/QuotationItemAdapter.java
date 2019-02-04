package com.ats.shivshambhoo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.GetQuotDetail;

import java.util.ArrayList;

public class QuotationItemAdapter extends RecyclerView.Adapter<QuotationItemAdapter.MyViewHolder> {

    private ArrayList<GetQuotDetail> detailList;
    private Context context;
    private int isTaxExtras;
    private float km;

    public QuotationItemAdapter(ArrayList<GetQuotDetail> detailList, Context context) {
        this.detailList = detailList;
        this.context = context;
    }

    public QuotationItemAdapter(ArrayList<GetQuotDetail> detailList, Context context, int isTaxExtras) {
        this.detailList = detailList;
        this.context = context;
        this.isTaxExtras = isTaxExtras;
    }

    public QuotationItemAdapter(ArrayList<GetQuotDetail> detailList, Context context, int isTaxExtras, float km) {
        this.detailList = detailList;
        this.context = context;
        this.isTaxExtras = isTaxExtras;
        this.km = km;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemName, tvUom, tvRate, tvTaxableValue, tvTaxValue, tvTotal;
        public EditText edQty, edRoyalty, edTransCost, edTollCost, edOtherCost, edCostAfterTax;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            tvItemName = view.findViewById(R.id.tvItemName);
            tvUom = view.findViewById(R.id.tvUom);
            tvRate = view.findViewById(R.id.tvRate);
            tvTaxableValue = view.findViewById(R.id.tvTaxableValue);
            tvTaxValue = view.findViewById(R.id.tvTaxValue);
            tvTotal = view.findViewById(R.id.tvTotal);

            edQty = view.findViewById(R.id.edQty);
            edRoyalty = view.findViewById(R.id.edRoyalty);
            edTransCost = view.findViewById(R.id.edTransCost);
            edTollCost = view.findViewById(R.id.edTollCost);
            edOtherCost = view.findViewById(R.id.edOtherCost);
            edCostAfterTax = view.findViewById(R.id.edCostAfterTax);

            cardView = view.findViewById(R.id.cardView);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_quotation_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final GetQuotDetail model = detailList.get(position);
        Log.e("Detail : ", " ---------------- " + model);

        holder.tvItemName.setText("" + model.getItemName());
        holder.tvUom.setText("" + model.getUomName());
        holder.tvRate.setText("Rate : " + model.getRate() + "/-");
        holder.edQty.setText("" + model.getQuotQty());
        holder.edRoyalty.setText("" + model.getRoyaltyRate());
        holder.edTransCost.setText("" + model.getTransCost());
        holder.edTollCost.setText("" + model.getTollCost());
        holder.edOtherCost.setText("" + model.getOtherCost());
        holder.tvTaxableValue.setText("" + model.getTaxableValue());
        holder.tvTaxValue.setText("" + model.getTaxValue());
        holder.edCostAfterTax.setText("" + model.getOtherCostAfterTax());
        holder.tvTotal.setText("" + model.getTotal());

        Log.e("FR RATE : ", "------------ " + model.getFrRate());

        try {
            float transCost = km * model.getFrRate();
            model.setTransCost(transCost);
            holder.edTransCost.setText("" + model.getTransCost());

            float rate = model.getRate();
            float royalty = model.getRoyaltyRate();
            float trans = model.getTransCost();
            float toll = model.getTollCost();
            float other = model.getOtherCost();

            float taxableCost = rate + royalty + trans + toll + other;
            model.setTaxableValue(taxableCost);
            holder.tvTaxableValue.setText("" + model.getTaxableValue());

            if (isTaxExtras == 1) {
                float taxValue = ((model.getTaxableValue())*(model.getSgstPer() + model.getCgstPer()))/100;
                model.setTaxValue(taxValue);
            } else if (isTaxExtras == 0) {
                model.setTaxValue(0);
            }
            holder.tvTaxValue.setText("" + model.getTaxValue());

            float taxable = model.getTaxableValue();
            float tax = model.getTaxValue();
            float afterTax = model.getOtherCostAfterTax();

            float total = taxable + tax + afterTax;
            model.setTotal(total);
            holder.tvTotal.setText("" + total);

        } catch (Exception e) {
        }

        holder.edQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    float qty = Float.parseFloat(charSequence.toString());
                    model.setQuotQty(qty);

                } catch (Exception e) {
                    float qty = 0;
                    model.setQuotQty(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        holder.edRoyalty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {

                    float royaltyVal = Float.parseFloat(charSequence.toString());
                    model.setRoyaltyRate(royaltyVal);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);


                } catch (Exception e) {

                    model.setRoyaltyRate(0);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        holder.edTollCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {

                    float tollVal = Float.parseFloat(charSequence.toString());
                    model.setTollCost(tollVal);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);

                } catch (Exception e) {

                    model.setTollCost(0);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        holder.edOtherCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {

                    float otherVal = Float.parseFloat(charSequence.toString());
                    model.setOtherCost(otherVal);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);


                } catch (Exception e) {

                    model.setOtherCost(0);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        holder.edCostAfterTax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    float afterTaxVal = Float.parseFloat(charSequence.toString());
                    model.setOtherCostAfterTax(afterTaxVal);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);

                } catch (Exception e) {

                    model.setOtherCostAfterTax(0);

                    float rate = model.getRate();
                    float royalty = model.getRoyaltyRate();
                    float trans = model.getTransCost();
                    float toll = model.getTollCost();
                    float other = model.getOtherCost();

                    float taxableCost = rate + royalty + trans + toll + other;
                    model.setTaxableValue(taxableCost);
                    holder.tvTaxableValue.setText("" + model.getTaxableValue());

                    if (isTaxExtras == 1) {
                        float taxValue = model.getSgstPer() + model.getCgstPer();
                        model.setTaxValue(taxValue);
                    } else if (isTaxExtras == 0) {
                        model.setTaxValue(0);
                    }
                    holder.tvTaxValue.setText("" + model.getTaxValue());

                    float taxable = model.getTaxableValue();
                    float tax = model.getTaxValue();
                    float afterTax = model.getOtherCostAfterTax();

                    float total = taxable + tax + afterTax;
                    model.setTotal(total);
                    holder.tvTotal.setText("" + total);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        if (position % 2 == 0) {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            holder.cardView.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
        }

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
}
