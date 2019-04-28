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
public class Kelas extends BaseObservable {

    private String C_KELAS;
    private String C_NAMA;
    private int N_TINGKAT;
    private String C_STATUS;
    private int N_JUMLAH_SISWA;

    public static Kelas fromJson(JSONObject jsonObject) {
        Kelas kelas = new Kelas();
        kelas.setC_KELAS(jsonObject.optString("C_KELAS"));
        kelas.setC_NAMA(jsonObject.optString("C_NAMA"));
        kelas.setN_TINGKAT(jsonObject.optInt("N_TINGKAT"));
        kelas.setC_STATUS(jsonObject.optString("C_STATUS"));
        kelas.setN_JUMLAH_SISWA(jsonObject.optInt("N_JUMLAH_SISWA"));
        return kelas;
    }


    @Bindable
    public String getC_KELAS() {
        return C_KELAS;
    }

    public void setC_KELAS(String C_KELAS) {
        this.C_KELAS = C_KELAS;
        notifyPropertyChanged(BR.c_KELAS);
    }

    @Bindable
    public String getC_NAMA() {
        return C_NAMA;
    }

    public void setC_NAMA(String C_NAMA) {
        this.C_NAMA = C_NAMA;
        notifyPropertyChanged(BR.c_NAMA);
    }

    @Bindable
    public int getN_TINGKAT() {
        return N_TINGKAT;
    }

    public void setN_TINGKAT(int N_TINGKAT) {
        this.N_TINGKAT = N_TINGKAT;
        notifyPropertyChanged(BR.n_TINGKAT);
    }

    @Bindable
    public String getC_STATUS() {
        return C_STATUS;
    }

    public void setC_STATUS(String C_STATUS) {
        this.C_STATUS = C_STATUS;
        notifyPropertyChanged(BR.c_STATUS);
    }

    @Bindable
    public int getN_JUMLAH_SISWA() {
        return N_JUMLAH_SISWA;
    }

    public void setN_JUMLAH_SISWA(int N_JUMLAH_SISWA) {
        this.N_JUMLAH_SISWA = N_JUMLAH_SISWA;
        notifyPropertyChanged(BR.n_JUMLAH_SISWA);
    }

    @Override
    public String toString() {
        return "Kelas{" +
                "C_KELAS='" + C_KELAS + '\'' +
                ", C_NAMA='" + C_NAMA + '\'' +
                ", N_TINGKAT=" + N_TINGKAT +
                ", C_STATUS='" + C_STATUS + '\'' +
                ", N_JUMLAH_SISWA=" + N_JUMLAH_SISWA +
                '}';
    }
}
