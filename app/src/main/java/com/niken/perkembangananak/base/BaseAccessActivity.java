package com.niken.perkembangananak.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.SessionHandler;
import com.niken.perkembangananak.activity.login.LoginActivity;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

/**
 * Created by Firman on 4/15/2019.
 */
public abstract class BaseAccessActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends BaseActivity<T, V> implements SessionHandler.SessionChangeListener {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getViewModel() != null)
            getViewModel().getSessionHandler().registerSessionChangeListener(this);
    }

    @Override
    public void onChange(String key, SessionHandler sessionHandler) {
        Log.d(TAG, "KEY = "+key);
        if (key.equals(Constant.KEY_ISLOGIN) && !sessionHandler.get(Constant.KEY_ISLOGIN, false)) {
            finish();
            Toast.makeText(this, "Session habis, silahkan login kembali", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getViewModel() != null)
            getViewModel().getSessionHandler().unregisterSessionChangeListener(this);
    }
}
