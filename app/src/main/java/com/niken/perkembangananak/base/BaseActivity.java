package com.niken.perkembangananak.base;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.view.View;
import android.widget.Toast;

import com.niken.perkembangananak.Constant;
import com.niken.perkembangananak.Utils;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private T mViewDataBinding;
    private V mViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    public T getBinding() {
        return mViewDataBinding;
    }

    public void hideKeyboard() {
        Utils.hideKeyboard(getCurrentFocus());
    }

    public void hideKeyboard(View view) {
        Utils.hideKeyboard(view);
    }

    public boolean isNetworkConnected() {
        return Utils.isNetworkConnected(getApplicationContext());
    }

    private void performDataBinding() {
        if (getLayoutId() == -1 || getViewModel() == null || getBindingVariable() == -1)
            return;
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewModel = getViewModel();
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mViewModel != null)
            this.mViewModel.onCleared();
    }
}