package com.ats.shivshambhoo.fragment;


import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.activity.EnquiryActivity;
import com.ats.shivshambhoo.adapter.CustomerListAdapter;
import com.ats.shivshambhoo.adapter.CustomerListDialogAdapter;
import com.ats.shivshambhoo.adapter.ItemListForEnquiryAdapter;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.model.Cust;
import com.ats.shivshambhoo.model.Document;
import com.ats.shivshambhoo.model.EnqDetail;
import com.ats.shivshambhoo.model.EnquiryHeader;
import com.ats.shivshambhoo.model.Info;
import com.ats.shivshambhoo.model.ItemsByPlant;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.model.QuotDetail;
import com.ats.shivshambhoo.model.QuotationHeader;
import com.ats.shivshambhoo.model.Uom;
import com.ats.shivshambhoo.model.User;
import com.ats.shivshambhoo.util.CommonDialog;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEnquiryFragment extends Fragment implements View.OnClickListener {

    private TextView tvCustName, tvCustId, tvPlantName;
    private RecyclerView recyclerView;
    private Button btnSubmit;

    User user;
    Plant plant;

    int plantId;
    private ArrayList<ItemsByPlant> itemList = new ArrayList<>();
    private ArrayList<Cust> customerList = new ArrayList<>();
    private ArrayList<String> uomNameList = new ArrayList<>();
    private ArrayList<Integer> uomIdList = new ArrayList<>();

    Dialog dialog;

    CustomerListDialogAdapter custAdapter;
    ItemListForEnquiryAdapter itemAdapter;

    private BroadcastReceiver mBroadcastReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_enquiry, container, false);

        getActivity().setTitle("New Enquiry");

        tvCustName = view.findViewById(R.id.tvCustName);
        tvCustId = view.findViewById(R.id.tvCustId);
        tvPlantName = view.findViewById(R.id.tvPlantName);
        tvCustName.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        String userStr = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_USER);
        Gson gson = new Gson();
        user = gson.fromJson(userStr, User.class);

        plantId = CustomSharedPreference.getInt(getActivity(), CustomSharedPreference.KEY_PLANT_ID);

        String plantStr = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_PLANT);
        Gson gsonPlant = new Gson();
        plant = gsonPlant.fromJson(plantStr, Plant.class);
        if (plant != null) {
            tvPlantName.setText("" + plant.getPlantName());
        }


        getCustomerList();
        //getItemList();
        getUOMList();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("CUSTOMER_DATA")) {
                    handlePushNotification(intent);
                }
            }
        };

        return view;
    }


    public void getCustomerList() {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<Cust>> listCall = Constants.myInterface.getCustomerList(plantId);
            listCall.enqueue(new Callback<ArrayList<Cust>>() {
                @Override
                public void onResponse(Call<ArrayList<Cust>> call, Response<ArrayList<Cust>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("CUSTOMER LIST : ", " - " + response.body());
                            customerList.clear();
                            customerList = response.body();

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
                public void onFailure(Call<ArrayList<Cust>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void getItemList() {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<ItemsByPlant>> listCall = Constants.myInterface.getItemsByPlant(plantId);
            listCall.enqueue(new Callback<ArrayList<ItemsByPlant>>() {
                @Override
                public void onResponse(Call<ArrayList<ItemsByPlant>> call, Response<ArrayList<ItemsByPlant>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("ITEM LIST : ", " - " + response.body());
                            itemList.clear();
                            itemList = response.body();

                            if (itemList.size() > 0) {
                                for (int i = 0; i < itemList.size(); i++) {
                                    itemList.get(i).setUomNewId(itemList.get(i).getUomId());
                                    Log.e("ITEM : ", " ---  " + itemList.get(i).getItemName() + "               UOM ID : " + itemList.get(i).getUomId() + "                NEW UOM ID : " + itemList.get(i).getUomNewId());
                                }
                            }

                            itemAdapter = new ItemListForEnquiryAdapter(itemList, uomNameList, uomIdList, getContext());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(itemAdapter);

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
                public void onFailure(Call<ArrayList<ItemsByPlant>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void getUOMList() {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<Uom>> listCall = Constants.myInterface.getUOMList();
            listCall.enqueue(new Callback<ArrayList<Uom>>() {
                @Override
                public void onResponse(Call<ArrayList<Uom>> call, Response<ArrayList<Uom>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("UOM LIST : ", " - " + response.body());
                            uomNameList.clear();
                            uomIdList.clear();

                            for (int i = 0; i < response.body().size(); i++) {
                                uomNameList.add(response.body().get(i).getUomName());
                                uomIdList.add(response.body().get(i).getUomId());
                            }

                            getItemList();

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
                public void onFailure(Call<ArrayList<Uom>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog() {
        dialog = new Dialog(getContext(), android.R.style.Theme_Light_NoTitleBar);
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.custom_dialog_fullscreen_search, null, false);
        dialog.setContentView(v);
        dialog.setCancelable(true);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        RecyclerView rvCustomerList = dialog.findViewById(R.id.rvCustomerList);
        EditText edSearch = dialog.findViewById(R.id.edSearch);

        custAdapter = new CustomerListDialogAdapter(customerList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvCustomerList.setLayoutManager(mLayoutManager);
        rvCustomerList.setItemAnimator(new DefaultItemAnimator());
        rvCustomerList.setAdapter(custAdapter);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (custAdapter != null) {
                        filterCustomer(editable.toString());
                    }
                } catch (Exception e) {
                }
            }
        });

        dialog.show();
    }

    void filterCustomer(String text) {
        ArrayList<Cust> temp = new ArrayList();
        for (Cust d : customerList) {
            if (d.getCustName().toLowerCase().contains(text.toLowerCase()) || d.getCustMobNo().contains(text.toLowerCase())) {
                temp.add(d);
            }
        }
        //update recyclerview
        custAdapter.updateList(temp);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tvCustName) {
            showDialog();
        } else if (view.getId() == R.id.btnSubmit) {

            int custId = 0;
            if (tvCustId.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Please select customer", Toast.LENGTH_SHORT).show();
                tvCustName.setError("please select customer");
            } else {
                tvCustName.setError(null);

                try {
                    custId = Integer.parseInt(tvCustId.getText().toString());
                } catch (Exception e) {
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                if (itemList.size() > 0) {

                    ArrayList<EnqDetail> detailList = new ArrayList<>();

                    for (int i = 0; i < itemList.size(); i++) {
                        if (itemList.get(i).getQty() > 0) {
                            EnqDetail enqDetail = new EnqDetail(0, 0, itemList.get(i).getItemId(), itemList.get(i).getQty(), "Kg", itemList.get(i).getUomId(), 0, 1, 0, itemList.get(i).getUomNewId(), "remark");
                            detailList.add(enqDetail);
                        }
                    }

                    if (detailList.size() > 0) {
                        EnquiryHeader header = new EnquiryHeader(0, "", user.getUserId(), sdf.format(System.currentTimeMillis()), custId, sdf.format(System.currentTimeMillis()), 0, 0, plantId, 0, 0, 1,1, "remark", detailList);

                        getDocument(1, header);

                    } else {
                        Toast.makeText(getContext(), "Please select item", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getContext(), "No Items found", Toast.LENGTH_SHORT).show();
                }


            }

            //   Log.e("SUBMIT : ", " -------------- " + itemList);
        }
    }

    private void handlePushNotification(Intent intent) {
        Log.e("handlePushNotification", "------------------------------------**********");
        dialog.dismiss();
        String name = intent.getStringExtra("name");
        int custId = intent.getIntExtra("id", 0);
        Log.e("CUSTOMER NAME : ", " " + name);
        tvCustName.setText("" + name);
        tvCustId.setText("" + custId);

    }

    public void saveEnquiry(EnquiryHeader header, final int srNo) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<EnquiryHeader> listCall = Constants.myInterface.saveEnquiry(header);
            listCall.enqueue(new Callback<EnquiryHeader>() {
                @Override
                public void onResponse(Call<EnquiryHeader> call, Response<EnquiryHeader> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("SAVE ENQUIRY : ", " - " + response.body());

                            if (response.body().getEnqHeadId() != 0) {
                                //  Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
//                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
//                                ft.commit();

                                updateDocument(1, srNo, response.body());

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
                public void onFailure(Call<EnquiryHeader> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveQuotation(QuotationHeader header, final int srNo, final EnquiryHeader enquiryHeader) {
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
                                //  Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
//                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
//                                ft.commit();

                                updateDocumentForQuotation(2, srNo, enquiryHeader);

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

    public void getDocument(int docCode, final EnquiryHeader header) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<Document> listCall = Constants.myInterface.getDocument(docCode);
            listCall.enqueue(new Callback<Document>() {
                @Override
                public void onResponse(Call<Document> call, Response<Document> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("DOCUMENT : ", " - " + response.body());

                            if (response.body().getDocId() > 0) {
                                int srNO = response.body().getSrNo();

                                String enqNo = response.body().getDocPrefix() + "" + srNO;
                                header.setEnqNo(enqNo);

                                saveEnquiry(header, (srNO + 1));

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
                public void onFailure(Call<Document> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDocumentForQuotation(int docCode, final EnquiryHeader header) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<Document> listCall = Constants.myInterface.getDocument(docCode);
            listCall.enqueue(new Callback<Document>() {
                @Override
                public void onResponse(Call<Document> call, Response<Document> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("DOCUMENT : ", " - " + response.body());

                            if (response.body().getDocId() > 0) {
                                int srNO = response.body().getSrNo();

                                String quotNo = response.body().getDocPrefix() + "" + srNO;

                                if (header.getEnqDetailList().size() > 0) {

                                    ArrayList<QuotDetail> detailList = new ArrayList<>();

                                    for (int i = 0; i < header.getEnqDetailList().size(); i++) {

                                        EnqDetail enqDetail = header.getEnqDetailList().get(i);

                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                        QuotDetail detail = new QuotDetail(0, 0, enqDetail.getItemId(), enqDetail.getItemQty(), 0, 0, 0, 0, 0, 0, enqDetail.getEnqDetailId(), 0, 0, 0, 0, 1, "NA", "NA", "NA", sdf.format(System.currentTimeMillis()), sdf.format(System.currentTimeMillis()), 0, 0, enqDetail.getEnqUomId(), 0, 0, 0, 0, 0, 0, 0, 0, 0);

                                        detailList.add(detail);
                                    }

                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                    QuotationHeader quotationHeader = new QuotationHeader(0, quotNo, header.getCustId(), user.getUserId(), sdf.format(System.currentTimeMillis()), header.getEnqHeadId(), plant.getCompanyId(), plantId, 0, 1, 0, 0, 0, 0, 0, "", "", "remark", "remark", "remark", "remark", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, detailList);
                                    saveQuotation(quotationHeader, (srNO + 1), header);

                                }


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
                public void onFailure(Call<Document> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDocument(int docCode, final int srNo, final EnquiryHeader enquiryHeader) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<Info> listCall = Constants.myInterface.updateDocument(docCode, srNo);
            listCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("UPDATE DOCUMENT: ", " - " + response.body());

                            if (!response.body().isError()) {
                                //  Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
//                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
//                                ft.commit();

                                // updateDocumentForQuotation(2, quotSrNo, enquiryHeader);

                                getDocumentForQuotation(2, enquiryHeader);

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
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDocumentForQuotation(int docCode, int srNo, final EnquiryHeader enquiryHeader) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<Info> listCall = Constants.myInterface.updateDocument(docCode, srNo);
            listCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("UPDATE QUOT DOCUMENT: ", " - " + response.body());

                            if (!response.body().isError()) {
                                //  Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
//                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
//                                ft.commit();

                                Gson gson = new Gson();
                                String json = gson.toJson(enquiryHeader);

                                Intent intent = new Intent(getContext(), EnquiryActivity.class);
                                intent.putExtra("enquiry", json);
                                intent.putExtra("customer", tvCustName.getText().toString());
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
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mBroadcastReceiver,
                new IntentFilter("CUSTOMER_DATA"));
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mBroadcastReceiver);
    }



    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(true);

        SearchView searchView = (SearchView) item.getActionView();
        EditText searchEditText =  searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorWhite));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorWhite));
        ImageView v = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        v.setImageResource(R.drawable.ic_search_white); //Changing the image

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // empAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchView.setQueryHint("search");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


}
