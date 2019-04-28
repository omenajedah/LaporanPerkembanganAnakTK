package com.niken.perkembangananak.activity.wali;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.Utils;
import com.niken.perkembangananak.base.BaseViewModel;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.model.HubunganWali;
import com.niken.perkembangananak.model.Kelas;
import com.niken.perkembangananak.model.Siswa;
import com.niken.perkembangananak.model.Wali;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Firman on 12/15/2018.
 */
public class DetailWaliViewModel extends BaseViewModel {

    private final OnExecuteListener<List<Siswa>> listener;
    private final Wali wali = new Wali();

    public DetailWaliViewModel(Context context, OnExecuteListener<List<Siswa>> listener) {
        super(context);
        this.listener = listener;
    }

    public void setWali(String c_USER) {
        wali.setC_USER(c_USER);
        getCompositeDisposable().add(
                downloadWali()
                        .subscribe(aBoolean -> {
                        }, throwable -> throwable.printStackTrace())
        );
        getCompositeDisposable().add(
                downloadSiswa()
                        .subscribe(listener::onExecuted,
                                throwable -> throwable.printStackTrace())
        );


    }

    public void onNameChanged(CharSequence s, int start, int before, int count) {
        wali.setV_NAMA(s.toString());
    }

    public Wali getWali() {
        return wali;
    }

    public void saveWali() {
        getCompositeDisposable().add(
                uploadWali()
                        .subscribe(aBoolean -> {
                        }, throwable -> throwable.printStackTrace())
        );
        Toast.makeText(getContext(), "Tersimpan", Toast.LENGTH_SHORT).show();
    }

    private Observable<Boolean> uploadWali() {
        Map<String, String> param = new HashMap<>();
        param.put("C_LOGIN", wali.getC_LOGIN());
        param.put("V_PASSWORD", wali.getV_PASSWORD());
        param.put("V_NAMA", wali.getV_NAMA());
        param.put("V_ALAMAT", wali.getV_ALAMAT());
        param.put("C_GROUP", String.valueOf(wali.getC_GROUP()));
        param.put("C_STATUS", String.valueOf(wali.getC_STATUS()));

        if (wali.getC_USER() != null)
            param.put("C_USER", wali.getC_USER());

        return Rx2AndroidNetworking.post(Constant.URL_UBAH_SISWA)
                .addBodyParameter(param)
                .build()
                .getJSONObjectObservable()
                .map(object -> object.optBoolean("success"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Boolean> downloadWali() {
        return Rx2AndroidNetworking.post(Constant.URL_WALI)
                .addBodyParameter("C_USER", wali.getC_USER())
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    Wali wali = Wali.fromJson(array.getJSONObject(0));
                    this.wali.setC_LOGIN(wali.getC_LOGIN());
                    this.wali.setV_PASSWORD(wali.getV_PASSWORD());
                    this.wali.setV_NAMA(wali.getV_NAMA());
                    this.wali.setV_ALAMAT(wali.getV_ALAMAT());
                    this.wali.setC_GROUP(wali.getC_GROUP());
                    this.wali.setC_STATUS(wali.getC_STATUS());
                    return true;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Siswa>> downloadSiswa() {
        return Rx2AndroidNetworking.post(Constant.URL_SISWA)
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    List<Siswa> siswas = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        siswas.add(Siswa.fromJson(array.getJSONObject(i)));
                    }
                    return siswas;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
