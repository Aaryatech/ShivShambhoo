package com.ats.shivshambhoo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.constants.Constants;
import com.ats.shivshambhoo.fcm.SharedPrefManager;
import com.ats.shivshambhoo.model.LoginResponse;
import com.ats.shivshambhoo.util.CommonDialog;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edMobile, edPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edMobile = findViewById(R.id.edMobile);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            String strMobile, strPass;
            boolean isValidMobile = false, isValidPass = false;

            strMobile = edMobile.getText().toString().trim();
            strPass = edPassword.getText().toString().trim();

            if (strMobile.isEmpty()) {
                edMobile.setError("required");
            } else if (strMobile.length() != 10) {
                edMobile.setError("required 10 digits");
            } else {
                edMobile.setError(null);
                isValidMobile = true;
            }

            if (strPass.isEmpty()) {
                edPassword.setError("required");
            } else {
                edPassword.setError(null);
                isValidPass = true;
            }

            if (isValidMobile && isValidPass) {
                doLogin(strMobile, strPass);
            }


        }
    }

    public void doLogin(String mobile, String pass) {
        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<LoginResponse> listCall = Constants.myInterface.doLogin(mobile, pass);
            listCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("User Data : ", "------------" + response.body());

                            LoginResponse data = response.body();
                            if (data == null) {
                                commonDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
                            } else {

                                if (!data.isError()) {

                                    //Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                    commonDialog.dismiss();

                                    Gson gson = new Gson();
                                    String json = gson.toJson(data.getUser());
                                    CustomSharedPreference.putString(LoginActivity.this, CustomSharedPreference.KEY_USER, json);

                                    String token = SharedPrefManager.getmInstance(LoginActivity.this).getDeviceToken();
                                    Log.e("Token : ", "---------" + token);
                                    //                                   updateToken(data.getCust().getCustId(), token);

                                    startActivity(new Intent(LoginActivity.this, PlantSelectionActivity.class));
                                    finish();


                                } else {
                                    Toast.makeText(LoginActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
                                    commonDialog.dismiss();

                                }
                            }
                        } else {
                            commonDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
                            Log.e("Data Null : ", "-----------");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


}
