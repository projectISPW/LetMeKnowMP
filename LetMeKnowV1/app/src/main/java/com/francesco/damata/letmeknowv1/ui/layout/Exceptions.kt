package com.francesco.damata.letmeknowv1.ui.layout

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


class Exceptions{
    companion object{
        fun mailUsed(context:Context){
            context.toast("the mail in input is already used")
        }
        fun mailIncorrect(context:Context){
            context.toast("the format of the mail in input is uncorrected")
        }
        fun passwdNotEqual(context:Context){
            context.toast("the password are not equal each other")
        }
        fun credentialsError(context:Context){
            context.toast("uncorrected login ")
        }
    }

}