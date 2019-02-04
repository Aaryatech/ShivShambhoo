package com.ats.shivshambhoo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.activity.PlantSelectionActivity;
import com.ats.shivshambhoo.adapter.PlantSelectionAdapter;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.model.Cust;
import com.ats.shivshambhoo.model.CustomerType;
import com.ats.shivshambhoo.model.Info;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.model.User;
import com.ats.shivshambhoo.util.CommonDialog;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.ats.shivshambhoo.util.ValidationFile;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerFragment extends Fragment implements View.OnClickListener {

    private EditText edName, edRespPerson, edMobile, edEmail, edAddress, edPAN, edGST;
    private Spinner spType;
    private Button btnSubmit;
    private TextView tvPlantName;

    ArrayList<String> typeNameArray = new ArrayList<>();
    ArrayList<Integer> typeIdArray = new ArrayList<>();

    int plantId;
    Cust customer;
    Plant plant;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_customer, container, false);

        getActivity().setTitle("New Customer");

        edName = view.findViewById(R.id.edName);
        edRespPerson = view.findViewById(R.id.edRespPerson);
        edMobile = view.findViewById(R.id.edMobile);
        edEmail = view.findViewById(R.id.edEmail);
        edAddress = view.findViewById(R.id.edAddress);
        edPAN = view.findViewById(R.id.edPAN);
        edGST = view.findViewById(R.id.edGST);

        spType = view.findViewById(R.id.spType);

        tvPlantName = view.findViewById(R.id.tvPlantName);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        try {
            String plantStr = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_PLANT);
            Gson gsonPlant = new Gson();
            plant = gsonPlant.fromJson(plantStr, Plant.class);
            tvPlantName.setText("" + plant.getPlantName());
        } catch (Exception e) {
        }

        plantId = CustomSharedPreference.getInt(getActivity(), CustomSharedPreference.KEY_PLANT_ID);

        getCustomerTypeList();

        try {
            String customerStr = getArguments().getString("model");
            Gson gson = new Gson();
            customer = gson.fromJson(customerStr, Cust.class);

            if (customer != null) {

                edName.setText("" + customer.getCustName());
                edRespPerson.setText("" + customer.getRespPerson());
                edMobile.setText("" + customer.getCustMobNo());
                edEmail.setText("" + customer.getCustEmail());
                edAddress.setText("" + customer.getCustAddress());
                edPAN.setText("" + customer.getCustPanNo());
                edGST.setText("" + customer.getCustGstNo());

            }
        } catch (Exception e) {
            Log.e("ADD CUST FRG", " ------- EXCEPTION " + e.getMessage());
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSubmit) {

            String strName, strRespPerson, strMobile, strEmail, strAddress, strPAN, strGST;

            boolean isValidName = false, isValidRespPerson = false, isValidMobile = false, isValidEmail = false, isValidPANCard = false, isValidGSTNo = false;

            strName = edName.getText().toString().trim();
            strRespPerson = edRespPerson.getText().toString().trim();
            strMobile = edMobile.getText().toString().trim();
            strEmail = edEmail.getText().toString().trim();
            strAddress = edAddress.getText().toString().trim();
            strPAN = edPAN.getText().toString().trim();
            strGST = edGST.getText().toString().trim();

            if (strName.isEmpty()) {
                edName.setError("required");
            } else if ((new ValidationFile().isValidNameString(strName))) {
                edName.setError("invalid string");
            } else {
                edName.setError(null);
                isValidName = true;
            }

            if (strRespPerson.isEmpty()) {
                edRespPerson.setError("required");
            } else if ((new ValidationFile().isValidNameString(strRespPerson))) {
                edName.setError("invalid string");
            } else {
                edRespPerson.setError(null);
                isValidRespPerson = true;
            }

            if (strMobile.isEmpty()) {
                edMobile.setError("required");
            } else if (strMobile.length() != 10) {
                edMobile.setError("required 10 digits");
            } else if (strMobile.equalsIgnoreCase("0000000000")) {
                edMobile.setError("invalid number");
            } else {
                edMobile.setError(null);
                isValidMobile = true;
            }

            if (strEmail.isEmpty()) {
                edEmail.setError("required");
            } else if (!(new ValidationFile().isValidEmailAddress(strEmail))) {
                edEmail.setError("Invalid email address");
            } else {
                edEmail.setError(null);
                isValidEmail = true;
            }

            if (!strPAN.isEmpty()) {
                if (!(new ValidationFile().isValidPAN(strPAN))) {
                    edPAN.setError("Invalid PAN");
                } else {
                    edPAN.setError(null);
                    isValidPANCard = true;
                }
            } else {
                isValidPANCard = true;
            }

            if (!strGST.isEmpty()) {
                if (!(new ValidationFile().isValidGST(strGST))) {
                    edGST.setError("Invalid GST number");
                } else {
                    edGST.setError(null);
                    isValidGSTNo = true;
                }
            } else {
                isValidGSTNo = true;
            }


            int typeId = 0;

            if (typeIdArray != null) {
                if (typeIdArray.size() > 0) {
                    typeId = typeIdArray.get(spType.getSelectedItemPosition());
                }
            }


            if (isValidName && isValidRespPerson && isValidMobile && isValidEmail && isValidPANCard && isValidGSTNo) {

                int plantId = CustomSharedPreference.getInt(getActivity(), CustomSharedPreference.KEY_PLANT_ID);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                int custId = 0;
                if (customer != null) {
                    custId = customer.getCustId();
                }

                Cust cust = new Cust(custId, strName, strRespPerson, strMobile, strEmail, strAddress, strPAN, strGST, typeId, 1, plantId, sdf.format(System.currentTimeMillis()));

                if (typeId != 0) {
                    checkCustomerExists(strMobile, cust);
                    //saveCustomer(cust);
                } else {
                    Toast.makeText(getContext(), "Please select type", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


    public void getCustomerTypeList() {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<CustomerType>> listCall = Constants.myInterface.getCustomerTypeList();
            listCall.enqueue(new Callback<ArrayList<CustomerType>>() {
                @Override
                public void onResponse(Call<ArrayList<CustomerType>> call, Response<ArrayList<CustomerType>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("CUSTOMER TYPE LIST : ", " - " + response.body());

                            typeNameArray.clear();
                            typeIdArray.clear();

                            typeNameArray.add("Select");
                            typeIdArray.add(0);

                            for (int i = 0; i < response.body().size(); i++) {
                                typeNameArray.add(response.body().get(i).getCustTypeName());
                                typeIdArray.add(response.body().get(i).getCustTypeId());
                            }

                            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, typeNameArray);
                            spType.setAdapter(spinnerAdapter);

                            if (customer != null) {
                                int position = 0;
                                if (typeIdArray.size() > 0) {
                                    for (int i = 0; i < typeIdArray.size(); i++) {
                                        if (customer.getCustType() == typeIdArray.get(i)) {
                                            position = i;
                                            break;
                                        }
                                    }
                                }
                                spType.setSelection(position);
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
                public void onFailure(Call<ArrayList<CustomerType>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveCustomer(Cust cust) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<Cust> listCall = Constants.myInterface.saveCustomer(cust);
            listCall.enqueue(new Callback<Cust>() {
                @Override
                public void onResponse(Call<Cust> call, Response<Cust> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("SAVE CUSTOMER : ", " - " + response.body());

                            if (response.body().getCustId() != 0) {
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
                                ft.commit();
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
                public void onFailure(Call<Cust> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkCustomerExists(String mobile, final Cust cust) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<Info> listCall = Constants.myInterface.checkCustomerExists(mobile);
            listCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("CHECK CUSTOMER : ", " - " + response.body());

                            Info info = response.body();

                            if (info.isError()) {
                                Toast.makeText(getContext(), "" + info.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                saveCustomer(cust);
                            }


                            commonDialog.dismiss();

                        } else {
                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                            Toast.makeText(getContext(), "Unable to process", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        Toast.makeText(getContext(), "Unable to process", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Info> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    Toast.makeText(getContext(), "Unable to process", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


}
