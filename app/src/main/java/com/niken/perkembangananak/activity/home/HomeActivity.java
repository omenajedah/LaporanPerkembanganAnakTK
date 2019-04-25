package com.niken.perkembangananak.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.niken.perkembangananak.BR;
import com.niken.perkembangananak.R;
import com.niken.perkembangananak.base.BaseAccessActivity;
import com.niken.perkembangananak.base.BaseFragment;
import com.niken.perkembangananak.databinding.ActivityHomeBinding;
import com.niken.perkembangananak.fragment.jadwal.JadwalFragment;
import com.niken.perkembangananak.fragment.hasil.HasilFragment;
import com.niken.perkembangananak.fragment.kelas.KelasFragment;
import com.niken.perkembangananak.fragment.profile.ProfileFragment;
import com.niken.perkembangananak.fragment.masterdata.MasterDataFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Firman on 12/15/2018.
 */
public class HomeActivity extends BaseAccessActivity<ActivityHomeBinding, HomeViewModel>
        implements HomeViewModel.HomeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private HomeViewModel viewModel;

    public static void start(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    public void onLogout() {
        finish();
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        if (viewModel == null)
            viewModel = new HomeViewModel(this, this);
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(getBinding().toolbar);
        getBinding().bottomNavigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new JadwalFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_jadwal:
                loadFragment(new JadwalFragment());
                break;
            case R.id.menu_hasil:
                loadFragment(new HasilFragment());
                break;
            case R.id.menu_kelas:
                loadFragment(new KelasFragment());
                break;
            case R.id.menu_wali:
                loadFragment(new MasterDataFragment());
                break;
            case R.id.menu_account:
                loadFragment(new ProfileFragment());
                break;
        }
        return true;
    }

    private void loadFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_root, fragment)
                .commitAllowingStateLoss();
    }
}
