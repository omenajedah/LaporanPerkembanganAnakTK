package com.niken.perkembangananak.base;

import androidx.databinding.BindingAdapter;
import android.graphics.Bitmap;

import androidx.annotation.IdRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.databinding.InverseBindingAdapter;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.niken.perkembangananak.Utils;

import java.text.ParseException;
import java.util.Date;

public class BindingHolder {

    @BindingAdapter("keyListener")
    public static void setOnKeyListener(View view, View.OnKeyListener keyListener) {
        view.setOnKeyListener(keyListener);
    }

    @InverseBindingAdapter(attribute ="dateFromText", event ="onTextChanged")
    public static Date getDateFromText(EditText editText) {
        Date date = null;
        try {
            date = Utils.parseDate(editText.getText().toString(), "dd MMMM yyyy");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
}
