package com.niken.perkembangananak.fragment.masterdata;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseFragment;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.databinding.FragmentMasterDataBinding;
import com.niken.perkembangananak.model.Siswa;
import com.niken.perkembangananak.model.Wali;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Firman on 4/17/2019.
 */
public class MasterDataFragment extends BaseFragment<FragmentMasterDataBinding, MasterDataViewModel> {


    private final List<Siswa> siswas = new ArrayList<>();
    private final List<Wali> walis = new ArrayList<>();

    private MasterDataViewModel viewModel;
    private SiswaAdapter siswaAdapter;
    private WaliAdapter waliAdapter;


    @Override
    protected int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_master_data;
    }

    @Override
    protected MasterDataViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new MasterDataViewModel(getActivity(), siswaListener, waliListener);
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        siswaAdapter = new SiswaAdapter(siswas);
        waliAdapter = new WaliAdapter(walis);

        getBinding().listData.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        getBinding().listData.addItemDecoration(decoration);
        viewModel.onMasterSelected(0);
    }

    private final OnExecuteListener<List<Siswa>> siswaListener = new OnExecuteListener<List<Siswa>>() {
        @Override
        public void onExecuted(List<Siswa> siswa) {
            siswas.clear();
            siswas.addAll(siswa);
            siswaAdapter.updateDataSet();
            getBinding().listData.setAdapter(siswaAdapter);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
    };

    private final OnExecuteListener<List<Wali>> waliListener = new OnExecuteListener<List<Wali>>() {
        @Override
        public void onExecuted(List<Wali> wali) {
            walis.clear();
            walis.addAll(wali);
            waliAdapter.updateDataSet();
            getBinding().listData.setAdapter(waliAdapter);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
    };
}
