package com.niken.perkembangananak.fragment.hasil;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.niken.perkembangananak.base.BaseRecyclerAdapter;
import com.niken.perkembangananak.base.BaseViewHolder;
import com.niken.perkembangananak.databinding.ListHasilKegiatanBinding;
import com.niken.perkembangananak.model.HasilKegiatan;

import java.util.List;

/**
 * Created by Firman on 4/17/2019.
 */
public class HasilKegiatanAdapter extends BaseRecyclerAdapter<HasilKegiatan> {


    public HasilKegiatanAdapter(List<HasilKegiatan> originalList) {
        super(originalList);
    }

    @Override
    public boolean onSearch(String filter, HasilKegiatan jadwal) throws Exception {
        return false;
    }

    @NonNull
    @Override
    public BaseViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListHasilKegiatanBinding binding = ListHasilKegiatanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new JadwalViewHolder(binding);
    }

    class JadwalViewHolder extends BaseViewHolder<ListHasilKegiatanBinding> {

        public JadwalViewHolder(ListHasilKegiatanBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.setItem(showList.get(position));
        }
    }
}
