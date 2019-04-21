package com.niken.perkembangananak.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import com.niken.perkembangananak.BR;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firman on 4/21/2019.
 */
public class Profile extends BaseObservable implements Observable {

    private String C_USER;
    private String C_LOGIN;
    private String V_PASSWORD;
    private String V_NAMA;
    private String V_ALAMAT;
    private int C_GROUP;
    private List<Kontak> kontaks = new ArrayList<>();

    public static Profile fromJson(JSONObject object) {
        Profile profile = new Profile();
        profile.setC_USER(object.optString("C_USER"));
        profile.setC_LOGIN(object.optString("C_LOGIN"));
        profile.setV_PASSWORD(object.optString("V_PASSWORD"));
        profile.setV_NAMA(object.optString("V_NAMA"));
        profile.setV_ALAMAT(object.optString("V_ALAMAT"));
        profile.setC_GROUP(object.optInt("C_GROUP"));

        JSONArray kontakArray = object.optJSONArray("kontak");
        List<Kontak> kontaks = new ArrayList<>();
        for (int i=0;i<kontakArray.length();i++) {

            JSONObject item = kontakArray.optJSONObject(i);
            Kontak kontak = new Kontak();
            kontak.setC_USER(item.optString("C_USER"));
            kontak.setC_TIPE(item.optString("C_TIPE"));
            kontak.setV_KONTAK(item.optString("V_KONTAK"));
            kontaks.add(kontak);
        }

        profile.setKontaks(kontaks);

        return profile;
    }

    public void copy(Profile profile) {
        setC_USER(profile.C_USER);
        setC_LOGIN(profile.C_LOGIN);
        setV_PASSWORD(profile.V_PASSWORD);
        setV_NAMA(profile.V_NAMA);
        setV_ALAMAT(profile.V_ALAMAT);
        setC_GROUP(profile.C_GROUP);
        setKontaks(profile.kontaks);
    }

    @Bindable
    public String getC_USER() {
        return C_USER;
    }

    public void setC_USER(String C_USER) {
        this.C_USER = C_USER;
        notifyPropertyChanged(BR.c_USER);
    }

    @Bindable
    public String getC_LOGIN() {
        return C_LOGIN;
    }

    public void setC_LOGIN(String C_LOGIN) {
        this.C_LOGIN = C_LOGIN;
        notifyPropertyChanged(BR.c_LOGIN);
    }

    @Bindable
    public String getV_PASSWORD() {
        return V_PASSWORD;
    }

    public void setV_PASSWORD(String V_PASSWORD) {
        this.V_PASSWORD = V_PASSWORD;
        notifyPropertyChanged(BR.v_PASSWORD);
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
    public String getV_ALAMAT() {
        return V_ALAMAT;
    }

    public void setV_ALAMAT(String V_ALAMAT) {
        this.V_ALAMAT = V_ALAMAT;
        notifyPropertyChanged(BR.v_ALAMAT);
    }

    @Bindable
    public int getC_GROUP() {
        return C_GROUP;
    }

    public void setC_GROUP(int C_GROUP) {
        this.C_GROUP = C_GROUP;
        notifyPropertyChanged(BR.c_GROUP);
    }

    @Bindable
    public List<Kontak> getKontaks() {
        return kontaks;
    }

    public void setKontaks(List<Kontak> kontaks) {
        this.kontaks = kontaks;
        notifyPropertyChanged(BR.kontaks);
    }



    static class Kontak extends BaseObservable {
        private String C_USER;
        private String C_TIPE;
        private String V_KONTAK;


        @Bindable
        public String getC_USER() {
            return C_USER;
        }

        @Bindable
        public String getC_TIPE() {
            return C_TIPE;
        }

        @Bindable
        public String getV_KONTAK() {
            return V_KONTAK;
        }

        public void setC_TIPE(String c_TIPE) {
            C_TIPE = c_TIPE;
            notifyPropertyChanged(BR.c_TIPE);
        }

        public void setC_USER(String c_USER) {
            C_USER = c_USER;
            notifyPropertyChanged(BR.c_USER);
        }

        public void setV_KONTAK(String v_KONTAK) {
            V_KONTAK = v_KONTAK;
            notifyPropertyChanged(BR.v_KONTAK);
        }
    }

}
