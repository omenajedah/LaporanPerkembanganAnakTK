package com.niken.perkembangananak.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Toast;

import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.activity.home.HomeActivity;
import com.niken.perkembangananak.base.BaseActivity;
import com.niken.perkembangananak.databinding.ActivityLoginBinding;

/**
 * Created by Firman on 12/15/2018.
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>
        implements LoginViewModel.LoginListener {

    private LoginViewModel viewModel;

    public static void startThisActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }


    @Override
    public void onLoginSuccess() {
        HomeActivity.startThisActivity(this);
        finish();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Login gagal, silahkan coba lagi", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new LoginViewModel(this, this);

        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        if (viewModel.getSessionHandler().isLogin()) {
            overridePendingTransition(0,0);
            HomeActivity.startThisActivity(this);
            finish();
            return;
        }
    }
}
