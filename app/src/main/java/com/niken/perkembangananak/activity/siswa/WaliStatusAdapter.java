package com.niken.perkembangananak.activity.siswa;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.niken.perkembangananak.base.BaseRecyclerAdapter;
import com.niken.perkembangananak.base.BaseViewHolder;
import com.niken.perkembangananak.databinding.ListWaliStatusBinding;
import com.niken.perkembangananak.model.HubunganWali;

import java.util.List;

/**
 * Created by Firman on 4/27/2019.
 */
public class WaliStatusAdapter extends BaseRecyclerAdapter<HubunganWali> {
    public WaliStatusAdapter(List<HubunganWali> originalList) {
        super(originalList);
    }

    @Override
    public boolean onSearch(String filter, HubunganWali hubunganWali) throws Exception {
        return false;
    }

    @NonNull
    @Override
    public BaseViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListWaliStatusBinding binding = ListWaliStatusBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WaliStatusViewHolder(binding);
    }

    class WaliStatusViewHolder extends BaseViewHolder<ListWaliStatusBinding> {

        public WaliStatusViewHolder(ListWaliStatusBinding binding) {
            super(binding);
        }

        @Override
        public void onBind(int position) {
//            binding.setItem(showList.get(position));
        }
    }
}
