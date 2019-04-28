package com.niken.perkembangananak.fragment.masterdata;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.niken.perkembangananak.activity.siswa.DetailSiswaActivity;
import com.niken.perkembangananak.base.BaseRecyclerAdapter;
import com.niken.perkembangananak.base.BaseViewHolder;
import com.niken.perkembangananak.databinding.ListSiswaBinding;
import com.niken.perkembangananak.model.Siswa;

import java.util.List;

/**
 * Created by Firman on 4/17/2019.
 */
public class SiswaAdapter extends BaseRecyclerAdapter<Siswa> {


    public SiswaAdapter(List<Siswa> originalList) {
        super(originalList);
    }

    @Override
    public boolean onSearch(String filter, Siswa jadwal) throws Exception {
        return false;
    }

    @NonNull
    @Override
    public BaseViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListSiswaBinding binding = ListSiswaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SiswaViewHolder(binding);
    }

    class SiswaViewHolder extends BaseViewHolder<ListSiswaBinding> {

        public SiswaViewHolder(ListSiswaBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.setItem(showList.get(position));
            binding.getRoot().setOnClickListener(v ->
                    DetailSiswaActivity.start(binding.getRoot().getContext(), showList.get(position).getC_SISWA()));

        }
    }
}
