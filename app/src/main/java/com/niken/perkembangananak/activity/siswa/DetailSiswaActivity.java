package com.niken.perkembangananak.activity.siswa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseAccessActivity;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.ActivityDetailSiswaBinding;
import com.niken.perkembangananak.fragment.masterdata.WaliAdapter;
import com.niken.perkembangananak.model.HubunganWali;
import com.niken.perkembangananak.model.Wali;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firman on 12/15/2018.
 */
public class DetailSiswaActivity extends BaseAccessActivity<ActivityDetailSiswaBinding, DetailSiswaViewModel>
        implements OnExecuteListener<List<Wali>> {

    private DetailSiswaViewModel viewModel;
    private List<Wali> waliList = new ArrayList<>();
    private WaliAdapter adapter = new WaliAdapter(waliList);

    public static void start(Context context, String c_SISWA) {
        Intent starter = new Intent(context, DetailSiswaActivity.class);
        if (c_SISWA != null)
            starter.putExtra("C_SISWA", c_SISWA);
        context.startActivity(starter);
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_siswa;
    }

    @Override
    public DetailSiswaViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new DetailSiswaViewModel(this, this);
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getBinding().listData.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        getBinding().listData.addItemDecoration(decoration);
        getBinding().listData.setAdapter(adapter);

        if (getIntent().hasExtra("C_SISWA")) {
            String c_SISWA = getIntent().getStringExtra("C_SISWA");
            viewModel.setSiswa(c_SISWA);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (getIntent().hasExtra("C_SISWA")) {
            String c_SISWA = getIntent().getStringExtra("C_SISWA");
            viewModel.setSiswa(c_SISWA);
        }
    }

    @Override
    public void onExecuted(List<Wali> waliList) {
        this.waliList.clear();
        this.waliList.addAll(waliList);
        adapter.updateDataSet();
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
