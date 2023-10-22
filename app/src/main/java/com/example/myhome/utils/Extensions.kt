package com.example.myhome.utils

import android.view.View
import android.widget.ImageView

fun ImageView.showImage(isFavorites:Boolean){
    if (isFavorites)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}