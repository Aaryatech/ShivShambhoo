package com.ats.shivshambhoo.util;

import android.util.Log;

public class ValidationFile {

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidPAN(String pan) {
        String ePattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(pan);
        return m.matches();
    }

    public boolean isValidGST(String gst) {
        String ePattern = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}\\d[Z]{1}[A-Z\\d]{1}";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(gst);
        return m.matches();
    }

    public boolean isValidNameString(String name) {
        String ePattern = "[0-9]+";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(name);
        Log.e("OUTPUT - "," ---------- "+m.matches());
        return m.matches();
    }


}
