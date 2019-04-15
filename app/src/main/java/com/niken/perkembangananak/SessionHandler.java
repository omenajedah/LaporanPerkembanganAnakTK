package com.niken.perkembangananak;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Firman on 12/15/2018.
 */
public class SessionHandler implements SharedPreferences.OnSharedPreferenceChangeListener {

    private final WeakReference<Context> context;
    private final SharedPreferences preference;
    private final Set<SessionChangeListener> changeListeners = new HashSet<>();


    public SessionHandler(Context context) {
        this.context = new WeakReference<>(context);
        this.preference = PreferenceManager.getDefaultSharedPreferences(context);
        this.preference.registerOnSharedPreferenceChangeListener(this);
    }

    public void registerSessionChangeListener(SessionChangeListener listener) {
        changeListeners.add(listener);
    }

    public void unregisterSessionChangeListener(SessionChangeListener listener) {
        changeListeners.remove(listener);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        for (SessionChangeListener lst : changeListeners)
            if (lst != null)
                lst.onChange(key, this);
    }

    public void login(String c_id, String c_login, String v_password, String v_nama, String v_alamat, int c_group) {
        put(Constant.KEY_ISLOGIN, true);
        putString(Constant.KEY_CID, c_id);
        putString(Constant.KEY_LOGIN, c_login);
        putString(Constant.KEY_PASSWORD, v_password);
        putString(Constant.KEY_NAMA, v_nama);
        putString(Constant.KEY_ALAMAT, v_alamat);
        put(Constant.KEY_GROUP, c_group);


    }
    public void login(String username, String password, String fullname) {
        putString("user_name", username);
        putString("user_pass", password);
        putString("user_fullname", fullname);
    }

    public boolean isLogin() {
        return get(Constant.KEY_ISLOGIN, true) &&
                (getString(Constant.KEY_LOGIN, null) != null &&
                        getString(Constant.KEY_PASSWORD, null) != null);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor edit = preference.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public void put(String key, boolean value) {
        SharedPreferences.Editor edit = preference.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void put(String key, int value) {
        SharedPreferences.Editor edit = preference.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public void put(String key, float value) {
        SharedPreferences.Editor edit = preference.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public void put(String key, Set<String> value) {
        SharedPreferences.Editor edit = preference.edit();
        edit.putStringSet(key, value);
        edit.apply();
    }

    public void put(String key, long value) {
        SharedPreferences.Editor edit = preference.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public boolean get(String key, boolean defaultValue) {
        return preference.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return preference.getString(key, defaultValue);
    }

    public int get(String key, int defaultValue) {
        return preference.getInt(key, defaultValue);
    }

    public float get(String key, float defaultValue) {
        return preference.getFloat(key, defaultValue);
    }

    public long get(String key, long defaultValue) {
        return preference.getLong(key, defaultValue);
    }

    public Set<String> get(String key, Set<String> defaultValue) {
        return preference.getStringSet(key, defaultValue);
    }

    public void remove(String key) {
        SharedPreferences.Editor edit = preference.edit();
        edit.remove(key);
        edit.apply();
    }

    public void clear() {
        preference.edit().clear().apply();
    }

    public interface SessionChangeListener {
        void onChange(String key, SessionHandler sessionHandler);
    }
}
