package com.niken.perkembangananak.activity.buatjadwal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.Utils;
import com.niken.perkembangananak.base.BaseViewModel;
import com.niken.perkembangananak.base.OnExecuteListener;
import com.niken.perkembangananak.model.Guru;
import com.niken.perkembangananak.model.Jadwal;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;

import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Firman on 12/15/2018.
 */
public class BuatJadwalViewModel extends BaseViewModel {

    private ArrayAdapter<Guru> guruAdapter;
    private ObservableArrayList<Guru> guruList = new ObservableArrayList<>();
    private final OnExecuteListener<Boolean> listener;
    private final Jadwal jadwal = new Jadwal();

    public BuatJadwalViewModel(Context context, OnExecuteListener<Boolean> listener) {
        super(context);
        guruAdapter = new ArrayAdapter<Guru>(getContext(), android.R.layout.simple_list_item_1, guruList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                ((TextView)convertView.findViewById(android.R.id.text1)).setText(getItem(position).getV_NAMA());
                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
                ((TextView)convertView.findViewById(android.R.id.text1)).setText(getItem(position).getV_NAMA());
                return convertView;
            }
        };
        this.listener = listener;
        getGuru().subscribe(aBoolean -> {}, Throwable::printStackTrace);
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public ArrayAdapter<Guru> getGuruAdapter() {
        return guruAdapter;
    }

    public void setTanggal(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    editText.setText(Utils.formatDate(calendar.getTime(), "dd MMMM yyyy"));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void setMulai(EditText editText) {
        TimePickerDialog dialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minute) -> editText.setText(Utils.addZeroTime(hourOfDay) + ":" + Utils.addZeroTime(minute)),
                0, 0, true);
        dialog.show();
    }

    public void setSelesai(EditText editText) {
        TimePickerDialog dialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minute) -> editText.setText(Utils.addZeroTime(hourOfDay) + ":" + Utils.addZeroTime(minute)),
                0, 0, true);
        dialog.show();
    }


    private Observable<Boolean> getGuru() {
        return Rx2AndroidNetworking.post(Constant.URL_GURU)
                .build()
                .getJSONObjectObservable()
                .map(object -> {
                    JSONArray array = object.getJSONArray("DataRow");
                    guruList.clear();
                    for (int i=0;i<array.length();i++) {
                        guruList.add(Guru.fromJson(array.getJSONObject(i)));
                    }
                    guruAdapter.notifyDataSetChanged();
                    return true;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
