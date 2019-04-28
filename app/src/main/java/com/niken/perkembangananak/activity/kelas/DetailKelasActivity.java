package com.niken.perkembangananak.activity.kelas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseAccessActivity;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.ActivityDetailKelasBinding;

/**
 * Created by Firman on 12/15/2018.
 */
public class DetailKelasActivity extends BaseAccessActivity<ActivityDetailKelasBinding, DetailKelasViewModel>
        implements OnExecuteListener<Boolean> {

    private DetailKelasViewModel viewModel;

    public static void start(Context context, String c_KELAS) {
        Intent starter = new Intent(context, DetailKelasActivity.class);
        if (c_KELAS != null)
            starter.putExtra("C_KELAS", c_KELAS);
        context.startActivity(starter);
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_kelas;
    }

    @Override
    public DetailKelasViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new DetailKelasViewModel(this, this);
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("C_KELAS")) {
            String c_KELAS = getIntent().getStringExtra("C_KELAS");
            viewModel.setKelas(c_KELAS);
        }
    }

    @Override
    public void onExecuted(Boolean aBoolean) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
