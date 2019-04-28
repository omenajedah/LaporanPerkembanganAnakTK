package com.niken.perkembangananak.activity.siswa;

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
public class DetailSiswaViewModel extends BaseViewModel {

    private final OnExecuteListener<List<Wali>> listener;
    private final Siswa siswa = new Siswa();
    private List<Kelas> kelasList = new ArrayList<>();
    private ArrayAdapter<Kelas> kelasAdapter;

    public DetailSiswaViewModel(Context context, OnExecuteListener<List<Wali>> listener) {
        super(context);
        this.listener = listener;

        kelasAdapter = new ArrayAdapter<Kelas>(getContext(), android.R.layout.simple_list_item_1, kelasList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getC_NAMA());
                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getC_NAMA());
                return convertView;
            }
        };
        getCompositeDisposable().add(
                downloadKelas().subscribe(aBoolean -> kelasAdapter.notifyDataSetChanged(),
                        Throwable::printStackTrace)
        );
    }

    public void setSiswa(String c_SISWA) {
        siswa.setC_SISWA(c_SISWA);
        getCompositeDisposable().add(
                downloadSiswa()
                        .subscribe(aBoolean -> {
                        }, throwable -> throwable.printStackTrace())
        );
        getCompositeDisposable().add(
                downloadWali()
                        .subscribe(listener::onExecuted,
                                throwable -> throwable.printStackTrace())
        );

    }

    public void onNameChanged(CharSequence s, int start, int before, int count) {
        siswa.setV_NAMA(s.toString());
    }

    public void onKelasSelected(AdapterView<?> parent, View view, int position, long id) {
        siswa.setKelas(kelasList.get(position));
    }

    public Siswa getSiswa() {
        return siswa;
    }

    public ArrayAdapter<Kelas> getKelasAdapter() {
        return kelasAdapter;
    }

    public void setTanggalLahir(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    siswa.setD_TGL_LAHIR(Utils.formatDate(calendar.getTime(), "dd MMMM yyyy"));
                    },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void setTanggalMasuk(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    siswa.setD_TGL_MASUK(Utils.formatDate(calendar.getTime(), "dd MMMM yyyy"));

//                    editText.setText());
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void saveSiswa() {
        getCompositeDisposable().add(
                uploadSiswa()
                        .subscribe(aBoolean -> {
                        }, throwable -> throwable.printStackTrace())
        );
        Toast.makeText(getContext(), "Tersimpan", Toast.LENGTH_SHORT).show();
    }

    private Observable<Boolean> uploadSiswa() {
        Map<String, String> param = new HashMap<>();
        param.put("V_NAMA", siswa.getV_NAMA());
        param.put("D_TGL_LAHIR", Utils.reformatDate(siswa.getD_TGL_LAHIR(), "dd MMMM yyyy", "yyyy-MM-dd"));
        param.put("C_TEMPAT_LAHIR", siswa.getC_TEMPAT_LAHIR());
        param.put("C_JENKEL", siswa.getC_JENKEL());
        param.put("C_KELAS", siswa.getKelas().getC_KELAS());
        param.put("D_TGL_MASUK", Utils.reformatDate(siswa.getD_TGL_MASUK(), "dd MMMM yyyy", "yyyy-MM-dd"));

        if (siswa.getC_SISWA() != null)
            param.put("C_SISWA", siswa.getC_SISWA());

        return Rx2AndroidNetworking.post(Constant.URL_UBAH_SISWA)
                .addBodyParameter(param)
                .build()
                .getJSONObjectObservable()
                .map(object -> object.optBoolean("success"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Boolean> downloadSiswa() {
        return Rx2AndroidNetworking.post(Constant.URL_SISWA)
                .addBodyParameter("C_SISWA", siswa.getC_SISWA())
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    Siswa siswa = Siswa.fromJson(array.getJSONObject(0));
                    this.siswa.setV_NAMA(siswa.getV_NAMA());
                    this.siswa.setD_TGL_LAHIR(Utils.reformatDate(siswa.getD_TGL_LAHIR(), "yyyy-MM-dd", "dd MMMM yyyy"));
                    this.siswa.setC_TEMPAT_LAHIR(siswa.getC_TEMPAT_LAHIR());
                    this.siswa.setC_JENKEL(siswa.getC_JENKEL());
                    this.siswa.setKelas(siswa.getKelas());
                    this.siswa.setD_TGL_MASUK(Utils.reformatDate(siswa.getD_TGL_MASUK(), "yyyy-MM-dd", "dd MMMM yyyy"));

                    return true;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Wali>> downloadWali() {
        return Rx2AndroidNetworking.post(Constant.URL_WALI_STATUS)
                .addBodyParameter("C_SISWA", siswa.getC_SISWA())
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    List<Wali> waliList = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        waliList.add(Wali.fromJson(array.getJSONObject(i)));
                    }
                    return waliList;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Boolean> downloadKelas() {
        return Rx2AndroidNetworking.post(Constant.URL_KELAS)
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    kelasList.clear();
                    for (int i = 0; i < array.length(); i++) {
                        kelasList.add(Kelas.fromJson(array.getJSONObject(i)));
                    }
                    return true;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
