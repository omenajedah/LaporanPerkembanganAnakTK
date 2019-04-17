package com.niken.perkembangananak.fragment.hasil;

import android.content.Context;
import android.view.View;

import com.niken.perkembangananak.base.BaseViewModel;

/**
 * Created by Firman on 4/17/2019.
 */
public class HasilViewModel extends BaseViewModel {

    public HasilViewModel(Context context) {
        super(context);
    }


    public void onKeluarClick(View view) {
        getSessionHandler().logout();
    }
}
