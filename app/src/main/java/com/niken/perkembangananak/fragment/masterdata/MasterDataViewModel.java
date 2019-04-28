package com.niken.perkembangananak.fragment.masterdata;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableInt;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.activity.siswa.DetailSiswaActivity;
import com.niken.perkembangananak.activity.wali.DetailWaliActivity;
import com.niken.perkembangananak.base.BaseViewModel;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.model.Siswa;
import com.niken.perkembangananak.model.Wali;
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
public class MasterDataViewModel extends BaseViewModel {

    private int selected;
    private final OnExecuteListener<List<Siswa>> siswaListener;
    private final OnExecuteListener<List<Wali>> waliListener;


    public MasterDataViewModel(Context context, OnExecuteListener<List<Siswa>> siswaListener,
                               OnExecuteListener<List<Wali>> waliListener) {
        super(context);
        this.siswaListener=siswaListener;
        this.waliListener=waliListener;
        refresh();
    }

    public void onMasterSelected(int position) {
        this.selected = position;
        refresh();
    }

    public void onClick(View view) {
        if (selected == 0)
            DetailSiswaActivity.start(getContext(), null);
        else
            DetailWaliActivity.start(getContext(), null);
    }

    public void refresh() {
        getCompositeDisposable().clear();

        if (selected == 0) {
            getCompositeDisposable().add(
                    getSiswa().subscribe(siswas -> {
                        Log.d(getClass().getSimpleName(), "size = " + siswas.size());
                        siswaListener.onExecuted(siswas);
                    }, throwable -> {
                        siswaListener.onError(throwable);
                    })
            );
        } else {
            getCompositeDisposable().add(
                    getWali().subscribe(walis -> {
                        Log.d(getClass().getSimpleName(), "size = " + walis.size());
                        waliListener.onExecuted(walis);
                    }, throwable -> {
                        waliListener.onError(throwable);
                    })
            );
        }


    }


    private Observable<List<Siswa>> getSiswa() {
        return Rx2AndroidNetworking.post(Constant.URL_SISWA)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> {
                    JSONArray array = jsonObject.getJSONArray("DataRow");
                    List<Siswa> siswas = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        siswas.add(Siswa.fromJson(array.getJSONObject(i)));
                    }
                    return siswas;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Wali>> getWali() {
        return Rx2AndroidNetworking.post(Constant.URL_WALI)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> {
                    JSONArray array = jsonObject.getJSONArray("DataRow");
                    List<Wali> walis = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        walis.add(Wali.fromJson(array.getJSONObject(i)));
                    }
                    return walis;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
