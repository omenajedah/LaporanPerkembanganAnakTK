package com.niken.perkembangananak.model;

import androidx.databinding.BaseObservable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firman on 4/21/2019.
 */
public class Wali extends Profile {

    public static Wali fromJson(JSONObject object) {
        Wali wali = new Wali();
        wali.setC_USER(object.optString("C_USER"));
        wali.setC_LOGIN(object.optString("C_LOGIN"));
        wali.setV_PASSWORD(object.optString("V_PASSWORD"));
        wali.setV_NAMA(object.optString("V_NAMA"));
        wali.setV_ALAMAT(object.optString("V_ALAMAT"));
        wali.setC_GROUP(object.optInt("C_GROUP"));

//        JSONArray kontakArray = object.optJSONArray("kontak");
//        List<Kontak> kontaks = new ArrayList<>();
//        for (int i=0;i<kontakArray.length();i++) {
//
//            JSONObject item = kontakArray.optJSONObject(i);
//            Kontak kontak = new Kontak();
//            kontak.setC_USER(item.optString("C_USER"));
//            kontak.setC_TIPE(item.optString("C_TIPE"));
//            kontak.setV_KONTAK(item.optString("V_KONTAK"));
//            kontaks.add(kontak);
//        }

//        wali.setKontaks(kontaks);

        return wali;
    }

    @Override
    public String getC_USER() {
        return super.getC_USER();
    }

    @Override
    public void setC_USER(String C_USER) {
        super.setC_USER(C_USER);
    }

    @Override
    public String getC_LOGIN() {
        return super.getC_LOGIN();
    }

    @Override
    public void setC_LOGIN(String C_LOGIN) {
        super.setC_LOGIN(C_LOGIN);
    }

    @Override
    public String getV_PASSWORD() {
        return super.getV_PASSWORD();
    }

    @Override
    public void setV_PASSWORD(String V_PASSWORD) {
        super.setV_PASSWORD(V_PASSWORD);
    }

    @Override
    public String getV_NAMA() {
        return super.getV_NAMA();
    }

    @Override
    public void setV_NAMA(String V_NAMA) {
        super.setV_NAMA(V_NAMA);
    }

    @Override
    public String getV_ALAMAT() {
        return super.getV_ALAMAT();
    }

    @Override
    public void setV_ALAMAT(String V_ALAMAT) {
        super.setV_ALAMAT(V_ALAMAT);
    }

    @Override
    public int getC_GROUP() {
        return super.getC_GROUP();
    }

    @Override
    public void setC_GROUP(int C_GROUP) {
        super.setC_GROUP(C_GROUP);
    }
}
