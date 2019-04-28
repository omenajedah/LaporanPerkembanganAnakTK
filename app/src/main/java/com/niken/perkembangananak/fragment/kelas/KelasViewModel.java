package com.niken.perkembangananak.fragment.kelas;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.activity.kelas.DetailKelasActivity;
import com.niken.perkembangananak.base.BaseViewModel;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.model.Kelas;
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
public class KelasViewModel extends BaseViewModel {

    private final OnExecuteListener<List<Kelas>> listener;

    public KelasViewModel(Context context, OnExecuteListener<List<Kelas>> listener) {
        super(context);
        this.listener = listener;
    }

    public void buatKelas(View view) {
        DetailKelasActivity.start(view.getContext(), null);
    }

    public void refresh() {
        getCompositeDisposable().clear();
        getCompositeDisposable().add(
                getJadwals().subscribe(jadwals -> {
                    Log.d(getClass().getSimpleName(), "size = "+jadwals.size());
                    listener.onExecuted(jadwals);
                }, throwable -> {
                    listener.onError(throwable);
                })
        );
    }


    private Observable<List<Kelas>> getJadwals() {
        return Rx2AndroidNetworking.post(Constant.URL_KELAS)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> {
                    JSONArray array = jsonObject.getJSONArray("DataRow");
                    List<Kelas> kelas = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        kelas.add(Kelas.fromJson(array.getJSONObject(i)));
                    }
                    return kelas;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
