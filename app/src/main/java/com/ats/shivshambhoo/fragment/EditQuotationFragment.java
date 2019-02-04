package com.ats.shivshambhoo.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.activity.QuotationActivity;
import com.ats.shivshambhoo.adapter.CustomerListAdapter;
import com.ats.shivshambhoo.adapter.ItemDialogAdapter;
import com.ats.shivshambhoo.adapter.ItemListForEnquiryAdapter;
import com.ats.shivshambhoo.adapter.QuotationItemAdapter;
import com.ats.shivshambhoo.adapter.QuotationListAdapter;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.model.Cust;
import com.ats.shivshambhoo.model.EnquiryHeader;
import com.ats.shivshambhoo.model.GetCust;
import com.ats.shivshambhoo.model.GetItemTax;
import com.ats.shivshambhoo.model.GetQuotDetail;
import com.ats.shivshambhoo.model.ItemsByPlant;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.model.Project;
import com.ats.shivshambhoo.model.QuotDetail;
import com.ats.shivshambhoo.model.QuotationHeader;
import com.ats.shivshambhoo.model.QuotationHeaderDisp;
import com.ats.shivshambhoo.model.User;
import com.ats.shivshambhoo.util.CommonDialog;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQuotationFragment extends Fragment implements View.OnClickListener {

    private TextView tvPlantName, tvQuoteNo, tvDate, tvCustName;
    private RadioButton rbOnSite, rbLocation, rbYes, rbNo;
    private Button btnSubmit, btnAddItem;
    private LinearLayout llLocation;
    private EditText edKM, edTollCount, edTollAmt;
    private RecyclerView recyclerView;

    QuotationHeaderDisp quotationHeader;
    Plant plant;

    QuotationItemAdapter adapter;

    public static ArrayList<GetQuotDetail> staticQuotationDetailList = new ArrayList<>();
    ArrayList<GetItemTax> itemList = new ArrayList<>();

    static float staticKM;
    static int staticIsTaxExtras;

    public static Dialog openItemDialog;

    private BroadcastReceiver mBroadcastReceiver;

    User user;
    int quotStatus = 0, allQuote = 0, emailQuote = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_quotation, container, false);

        getActivity().setTitle("Quotation Edit");

        String userStr = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_USER);
        Gson gson = new Gson();
        user = gson.fromJson(userStr, User.class);

        tvPlantName = view.findViewById(R.id.tvPlantName);
        tvQuoteNo = view.findViewById(R.id.tvQuoteNo);
        tvDate = view.findViewById(R.id.tvDate);
        tvCustName = view.findViewById(R.id.tvCustName);

        rbOnSite = view.findViewById(R.id.rbOnSite);
        rbLocation = view.findViewById(R.id.rbLocation);
        rbYes = view.findViewById(R.id.rbYes);
        rbNo = view.findViewById(R.id.rbNo);


        rbOnSite.setChecked(true);

        rbYes.setChecked(true);
        if (rbYes.isChecked()) {
            staticIsTaxExtras = 1;
            if (staticQuotationDetailList != null) {
                if (staticQuotationDetailList.size() > 0) {
                    for (int i = 0; i < staticQuotationDetailList.size(); i++) {
                        GetQuotDetail detail = staticQuotationDetailList.get(i);

                        float taxValue = detail.getSgstPer() + detail.getCgstPer();
                        detail.setTaxValue(taxValue);

                    }
                }
            }
        }

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnAddItem = view.findViewById(R.id.btnAddItem);
        btnSubmit.setOnClickListener(this);
        btnAddItem.setOnClickListener(this);

        llLocation = view.findViewById(R.id.llLocation);

        edKM = view.findViewById(R.id.edKM);
        edTollCount = view.findViewById(R.id.edTollCount);
        edTollAmt = view.findViewById(R.id.edTollAmt);

        recyclerView = view.findViewById(R.id.recyclerView);

        quotStatus = getArguments().getInt("type");
        allQuote = getArguments().getInt("allQuote");
        emailQuote = getArguments().getInt("email");

        if (quotStatus == 0) {
            btnSubmit.setText("Submit");
            getActivity().setTitle("Quotation Edit");
        } else if (quotStatus == 1) {

            if (allQuote == 0) {
                btnSubmit.setText("Generate Quotation");
                getActivity().setTitle("Generate Quotation");
            } else {
                btnSubmit.setText("Submit");
                getActivity().setTitle("Quotation Edit");
            }

        }

        try {
            String plantStr = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_PLANT);
            Gson gsonPlant = new Gson();
            plant = gsonPlant.fromJson(plantStr, Plant.class);

            if (plant != null) {
                getItemList();
            }

        } catch (Exception e) {
        }

        rbLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    llLocation.setVisibility(View.VISIBLE);
                }
            }
        });

        rbOnSite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    llLocation.setVisibility(View.GONE);
                    edKM.setText("0");
                    edTollAmt.setText("0");
                    edTollCount.setText("0");
                }
            }
        });

        rbYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    staticIsTaxExtras = 1;

                    if (staticQuotationDetailList != null) {
                        if (staticQuotationDetailList.size() > 0) {
                            for (int i = 0; i < staticQuotationDetailList.size(); i++) {
                                GetQuotDetail detail = staticQuotationDetailList.get(i);

                                float taxValue = detail.getSgstPer() + detail.getCgstPer();
                                detail.setTaxValue(taxValue);

                            }
                        }
                    }
                    // adapter.notifyDataSetChanged();
                    adapter = new QuotationItemAdapter(staticQuotationDetailList, getContext(), staticIsTaxExtras, staticKM);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        rbNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    staticIsTaxExtras = 0;

                    if (staticQuotationDetailList != null) {
                        if (staticQuotationDetailList.size() > 0) {
                            for (int i = 0; i < staticQuotationDetailList.size(); i++) {
                                GetQuotDetail detail = staticQuotationDetailList.get(i);

                                //float taxValue = detail.getSgstPer() + detail.getCgstPer();
                                detail.setTaxValue(0);

                            }
                        }
                    }
                    // adapter.notifyDataSetChanged();

                    adapter = new QuotationItemAdapter(staticQuotationDetailList, getContext(), staticIsTaxExtras, staticKM);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        edKM.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    staticKM = Float.parseFloat(charSequence.toString());

                    adapter = new QuotationItemAdapter(staticQuotationDetailList, getContext(), staticIsTaxExtras, staticKM);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    staticKM = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edTollAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    float tollCost = Float.parseFloat(charSequence.toString());

                    if (staticQuotationDetailList != null) {
                        if (staticQuotationDetailList.size() > 0) {
                            for (int j = 0; j < staticQuotationDetailList.size(); j++) {
                                GetQuotDetail detail = staticQuotationDetailList.get(j);

                                detail.setTollCost(tollCost);
                            }
                            adapter = new QuotationItemAdapter(staticQuotationDetailList, getContext(), staticIsTaxExtras, staticKM);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);
                        }
                    }


                } catch (Exception e) {

                    if (staticQuotationDetailList != null) {
                        if (staticQuotationDetailList.size() > 0) {
                            for (int j = 0; j < staticQuotationDetailList.size(); j++) {
                                GetQuotDetail detail = staticQuotationDetailList.get(j);

                                detail.setTollCost(0);
                            }
                            adapter = new QuotationItemAdapter(staticQuotationDetailList, getContext(), staticIsTaxExtras, staticKM);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);
                        }
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("QUOTATION_ITEM_DATA")) {
                    handlePushNotification(intent);
                }
            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mBroadcastReceiver,
                new IntentFilter("QUOTATION_ITEM_DATA"));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mBroadcastReceiver);
    }

    private void handlePushNotification(Intent intent) {
        Log.e("handlePushNotification", "------------------------------------**********");

        adapter = new QuotationItemAdapter(staticQuotationDetailList, getContext(), staticIsTaxExtras, staticKM);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void getItemList() {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<GetItemTax>> listCall = Constants.myInterface.getItemsTax(plant.getPlantId());
            listCall.enqueue(new Callback<ArrayList<GetItemTax>>() {
                @Override
                public void onResponse(Call<ArrayList<GetItemTax>> call, Response<ArrayList<GetItemTax>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("ITEM LIST : ", " - " + response.body());
                            itemList.clear();
                            itemList = response.body();

                            loadData();

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
                public void onFailure(Call<ArrayList<GetItemTax>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadData() {
        try {
            String quoteStr = getArguments().getString("model");
            Gson gson = new Gson();
            quotationHeader = gson.fromJson(quoteStr, QuotationHeaderDisp.class);

            if (quotationHeader != null) {

                Log.e("HEADER : ", "     " + quotationHeader);

                tvQuoteNo.setText("Quotation No. : " + quotationHeader.getQuotNo());
                tvDate.setText("" + quotationHeader.getQuotDate());
                tvCustName.setText("" + quotationHeader.getCustName());

                if (plant != null) {
                    tvPlantName.setText("" + plant.getPlantName());
                }

                if (quotationHeader.getGetQuotDetailList() != null) {
                    if (quotationHeader.getGetQuotDetailList().size() > 0) {

                        GetQuotDetail detail = new GetQuotDetail();
                        if (staticQuotationDetailList != null) {
                            staticQuotationDetailList.clear();
                        } else {
                            staticQuotationDetailList = new ArrayList<>();
                        }

                        for (int i = 0; i < quotationHeader.getGetQuotDetailList().size(); i++) {
                            for (int j = 0; j < itemList.size(); j++) {
                                detail = quotationHeader.getGetQuotDetailList().get(i);

                                if (detail.getItemId() == itemList.get(j).getItemId()) {
                                    Log.e("ITEM MATCHED : ", "-----------------------------------------" + itemList.get(j));
                                    detail.setRate(itemList.get(j).getItemRate1());
                                    detail.setFrRate(itemList.get(j).getFreightRate());
                                    detail.setCgstPer(itemList.get(j).getCgst());
                                    detail.setSgstPer(itemList.get(j).getSgst());
                                    detail.setRoyaltyRate(itemList.get(j).getRoyaltyRate());

                                    staticQuotationDetailList.add(detail);

                                }

                            }

                        }

                        adapter = new QuotationItemAdapter(staticQuotationDetailList, getContext(), staticIsTaxExtras, staticKM);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                    }
                }

            }
        } catch (Exception e) {
            Log.e("EDIT QUOTE FRG", " ------- EXCEPTION " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showItemDialog(int quotHeadId) {

        openItemDialog = new Dialog(getContext(), android.R.style.Theme_Light_NoTitleBar);
        openItemDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openItemDialog.setContentView(R.layout.dialog_show_item);

/*        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.x = 100;
        wlp.y = 100;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);*/

        RecyclerView rvItemList = openItemDialog.findViewById(R.id.recyclerView);

        ItemDialogAdapter itemAdapter = new ItemDialogAdapter(itemList, getContext(), quotHeadId);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvItemList.setLayoutManager(mLayoutManager);
        rvItemList.setItemAnimator(new DefaultItemAnimator());
        rvItemList.setAdapter(itemAdapter);

        openItemDialog.show();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddItem) {
            showItemDialog(quotationHeader.getQuotHeadId());
        } else if (view.getId() == R.id.btnSubmit) {

            if (rbLocation.isChecked()) {

                float km = 0, tollCost = 0;
                int tollCount = 0;

                if (edKM.getText().toString().isEmpty()) {
                    edKM.setError("Required");
                } else if (edTollCount.getText().toString().isEmpty()) {
                    edTollCount.setError("Required");

                    edKM.setError(null);
                    km = Float.parseFloat(edKM.getText().toString());

                } else if (edTollAmt.getText().toString().isEmpty()) {
                    edTollAmt.setError("Required");

                    edTollCount.setError(null);
                    tollCount = Integer.parseInt(edTollCount.getText().toString());

                } else {
                    edTollAmt.setError(null);
                    tollCost = Float.parseFloat(edTollAmt.getText().toString());

                    if (staticQuotationDetailList != null) {

                        ArrayList<QuotDetail> detailList = new ArrayList<>();

                        if (staticQuotationDetailList.size() > 0) {
                            for (int i = 0; i < staticQuotationDetailList.size(); i++) {

                                GetQuotDetail qoutDetail = staticQuotationDetailList.get(i);

                                QuotDetail detail = new QuotDetail(qoutDetail.getQuotDetailId(), qoutDetail.getQuotHeadId(), qoutDetail.getItemId(), qoutDetail.getQuotQty(), qoutDetail.getRate(), qoutDetail.getTaxableValue(), qoutDetail.getTaxValue(), qoutDetail.getTaxId(), qoutDetail.getSgstPer(), qoutDetail.getCgstPer(), qoutDetail.getEnqDetailId(), qoutDetail.getCgstValue(), qoutDetail.getSgstValue(), qoutDetail.getOtherCost(), qoutDetail.getStatus(), qoutDetail.getDelStatus(), qoutDetail.getExVar1(), qoutDetail.getExVar2(), qoutDetail.getExVar3(), qoutDetail.getExDate1(), qoutDetail.getExDate2(), qoutDetail.getConFactor(), qoutDetail.getConvQty(), qoutDetail.getQuotUomId(), qoutDetail.getIgstPer(), qoutDetail.getIgstValue(), qoutDetail.getTotal(), qoutDetail.getTollCost(), qoutDetail.getTransCost(), qoutDetail.getOtherCostBeforeTax(), qoutDetail.getOtherCostAfterTax(), qoutDetail.getRoyaltyRate(), qoutDetail.getNoOfKm());
                                detailList.add(detail);
                            }
                        }

                        QuotationHeader header = new QuotationHeader(quotationHeader.getQuotHeadId(), quotationHeader.getQuotNo(), quotationHeader.getCustId(), user.getUserId(), quotationHeader.getQuotDate(), quotationHeader.getEnqHeadId(), quotationHeader.getCompanyId(), quotationHeader.getPlantIds(), quotStatus, 1, 0, 0, 0, 0, 0, "pay term", "transport term", quotationHeader.getOtherRemark1(), quotationHeader.getOtherRemark2(), quotationHeader.getOtherRemark3(), quotationHeader.getOtherRemark4(), quotationHeader.getProjId(), tollCount, 0, 0, 0, 0, 0, 0, 0, km, detailList);
                        saveQuotation(header);

                        Log.e("QUOTATION ITEMS", "  ------------- " + detailList);
                    }


                }

            } else {

                if (staticQuotationDetailList != null) {

                    ArrayList<QuotDetail> detailList = new ArrayList<>();

                    if (staticQuotationDetailList.size() > 0) {
                        for (int i = 0; i < staticQuotationDetailList.size(); i++) {

                            GetQuotDetail qoutDetail = staticQuotationDetailList.get(i);

                            QuotDetail detail = new QuotDetail(qoutDetail.getQuotDetailId(), qoutDetail.getQuotHeadId(), qoutDetail.getItemId(), qoutDetail.getQuotQty(), qoutDetail.getRate(), qoutDetail.getTaxableValue(), qoutDetail.getTaxValue(), qoutDetail.getTaxId(), qoutDetail.getSgstPer(), qoutDetail.getCgstPer(), qoutDetail.getEnqDetailId(), qoutDetail.getCgstValue(), qoutDetail.getSgstValue(), qoutDetail.getOtherCost(), qoutDetail.getStatus(), qoutDetail.getDelStatus(), qoutDetail.getExVar1(), qoutDetail.getExVar2(), qoutDetail.getExVar3(), qoutDetail.getExDate1(), qoutDetail.getExDate2(), qoutDetail.getConFactor(), qoutDetail.getConvQty(), qoutDetail.getQuotUomId(), qoutDetail.getIgstPer(), qoutDetail.getIgstValue(), qoutDetail.getTotal(), qoutDetail.getTollCost(), qoutDetail.getTransCost(), qoutDetail.getOtherCostBeforeTax(), qoutDetail.getOtherCostAfterTax(), qoutDetail.getRoyaltyRate(), qoutDetail.getNoOfKm());
                            detailList.add(detail);
                        }
                    }

                    QuotationHeader header = new QuotationHeader(quotationHeader.getQuotHeadId(), quotationHeader.getQuotNo(), quotationHeader.getCustId(), user.getUserId(), quotationHeader.getQuotDate(), quotationHeader.getEnqHeadId(), quotationHeader.getCompanyId(), quotationHeader.getPlantIds(), quotStatus, 1, 0, 0, 0, 0, 0, "pay term", "transport term", quotationHeader.getOtherRemark1(), quotationHeader.getOtherRemark2(), quotationHeader.getOtherRemark3(), quotationHeader.getOtherRemark4(), quotationHeader.getProjId(), 0, 0, 0, 0, 0, 0, 0, 0, 0, detailList);
                    saveQuotation(header);

                    Log.e("QUOTATION ITEMS", "  ------------- " + detailList);

                }

            }
        }
    }

    public void saveQuotation(QuotationHeader header) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<QuotationHeader> listCall = Constants.myInterface.saveQuotation(header);
            listCall.enqueue(new Callback<QuotationHeader>() {
                @Override
                public void onResponse(Call<QuotationHeader> call, Response<QuotationHeader> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("SAVE QUOTATION : ", " - " + response.body());

                            if (response.body().getQuotHeadId() != 0) {

                                Gson gson = new Gson();
                                String json = gson.toJson(response.body());

                                Intent intent = new Intent(getContext(), QuotationActivity.class);
                                intent.putExtra("customer", quotationHeader.getCustName());
                                intent.putExtra("model", json);
                                intent.putExtra("allQuote", allQuote);
                                intent.putExtra("email", emailQuote);
                                intent.putExtra("status", quotStatus);
                                startActivity(intent);


                            } else {
                                Toast.makeText(getContext(), "Unable to process", Toast.LENGTH_SHORT).show();
                            }


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
                public void onFailure(Call<QuotationHeader> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


}
