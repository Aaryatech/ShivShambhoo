package com.ats.shivshambhoo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ats.shivshambhoo.R;
import com.ats.shivshambhoo.model.User;
import com.ats.shivshambhoo.util.CustomSharedPreference;
import com.google.gson.Gson;

public class UserFragment extends Fragment {

    private EditText edName, edPwd, edMobile, edEmail;

    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        getActivity().setTitle("User");

        edName = view.findViewById(R.id.edName);
        edPwd = view.findViewById(R.id.edPwd);
        edMobile = view.findViewById(R.id.edMobile);
        edEmail = view.findViewById(R.id.edEmail);

        String userStr = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_USER);
        Gson gson = new Gson();
        user = gson.fromJson(userStr, User.class);
        Log.e("USER FRAGMENT : ", "--------USER-------" + user);

        if (user != null) {

            edName.setText("" + user.getUsrName());
            edPwd.setText("" + user.getUserPass());
            edMobile.setText("" + user.getUsrMob());
            edEmail.setText("" + user.getUsrEmail());

        }

        return view;
    }

}
