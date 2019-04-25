package com.niken.perkembangananak.activity.buatjadwal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseAccessActivity;
import com.niken.perkembangananak.base.BaseFragment;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.ActivityBuatJadwalBinding;
import com.niken.perkembangananak.databinding.ActivityHomeBinding;
import com.niken.perkembangananak.fragment.hasil.HasilFragment;
import com.niken.perkembangananak.fragment.jadwal.JadwalFragment;
import com.niken.perkembangananak.fragment.kelas.KelasFragment;
import com.niken.perkembangananak.fragment.masterdata.MasterDataFragment;
import com.niken.perkembangananak.fragment.profile.ProfileFragment;

/**
 * Created by Firman on 12/15/2018.
 */
public class BuatJadwalActivity extends BaseAccessActivity<ActivityBuatJadwalBinding, BuatJadwalViewModel>
        implements OnExecuteListener<Boolean> {

    private BuatJadwalViewModel viewModel;

    public static void start(Context context) {
        context.startActivity(new Intent(context, BuatJadwalActivity.class));
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_buat_jadwal;
    }

    @Override
    public BuatJadwalViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new BuatJadwalViewModel(this, this);
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onExecuted(Boolean aBoolean) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
