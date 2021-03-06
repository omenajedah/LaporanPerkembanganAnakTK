package com.niken.perkembangananak.fragment.hasil;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.base.BaseViewModel;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.model.HasilKegiatan;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Firman on 4/17/2019.
 */
public class HasilViewModel extends BaseViewModel {


    private final OnExecuteListener<List<HasilKegiatan>> listener;

    public HasilViewModel(Context context, OnExecuteListener<List<HasilKegiatan>> listener) {
        super(context);
        this.listener = listener;
    }


    public void refresh() {
        getCompositeDisposable().clear();
        getCompositeDisposable().add(
                getJadwals().subscribe(kegiatans -> {
                    Log.d(getClass().getSimpleName(), "size = "+kegiatans.size());
                    listener.onExecuted(kegiatans);
                }, throwable -> {
                    listener.onError(throwable);
                })
        );
    }


    private Observable<List<HasilKegiatan>> getJadwals() {
        return Rx2AndroidNetworking.post(Constant.URL_HASIL_KEGIATAN)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> {
                    JSONArray array = jsonObject.getJSONArray("DataRow");
                    List<HasilKegiatan> kegiatans = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        kegiatans.add(HasilKegiatan.fromJson(array.getJSONObject(i)));
                    }
                    return kegiatans;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
