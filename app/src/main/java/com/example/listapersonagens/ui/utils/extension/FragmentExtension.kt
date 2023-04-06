package com.example.listapersonagens.ui.utils.extension

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(context: Context = requireContext(), message: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context, message, length).show()
}
