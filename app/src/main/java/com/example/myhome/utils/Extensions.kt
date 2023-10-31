package com.example.myhome.utils

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.example.myhome.R

fun ImageView.showImage(isFavorites:Boolean){
    if (isFavorites)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun EditText.checkingForEmptyLine(): String{
    if (this.text.isNotEmpty()){
        return this.text.toString()
    }else{
        throw EditTextEmptyLineException(context.getString(R.string.the_field_is_not_filled))
    }
}