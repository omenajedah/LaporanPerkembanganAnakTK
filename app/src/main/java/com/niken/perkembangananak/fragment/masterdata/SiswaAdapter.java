package com.niken.perkembangananak.fragment.masterdata;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

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
        return new JadwalViewHolder(binding);
    }

    class JadwalViewHolder extends BaseViewHolder<ListSiswaBinding> {

        public JadwalViewHolder(ListSiswaBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.setItem(showList.get(position));
        }
    }
}
