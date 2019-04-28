package com.niken.perkembangananak.base;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.niken.perkembangananak.Utils;

import java.text.ParseException;
import java.util.Date;

public class BindingHolder {

    @BindingAdapter("keyListener")
    public static void setOnKeyListener(View view, View.OnKeyListener keyListener) {
        view.setOnKeyListener(keyListener);
    }

    @BindingAdapter("endDrawableClick")
    public static void setOnCompoundDrawableClickListener(EditText view,
                                                          View.OnClickListener listener) {
        view.setOnTouchListener((v, event) -> {
            boolean hasConsumed = false;
            if (v instanceof EditText) {
                if (event.getRawX() >= v.getRight() - view.getCompoundDrawablesRelative()[2].getBounds().width()) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        listener.onClick(v);
                    }
                    hasConsumed = true;
                }
            }
            return hasConsumed;
        });
    }

    @BindingAdapter("recyclerAdapter")
    public static void setRecyclerAdapter(RecyclerView recyclerView, BaseRecyclerAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
