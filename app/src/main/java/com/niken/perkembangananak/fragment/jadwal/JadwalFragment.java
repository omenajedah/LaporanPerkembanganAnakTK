package com.niken.perkembangananak.fragment.jadwal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseFragment;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.FragmentJadwalBinding;
import com.niken.perkembangananak.model.Jadwal;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Created by Firman on 4/17/2019.
 */
public class JadwalFragment extends BaseFragment<FragmentJadwalBinding, JadwalViewModel>
        implements OnExecuteListener<List<Jadwal>> {

    private List<Jadwal> jadwalList = new ArrayList<>();
    private JadwalAdapter adapter;
    private JadwalViewModel viewModel;
    @Override
    protected int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jadwal;
    }

    @Override
    protected JadwalViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new JadwalViewModel(getActivity(), this);
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new JadwalAdapter(jadwalList);
        getBinding().listData.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        getBinding().listData.addItemDecoration(decoration);
        getBinding().listData.setAdapter(adapter);
        viewModel.refresh();
    }

    @Override
    public void onExecuted(List<Jadwal> jadwals) {
        Log.d(getTag(), "data size="+jadwals.size());
        this.jadwalList.clear();
        this.jadwalList.addAll(jadwals);
        adapter.updateDataSet();
    }

    @Override
    public void onError(Throwable throwable) {

        throwable.printStackTrace();
    }
}
