package com.niken.perkembangananak.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.niken.perkembangananak.BR;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firman on 4/21/2019.
 */
public class Wali extends Profile {

    private int C_STATUS;

    public static Wali fromJson(JSONObject object) {
        Wali wali = new Wali();
        wali.setC_USER(object.optString("C_USER"));
        wali.setC_LOGIN(object.optString("C_LOGIN"));
        wali.setV_PASSWORD(object.optString("V_PASSWORD"));
        wali.setV_NAMA(object.optString("V_NAMA"));
        wali.setV_ALAMAT(object.optString("V_ALAMAT"));
        wali.setC_GROUP(object.optInt("C_GROUP"));
        wali.setC_STATUS(object.optInt("C_STATUS"));
        return wali;
    }

    @Bindable
    public int getC_STATUS() {
        return C_STATUS;
    }

    public void setC_STATUS(int c_STATUS) {
        C_STATUS = c_STATUS;
        notifyPropertyChanged(BR.c_STATUS);
    }
}
