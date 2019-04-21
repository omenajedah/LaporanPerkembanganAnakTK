package com.niken.perkembangananak.model;

import androidx.databinding.Bindable;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.Utils;

import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by Firman on 4/18/2019.
 */
public class HasilKegiatan extends Jadwal{

    private int N_SISWA_HADIR;
    private int N_JML_SISWA;

    public static HasilKegiatan fromJson(JSONObject jsonObject) {
        HasilKegiatan kegiatan = new HasilKegiatan();
        kegiatan.setC_JADWAL(jsonObject.optString("C_JADWAL"));
        kegiatan.setC_NAMA(jsonObject.optString("C_NAMA"));
        try {
            kegiatan.setD_TIME(Utils.parseDate(jsonObject.optString("D_TGL") + " " + jsonObject.optString("D_JAM"), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            kegiatan.setD_SELESAI(Utils.parseDate(jsonObject.optString("D_TGL") + " " + jsonObject.optString("D_SELESAI"), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        kegiatan.setC_STATUS(jsonObject.optInt("C_STATUS"));
        kegiatan.setN_SISWA_HADIR(jsonObject.optInt("N_SISWA_HADIR"));
        kegiatan.setN_JML_SISWA(jsonObject.optInt("N_JML_SISWA"));

        Kelas kelas = new Kelas();
        kelas.setC_KELAS(jsonObject.optString("C_KELAS"));
        kelas.setC_NAMA(jsonObject.optString("C_NAMAKELAS"));
        kegiatan.setKelas(kelas);

        Guru guru = new Guru();
        guru.setC_NIP(jsonObject.optString("C_NIP"));
        guru.setV_NAMA(jsonObject.optString("C_NAMAGURU"));

        kegiatan.setGuru(guru);
        return kegiatan;
    }

    @Bindable
    public int getN_SISWA_HADIR() {
        return N_SISWA_HADIR;
    }

    public void setN_SISWA_HADIR(int n_SISWA_HADIR) {
        N_SISWA_HADIR = n_SISWA_HADIR;
        notifyPropertyChanged(BR.n_SISWA_HADIR);
    }

    @Bindable
    public int getN_JML_SISWA() {
        return N_JML_SISWA;
    }

    public void setN_JML_SISWA(int n_JML_SISWA) {
        N_JML_SISWA = n_JML_SISWA;
        notifyPropertyChanged(BR.n_JML_SISWA);
    }
}
