package com.francesco.damata.letmeknowv1.screen
import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
@Parcelize
class MyModelScreen :Parcelable{
    @IgnoredOnParcel
    private var _layout=mutableStateOf(1)
    var layout: Int
    get() {
        return _layout.value
    }
   set(value) {
       _layout.value = value
   }
}