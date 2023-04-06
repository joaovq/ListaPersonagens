package com.example.listapersonagens.ui.utils.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.listapersonagens.R
import com.example.listapersonagens.model.domain.CharacterType

fun View.visible() {
    if (this.visibility != View.VISIBLE) this.visibility = View.VISIBLE
}

fun View.gone() {
    if (this.visibility != View.GONE) this.visibility = View.GONE
}

fun ImageView.loadImage(
    view: View = this,
    source: String,
    @DrawableRes errorImage: Int = R.drawable.ic_launcher_background,
) {
    Glide.with(view)
        .load(source)
        .error(errorImage)
        .into(this)
}
