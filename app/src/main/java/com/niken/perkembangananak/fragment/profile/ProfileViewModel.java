package com.niken.perkembangananak.fragment.profile;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.base.BaseViewModel;
import com.niken.perkembangananak.model.Profile;
import com.rx2androidnetworking.Rx2ANRequest;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Firman on 4/17/2019.
 */
public class ProfileViewModel extends BaseViewModel {


    private final Profile profile = new Profile();
    private ObservableBoolean changed = new ObservableBoolean();

    public ProfileViewModel(Context context) {
        super(context);

        refresh();
    }

    private void refresh() {
        getCompositeDisposable().add(
                getProfileObservsble()
                .subscribe(profile1 -> profile.copy(profile1), throwable -> throwable.printStackTrace())
        );
    }

    public ObservableBoolean getChanged() {
        return changed;
    }

    public Profile getProfile() {
        return profile;
    }

    public void onSaveClick(View view) {
        getCompositeDisposable().add(
                saveObservable()
                        .subscribe(aBoolean -> {
                            if (true)
                                Toast.makeText(getContext(), "Profil Berhasil Diubah", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getContext(), "Profil Gagal Diubah", Toast.LENGTH_SHORT).show();
                        }, throwable -> {
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        })
        );
    }

    public void onChangeKontakClick(View view) {

    }

    public void onChangePassClick(View view) {

    }

    public void onKeluarClick(View view) {
        getSessionHandler().logout();
    }

    private Observable<Profile> getProfileObservsble() {
        return Rx2AndroidNetworking.post(Constant.URL_PROFILE)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> Profile.fromJson(jsonObject.getJSONObject("DataRow")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<Boolean> saveObservable() {
        return Rx2AndroidNetworking.post(Constant.URL_CHANGE_PROFILE)
                .addBodyParameter(profile)
                .build()
                .getJSONObjectObservable()
                .map(jsonObject -> jsonObject.optBoolean("success"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
