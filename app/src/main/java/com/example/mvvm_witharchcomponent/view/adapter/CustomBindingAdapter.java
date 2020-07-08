package com.example.mvvm_witharchcomponent.view.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class CustomBindingAdapter {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
