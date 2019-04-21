package com.niken.perkembangananak.fragment.masterdata;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.niken.perkembangananak.base.BaseRecyclerAdapter;
import com.niken.perkembangananak.base.BaseViewHolder;
import com.niken.perkembangananak.databinding.ListWaliBinding;
import com.niken.perkembangananak.model.Wali;

import java.util.List;

/**
 * Created by Firman on 4/17/2019.
 */
public class WaliAdapter extends BaseRecyclerAdapter<Wali> {


    public WaliAdapter(List<Wali> originalList) {
        super(originalList);
    }

    @Override
    public boolean onSearch(String filter, Wali jadwal) throws Exception {
        return false;
    }

    @NonNull
    @Override
    public BaseViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListWaliBinding binding = ListWaliBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WaliViewHolder(binding);
    }

    class WaliViewHolder extends BaseViewHolder<ListWaliBinding> {

        public WaliViewHolder(ListWaliBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
            binding.setItem(showList.get(position));
        }
    }
}
