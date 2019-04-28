package com.niken.perkembangananak.activity.jadwal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.base.BaseViewModel;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.model.Guru;
import com.niken.perkembangananak.model.Jadwal;
import com.niken.perkembangananak.model.Kelas;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Firman on 12/15/2018.
 */
public class DetailJadwalViewModel extends BaseViewModel {

    private final OnExecuteListener<Boolean> listener;
    private final Jadwal jadwal = new Jadwal();
    private ArrayAdapter<Guru> guruAdapter;
    private ArrayAdapter<Kelas> kelasAdapter;
    private List<Guru> guruList = new ArrayList<>();
    private List<Kelas> kelasList = new ArrayList<>();

    public DetailJadwalViewModel(Context context, OnExecuteListener<Boolean> listener) {
        super(context);
        this.listener = listener;
        initAdapter();
        getCompositeDisposable().add(
                getGuru().subscribe(aBoolean -> guruAdapter.notifyDataSetChanged(),
                        Throwable::printStackTrace)
        );
        getCompositeDisposable().add(
                getKelas().subscribe(aBoolean -> kelasAdapter.notifyDataSetChanged(),
                        Throwable::printStackTrace)
        );
    }

    private void initAdapter() {
        guruAdapter = new ArrayAdapter<Guru>(getContext(), android.R.layout.simple_list_item_1, guruList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getV_NAMA());
                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getV_NAMA());
                return convertView;
            }
        };
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
    }

    public void onNameChanged(CharSequence s, int start, int before, int count) {
        jadwal.setC_NAMA(s.toString());
    }

    public void setJadwal(String c_JADWAL) {
        jadwal.setC_JADWAL(c_JADWAL);
        getCompositeDisposable().add(
                downloadJadwal()
                        .subscribe(aBoolean -> {
                        }, throwable -> throwable.printStackTrace())
        );
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public ArrayAdapter<Guru> getGuruAdapter() {
        return guruAdapter;
    }

    public ArrayAdapter<Kelas> getKelasAdapter() {
        return kelasAdapter;
    }

    public void setTanggal(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    jadwal.setD_TANGGAL(calendar.getTime());

//                    editText.setText(Utils.formatDate(calendar.getTime(), "dd MMMM yyyy"));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void setMulai(TextInputEditText editText) {
        if (jadwal.getD_TANGGAL() == null) {
            editText.setError("Mohon pilih tanggal dahulu");
            return;
        }

        TimePickerDialog dialog =
                new TimePickerDialog(getContext(), (view, hourOfDay, minute) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(jadwal.getD_TANGGAL());
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    jadwal.setD_MULAI(calendar.getTime());
//                    editText.setText(Utils.addZeroTime(hourOfDay) + ":" + Utils.addZeroTime(minute));
                }, 0, 0, true);
        dialog.show();
    }

    public void setSelesai(EditText editText) {
        if (jadwal.getD_TANGGAL() == null) {
            editText.setError("Mohon pilih tanggal dahulu");
            return;
        }
        TimePickerDialog dialog =
                new TimePickerDialog(getContext(), (view, hourOfDay, minute) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(jadwal.getD_TANGGAL());
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    jadwal.setD_SELESAI(calendar.getTime());
//                    editText.setText(Utils.addZeroTime(hourOfDay) + ":" + Utils.addZeroTime(minute));
                }, 0, 0, true);
        dialog.show();
    }

    public void onGuruSelected(AdapterView<?> parent, View view, int position, long id) {
        jadwal.setGuru(guruList.get(position));
    }

    public void onKelasSelected(AdapterView<?> parent, View view, int position, long id) {
        jadwal.setKelas(kelasList.get(position));
    }

    public void saveJadwal() {
        Log.d(getClass().getSimpleName(), jadwal.toString());
    }


    private Observable<Boolean> getGuru() {
        return Rx2AndroidNetworking.post(Constant.URL_GURU)
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    guruList.clear();
                    for (int i = 0; i < array.length(); i++) {
                        guruList.add(Guru.fromJson(array.getJSONObject(i)));
                    }
                    return true;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Boolean> getKelas() {
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

    private Observable<Boolean> downloadJadwal() {
        return Rx2AndroidNetworking.post(Constant.URL_JADWAL)
                .addBodyParameter("C_JADWAL", jadwal.getC_JADWAL())
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    Jadwal jadwal = Jadwal.fromJson(array.getJSONObject(0));
                    this.jadwal.setC_NAMA(jadwal.getC_NAMA());
                    this.jadwal.setD_TANGGAL(jadwal.getD_TANGGAL());
                    this.jadwal.setD_SELESAI(jadwal.getD_SELESAI());
                    this.jadwal.setD_MULAI(jadwal.getD_MULAI());
                    this.jadwal.setC_STATUS(jadwal.getC_STATUS());
                    this.jadwal.setKelas(jadwal.getKelas());
                    this.jadwal.setGuru(jadwal.getGuru());
                    return true;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
