package com.niken.perkembangananak.fragment.kelas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseFragment;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.FragmentKelasBinding;
import com.niken.perkembangananak.model.Kelas;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Created by Firman on 4/17/2019.
 */
public class KelasFragment extends BaseFragment<FragmentKelasBinding, KelasViewModel>
        implements OnExecuteListener<List<Kelas>> {

    private List<Kelas> jadwalList = new ArrayList<>();
    private KelasAdapter adapter;
    private KelasViewModel viewModel;
    @Override
    protected int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kelas;
    }

    @Override
    protected KelasViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new KelasViewModel(getActivity(), this);
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new KelasAdapter(jadwalList);
        getBinding().listData.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        getBinding().listData.addItemDecoration(decoration);
        getBinding().listData.setAdapter(adapter);

        viewModel.refresh();
    }

    @Override
    public void onExecuted(List<Kelas> kelas) {
        Log.d(getTag(), "data size="+kelas.size());
        this.jadwalList.clear();
        this.jadwalList.addAll(kelas);
        adapter.updateDataSet();
    }

    @Override
    public void onError(Throwable throwable) {

        throwable.printStackTrace();
    }
}
