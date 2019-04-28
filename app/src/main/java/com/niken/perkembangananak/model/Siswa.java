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
public class Siswa extends BaseObservable {

    private String C_SISWA;
    private String V_NAMA;
    private String D_TGL_LAHIR;
    private String C_TEMPAT_LAHIR;
    private String C_JENKEL;
    private Kelas kelas;
    private String D_TGL_MASUK;


    public static Siswa fromJson(JSONObject jsonObject) {
        Siswa siswa = new Siswa();
        siswa.setC_SISWA(jsonObject.optString("C_SISWA"));
        siswa.setV_NAMA(jsonObject.optString("V_NAMA"));
        siswa.setD_TGL_LAHIR(jsonObject.optString("D_TGL_LAHIR"));
        siswa.setC_TEMPAT_LAHIR(jsonObject.optString("C_TEMPAT_LAHIR"));
        siswa.setC_JENKEL(jsonObject.optString("C_JENKEL"));
        siswa.setD_TGL_MASUK(jsonObject.optString("D_TGL_MASUK"));

        Kelas kelas = new Kelas();
        kelas.setC_KELAS(jsonObject.optString("C_KELAS"));
        kelas.setC_NAMA(jsonObject.optString("C_NAMA"));
        siswa.setKelas(kelas);

        return siswa;
    }

    @Bindable
    public String getC_SISWA() {
        return C_SISWA;
    }

    public void setC_SISWA(String C_SISWA) {
        this.C_SISWA = C_SISWA;
        notifyPropertyChanged(BR.c_SISWA);
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
    public String getD_TGL_LAHIR() {
        return D_TGL_LAHIR;
    }

    public void setD_TGL_LAHIR(String D_TGL_LAHIR) {
        this.D_TGL_LAHIR = D_TGL_LAHIR;
        notifyPropertyChanged(BR.d_TGL_LAHIR);
    }

    @Bindable
    public String getC_TEMPAT_LAHIR() {
        return C_TEMPAT_LAHIR;
    }

    public void setC_TEMPAT_LAHIR(String C_TEMPAT_LAHIR) {
        this.C_TEMPAT_LAHIR = C_TEMPAT_LAHIR;
        notifyPropertyChanged(BR.c_TEMPAT_LAHIR);
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
    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
        notifyPropertyChanged(BR.kelas);
    }

    @Bindable
    public String getD_TGL_MASUK() {
        return D_TGL_MASUK;
    }

    public void setD_TGL_MASUK(String d_TGL_MASUK) {
        D_TGL_MASUK = d_TGL_MASUK;
        notifyPropertyChanged(BR.d_TGL_MASUK);
    }

    @Override
    public String toString() {
        return "Siswa{" +
                "C_SISWA='" + C_SISWA + '\'' +
                ", V_NAMA='" + V_NAMA + '\'' +
                ", D_TGL_LAHIR='" + D_TGL_LAHIR + '\'' +
                ", C_TEMPAT_LAHIR='" + C_TEMPAT_LAHIR + '\'' +
                ", C_JENKEL='" + C_JENKEL + '\'' +
                ", kelas=" + kelas +
                ", D_TGL_MASUK='" + D_TGL_MASUK + '\'' +
                '}';
    }
}