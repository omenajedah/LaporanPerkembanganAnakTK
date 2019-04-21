package com.niken.perkembangananak.model;


import com.niken.perkembangananak.BR;

import org.json.JSONObject;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

/**
 * Created by Firman on 4/17/2019.
 */
public class Guru extends BaseObservable {

    private String C_NIP;
    private String V_NAMA;
    private String C_JENKEL;
    private String C_STATUS_AJAR;
    private String D_TGL_MASUK;

    public static Guru fromJson(JSONObject jsonObject) {
        Guru guru = new Guru();
        guru.setC_NIP(jsonObject.optString("C_NIP"));
        guru.setV_NAMA(jsonObject.optString("V_NAMA"));
        guru.setC_JENKEL(jsonObject.optString("C_JENKEL"));
        guru.setC_STATUS_AJAR(jsonObject.optString("C_STATUS_AJAR"));
        guru.setD_TGL_MASUK(jsonObject.optString("D_TGL_MASUK"));
        return guru;
    }


    @Bindable
    public String getC_NIP() {
        return C_NIP;
    }

    public void setC_NIP(String C_NIP) {
        this.C_NIP = C_NIP;
        notifyPropertyChanged(BR.c_NIP);
    }

    @Bindable
    public String getV_NAMA() {
        return V_NAMA;
    }

    public void setV_NAMA(String V_NAMA) {
        this.V_NAMA = V_NAMA;
        notifyPropertyChanged(BR.v_NAMA);
    }

    @Bindable
    public String getC_JENKEL() {
        return C_JENKEL;
    }

    public void setC_JENKEL(String C_JENKEL) {
        this.C_JENKEL = C_JENKEL;
        notifyPropertyChanged(BR.c_JENKEL);
    }

    @Bindable
    public String getC_STATUS_AJAR() {
        return C_STATUS_AJAR;
    }

    public void setC_STATUS_AJAR(String C_STATUS_AJAR) {
        this.C_STATUS_AJAR = C_STATUS_AJAR;
        notifyPropertyChanged(BR.c_STATUS_AJAR);
    }

    @Bindable
    public String getD_TGL_MASUK() {
        return D_TGL_MASUK;
    }

    public void setD_TGL_MASUK(String D_TGL_MASUK) {
        this.D_TGL_MASUK = D_TGL_MASUK;
        notifyPropertyChanged(BR.d_TGL_MASUK);
    }
}
