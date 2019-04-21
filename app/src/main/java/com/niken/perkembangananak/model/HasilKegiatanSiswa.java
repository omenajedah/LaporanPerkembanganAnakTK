package com.niken.perkembangananak.model;

import androidx.databinding.Bindable;

import com.niken.perkembangananak.BR;

/**
 * Created by Firman on 4/18/2019.
 */
public class HasilKegiatanSiswa extends Jadwal {

    private boolean C_ABSEN;
    private int C_NILAI;
    private Siswa siswa;

    @Bindable
    public boolean isC_ABSEN() {
        return C_ABSEN;
    }

    public void setC_ABSEN(boolean c_absen) {
        C_ABSEN = c_absen;
        notifyPropertyChanged(BR.c_ABSEN);
    }

    @Bindable
    public int getC_NILAI() {
        return C_NILAI;
    }

    public void setC_NILAI(int c_NILAI) {
        C_NILAI = c_NILAI;
        notifyPropertyChanged(BR.c_NILAI);
    }

    @Bindable
    public Siswa getSiswa() {
        return siswa;
    }

    public void setSiswa(Siswa siswa) {
        this.siswa = siswa;
        notifyPropertyChanged(BR.siswa);
    }
}
