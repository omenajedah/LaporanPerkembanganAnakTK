package com.niken.perkembangananak.fragment.kelas;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niken.perkembangananak.base.BaseRecyclerAdapter;
import com.niken.perkembangananak.base.BaseViewHolder;
import com.niken.perkembangananak.databinding.ListKelasBinding;
import com.niken.perkembangananak.model.Kelas;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Firman on 4/17/2019.
 */
public class KelasAdapter extends BaseRecyclerAdapter<Kelas> {


    public KelasAdapter(List<Kelas> originalList) {
        super(originalList);
    }

    @Override
    public boolean onSearch(String filter, Kelas jadwal) throws Exception {
        return false;
    }

    @NonNull
    @Override
    public BaseViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListKelasBinding binding = ListKelasBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new KelasViewHolder(binding);
    }

    class KelasViewHolder extends BaseViewHolder<ListKelasBinding> {

        public KelasViewHolder(ListKelasBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.setItem(showList.get(position));
        }
    }
}
