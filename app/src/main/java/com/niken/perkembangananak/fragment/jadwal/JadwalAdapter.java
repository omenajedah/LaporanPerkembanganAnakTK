package com.niken.perkembangananak.fragment.jadwal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niken.perkembangananak.activity.buatjadwal.BuatJadwalActivity;
import com.niken.perkembangananak.base.BaseRecyclerAdapter;
import com.niken.perkembangananak.base.BaseViewHolder;
import com.niken.perkembangananak.databinding.ListJadwalBinding;
import com.niken.perkembangananak.model.Jadwal;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by Firman on 4/17/2019.
 */
public class JadwalAdapter extends BaseRecyclerAdapter<Jadwal> {


    public JadwalAdapter(List<Jadwal> originalList) {
        super(originalList);
    }

    @Override
    public boolean onSearch(String filter, Jadwal jadwal) throws Exception {
        return false;
    }

    @NonNull
    @Override
    public BaseViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListJadwalBinding binding = ListJadwalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new JadwalViewHolder(binding);
    }

    class JadwalViewHolder extends BaseViewHolder<ListJadwalBinding> {

        public JadwalViewHolder(ListJadwalBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.setItem(showList.get(position));
            binding.cardView.setOnClickListener(v -> BuatJadwalActivity.start(v.getContext()));
        }
    }
}
