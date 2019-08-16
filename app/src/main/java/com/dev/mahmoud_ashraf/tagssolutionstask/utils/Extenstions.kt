package com.dev.mahmoud_ashraf.tagssolutionstask.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.dev.mahmoud_ashraf.tagssolutionstask.R
import kotlinx.android.synthetic.main.loading.view.*

fun Context.showLoadingDialog() : Dialog {
    val view = LayoutInflater.from(this).inflate(R.layout.loading, null)
    val alertDialog = AlertDialog.Builder(this).setView(view)
    val dialog = alertDialog.create()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    view.loading.loadImage(R.drawable.loading)
    dialog.show()

    return dialog
}

fun ImageView.loadImage(@DrawableRes drawableRes: Int) {
    Glide.with(this).load(drawableRes).fitCenter().into(this)
}