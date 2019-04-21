package com.niken.perkembangananak.fragment.hasil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseFragment;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.FragmentHasilBinding;
import com.niken.perkembangananak.model.HasilKegiatan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firman on 4/17/2019.
 */
public class HasilFragment extends BaseFragment<FragmentHasilBinding, HasilViewModel>
        implements OnExecuteListener<List<HasilKegiatan>> {


    private List<HasilKegiatan> jadwalList = new ArrayList<>();
    private HasilKegiatanAdapter adapter;
    private HasilViewModel viewModel;
    @Override
    protected int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hasil;
    }

    @Override
    protected HasilViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new HasilViewModel(getActivity(), this);
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HasilKegiatanAdapter(jadwalList);
        getBinding().listData.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        getBinding().listData.addItemDecoration(decoration);
        getBinding().listData.setAdapter(adapter);
        viewModel.refresh();
    }

    @Override
    public void onExecuted(List<HasilKegiatan> jadwals) {
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
