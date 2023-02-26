package com.assignment.neosoftassignment.view.adapter

import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.assignment.neosoftassignment.R

object BindingAdapter {


    /*@JvmStatic
    @BindingAdapter("animation")
    fun loadAnimation(view: ImageView, boolean: Boolean) {
        val slideAnimation = AnimationUtils.loadAnimation(view.context, R.anim.silde_side)
        view.startAnimation(slideAnimation)
    }*/
    @androidx.databinding.BindingAdapter("animation")
    fun loadAnimation(view: ImageView, boolean: Boolean) {
        val slideAnimation = AnimationUtils.loadAnimation(view.context, R.anim.silde_side)
        view.startAnimation(slideAnimation)
    }
}