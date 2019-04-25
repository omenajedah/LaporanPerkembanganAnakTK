package com.niken.perkembangananak.activity.home;

import android.content.Context;
import androidx.databinding.ObservableField;


import com.niken.perkembangananak.activity.login.LoginActivity;
import com.niken.perkembangananak.base.BaseViewModel;

/**
 * Created by Firman on 12/15/2018.
 */
public class HomeViewModel extends BaseViewModel {

    private final HomeListener listener;
    private ObservableField<String> fullname = new ObservableField<>();


    public HomeViewModel(Context context, HomeListener listener) {
        super(context);
        this.listener=listener;
        fullname.set("Selamat Datang, "+getSessionHandler().getString("user_fullname", null));
    }

    public ObservableField<String> getFullname() {
        return fullname;
    }

    public void onSupplierClick() {

    }

    public void onBarangKeluarClick() {

    }

    public void onBarangMasukClick() {

    }

    public void onStokClick() {

    }

    public void onLaporanClick() {

    }

    public void onLogoutClick() {
        getSessionHandler().clear();
        LoginActivity.start(getContext());
        listener.onLogout();

    }

    public interface HomeListener {
        void onLogout();
    }
}
