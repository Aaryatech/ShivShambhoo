package com.ats.shivshambhoo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ats.shivshambhoo.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llModerate, llConservative, llAggressive;
    private TextView tvModerate, tvConservative, tvAggressive, tvModAmt, tvConsAmt, tvAggAmt, tvModPer, tvConsPer, tvAggPer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        llModerate = findViewById(R.id.llModerate);
        llConservative = findViewById(R.id.llConservative);
        llAggressive = findViewById(R.id.llAggressive);

        tvModerate = findViewById(R.id.tvModerate);
        tvConservative = findViewById(R.id.tvConservative);
        tvAggressive = findViewById(R.id.tvAggressive);
        tvModAmt = findViewById(R.id.tvModAmt);
        tvConsAmt = findViewById(R.id.tvConsAmt);
        tvAggAmt = findViewById(R.id.tvAggAmt);
        tvModPer = findViewById(R.id.tvModPer);
        tvConsPer = findViewById(R.id.tvConsPer);
        tvAggPer = findViewById(R.id.tvAggPer);

        llModerate.setOnClickListener(this);
        llConservative.setOnClickListener(this);
        llAggressive.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.llModerate) {

            tvConservative.setTextSize(15);
            tvConsAmt.setTextSize(15);
            tvConsPer.setTextSize(12);

            tvAggressive.setTextSize(15);
            tvAggAmt.setTextSize(15);
            tvAggPer.setTextSize(12);

            tvModerate.setTextSize(22);
            tvModAmt.setTextSize(22);
            tvModPer.setTextSize(18);

        } else if (view.getId() == R.id.llConservative) {

            tvModerate.setTextSize(15);
            tvModAmt.setTextSize(15);
            tvModPer.setTextSize(12);

            tvAggressive.setTextSize(15);
            tvAggAmt.setTextSize(15);
            tvAggPer.setTextSize(12);

            tvConservative.setTextSize(22);
            tvConsAmt.setTextSize(22);
            tvConsPer.setTextSize(18);

        } else if (view.getId() == R.id.llAggressive) {

            tvConservative.setTextSize(15);
            tvConsAmt.setTextSize(15);
            tvConsPer.setTextSize(12);

            tvModerate.setTextSize(15);
            tvModAmt.setTextSize(15);
            tvModPer.setTextSize(12);

            tvAggressive.setTextSize(22);
            tvAggAmt.setTextSize(22);
            tvAggPer.setTextSize(18);

        }
    }

}
