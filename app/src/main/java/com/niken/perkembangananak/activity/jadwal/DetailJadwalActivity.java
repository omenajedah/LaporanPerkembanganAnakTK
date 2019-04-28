package com.niken.perkembangananak.activity.jadwal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseAccessActivity;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.ActivityDetailJadwalBinding;

/**
 * Created by Firman on 12/15/2018.
 */
public class DetailJadwalActivity extends BaseAccessActivity<ActivityDetailJadwalBinding, DetailJadwalViewModel>
        implements OnExecuteListener<Boolean> {

    private DetailJadwalViewModel viewModel;

    public static void start(Context context, String c_JADWAL) {
        Intent starter = new Intent(context, DetailJadwalActivity.class);
        if (c_JADWAL != null)
            starter.putExtra("C_JADWAL", c_JADWAL);
        context.startActivity(starter);
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_jadwal;
    }

    @Override
    public DetailJadwalViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new DetailJadwalViewModel(this, this);
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (getIntent().hasExtra("C_JADWAL")) {
            String c_JADWAL = getIntent().getStringExtra("C_JADWAL");
            viewModel.setJadwal(c_JADWAL);
            getBinding().tanggalEt.setEnabled(false);
            getBinding().mulaiEt.setEnabled(false);
            getBinding().selesaiEt.setEnabled(false);
            getBinding().namaKegiatanET.setEnabled(false);
            getBinding().button2.setEnabled(false);
            getBinding().spinner1.setEnabled(false);
            getBinding().spinner2.setEnabled(false);
        }
    }

    @Override
    public void onExecuted(Boolean aBoolean) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
