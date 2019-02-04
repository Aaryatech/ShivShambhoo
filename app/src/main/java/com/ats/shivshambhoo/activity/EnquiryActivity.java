package com.ats.shivshambhoo.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.adapter.EnquiryListAdapter;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.model.EnquiryHeader;
import com.ats.shivshambhoo.model.EnquirySource;
import com.ats.shivshambhoo.model.Info;
import com.ats.shivshambhoo.model.Plant;
import com.ats.shivshambhoo.model.User;
import com.ats.shivshambhoo.util.CommonDialog;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnquiryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edRemark;
    private TextView tvCustName, tvEnqNo, tvDate, tvPlantName;
    private Button btnSubmit;
    private Spinner spPriority, spSource;
    private EditText edDate;

    EnquiryHeader enquiryHeader;

    ArrayList<String> priorityArray = new ArrayList<>();
    ArrayList<String> sourceNameList = new ArrayList<>();
    ArrayList<Integer> sourceIdList = new ArrayList<>();

    long dateMillis;
    int yyyy, mm, dd;

    Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);

        setTitle("Enquiry");

        edRemark = findViewById(R.id.edRemark);
        edDate = findViewById(R.id.edDate);
        tvCustName = findViewById(R.id.tvCustName);
        tvEnqNo = findViewById(R.id.tvEnqNo);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        edDate.setOnClickListener(this);

        tvPlantName = findViewById(R.id.tvPlantName);

        spPriority = findViewById(R.id.spPriority);
        spSource = findViewById(R.id.spSource);
        tvDate = findViewById(R.id.tvDate);

        try {
            String plantStr = CustomSharedPreference.getString(this, CustomSharedPreference.KEY_PLANT);
            Gson gsonPlant = new Gson();
            plant = gsonPlant.fromJson(plantStr, Plant.class);
            tvPlantName.setText("" + plant.getPlantName());
        } catch (Exception e) {
        }

        String custName = getIntent().getStringExtra("customer");
        String enqStr = getIntent().getStringExtra("enquiry");
        Gson gson = new Gson();
        enquiryHeader = gson.fromJson(enqStr, EnquiryHeader.class);

        if (enquiryHeader != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

            tvCustName.setText("" + custName);
            edDate.setText("" + sdf.format(System.currentTimeMillis()));
            tvDate.setText("" + sdf1.format(System.currentTimeMillis()));
            tvEnqNo.setText("" + enquiryHeader.getEnqNo());

        }

        getEnquirySourceList();

        priorityArray.clear();
        priorityArray.add("Low");
        priorityArray.add("Medium");
        priorityArray.add("High");

        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorityArray);
        spPriority.setAdapter(priorityAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.edDate) {

            int yr, mn, dy;
            if (dateMillis > 0) {
                Calendar purchaseCal = Calendar.getInstance();
                purchaseCal.setTimeInMillis(dateMillis);
                yr = purchaseCal.get(Calendar.YEAR);
                mn = purchaseCal.get(Calendar.MONTH);
                dy = purchaseCal.get(Calendar.DAY_OF_MONTH);
            } else {
                Calendar purchaseCal = Calendar.getInstance();
                yr = purchaseCal.get(Calendar.YEAR);
                mn = purchaseCal.get(Calendar.MONTH);
                dy = purchaseCal.get(Calendar.DAY_OF_MONTH);
            }
            DatePickerDialog dialog = new DatePickerDialog(EnquiryActivity.this, dateListener, yr, mn, dy);
            dialog.show();

        } else if (view.getId() == R.id.btnSubmit) {

            String remark = edRemark.getText().toString().trim();

            int priority = spPriority.getSelectedItemPosition();
            int source = 0;
            try {
                source = spSource.getSelectedItemPosition();
            } catch (Exception e) {
            }

           /* if (remark.isEmpty()) {
                edRemark.setError("required");
            } else {
                edRemark.setError(null);
*/
            String date = tvDate.getText().toString();

            if (source == 0) {
                Toast.makeText(this, "please select enquiry source", Toast.LENGTH_SHORT).show();
            } else {
                updateEnquiryRemark(enquiryHeader.getEnqHeadId(), remark, date, priority, source);
            }
            //          }

        }
    }

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yyyy = year;
            mm = month + 1;
            dd = dayOfMonth;
            edDate.setText(dd + "-" + mm + "-" + yyyy);
            tvDate.setText(yyyy + "-" + mm + "-" + dd);

            Calendar calendar = Calendar.getInstance();
            calendar.set(yyyy, mm - 1, dd);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR, 0);
            dateMillis = calendar.getTimeInMillis();
        }
    };


    public void updateEnquiryRemark(int enqId, String remark, String date, int priority, int source) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<Info> listCall = Constants.myInterface.updateEnquiryRemark(enqId, remark, date, source, priority);
            listCall.enqueue(new Callback<Info>() {
                @Override
                public void onResponse(Call<Info> call, Response<Info> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("UPDATE REMARK: ", " - " + response.body());

                            if (!response.body().isError()) {
                                Toast.makeText(EnquiryActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                ft.replace(R.id.content_frame, new CustomerListFragment(), "HomeFragment");
//                                ft.commit();

                                Intent intent = new Intent(EnquiryActivity.this, HomeActivity.class);
                                intent.putExtra("type", 1);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            } else {
                                Toast.makeText(EnquiryActivity.this, "Unable to process", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(EnquiryActivity.this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


    public void getEnquirySourceList() {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<EnquirySource>> listCall = Constants.myInterface.getEnquirySourceList();
            listCall.enqueue(new Callback<ArrayList<EnquirySource>>() {
                @Override
                public void onResponse(Call<ArrayList<EnquirySource>> call, Response<ArrayList<EnquirySource>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("ENQUIRY SOURCE LIST : ", " - " + response.body());

                            sourceNameList.clear();
                            sourceIdList.clear();

                            sourceIdList.add(0);
                            sourceNameList.add("select");

                            if (response.body().size() > 0) {
                                for (int i = 0; i < response.body().size(); i++) {
                                    sourceIdList.add(response.body().get(i).getEnqGenId());
                                    sourceNameList.add(response.body().get(i).getEnqGenBy());
                                }

                                ArrayAdapter<String> sourceAdapter = new ArrayAdapter<>(EnquiryActivity.this, android.R.layout.simple_spinner_dropdown_item, sourceNameList);
                                spSource.setAdapter(sourceAdapter);
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
                public void onFailure(Call<ArrayList<EnquirySource>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(EnquiryActivity.this, R.style.AlertDialogTheme);
        builder.setTitle("Caution");
        builder.setMessage("Remark not inserted, do you want to exit");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(EnquiryActivity.this, HomeActivity.class);
                intent.putExtra("type", 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
