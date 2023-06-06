package com.francesco.damata.letmeknowv1.ui.layout

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


class Exceptions{
    companion object{
        fun mailExeption(context:Context){
            context.toast("the mail in input is already used or the format is uncorrect")
        }
    }
}