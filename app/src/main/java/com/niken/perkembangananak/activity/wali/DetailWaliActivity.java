package com.niken.perkembangananak.activity.wali;

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
import com.niken.perkembangananak.databinding.ActivityDetailWaliBinding;
import com.niken.perkembangananak.fragment.masterdata.SiswaAdapter;
import com.niken.perkembangananak.model.HubunganWali;
import com.niken.perkembangananak.model.Siswa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firman on 12/15/2018.
 */
public class DetailWaliActivity extends BaseAccessActivity<ActivityDetailWaliBinding, DetailWaliViewModel>
        implements OnExecuteListener<List<Siswa>> {

    private DetailWaliViewModel viewModel;
    private List<Siswa> siswaList = new ArrayList<>();
    private SiswaAdapter adapter = new SiswaAdapter(siswaList);

    public static void start(Context context, String c_USER) {
        Intent starter = new Intent(context, DetailWaliActivity.class);
        if (c_USER != null)
            starter.putExtra("C_USER", c_USER);
        context.startActivity(starter);
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_wali;
    }

    @Override
    public DetailWaliViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new DetailWaliViewModel(this, this);
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

        if (getIntent().hasExtra("C_USER")) {
            String c_SISWA = getIntent().getStringExtra("C_USER");
            viewModel.setWali(c_SISWA);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (getIntent().hasExtra("C_USER")) {
            String c_SISWA = getIntent().getStringExtra("C_USER");
            viewModel.setWali(c_SISWA);
        }
    }

    @Override
    public void onExecuted(List<Siswa> siswaList) {
        this.siswaList.clear();
        this.siswaList.addAll(siswaList);
        adapter.updateDataSet();
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
