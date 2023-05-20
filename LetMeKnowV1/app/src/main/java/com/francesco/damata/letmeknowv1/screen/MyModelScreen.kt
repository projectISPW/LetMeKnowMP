package com.francesco.damata.letmeknowv1.screen
import android.os.Parcelable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.francesco.damata.letmeknowv1.db.User
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
@Parcelize
class MyModelScreen :Parcelable{
    @IgnoredOnParcel
    private var _user=mutableStateOf("")
    var user:String
    get(){
        return _user.value
    }
    set(edit){
        _user.value=edit
    }

    @IgnoredOnParcel
    private var _userClass=mutableStateOf(User("","","",0,0,0))
    var userClass:User
    get(){
        return _userClass.value
    }
    set(edit){
        _userClass.value=edit
    }


    @IgnoredOnParcel
    private var _log=mutableStateOf(false)
    var log:Boolean
    get(){
        return _log.value
    }
    set(noSense){
        _log.value=!_log.value
    }










    @IgnoredOnParcel
    private var _chatWith=mutableStateOf(User("0000000","0","0",0,0,0))
    var chatWith:String
    get() {
        return _chatWith.value.userid
    }
    set(userid) {
        //leggo da db in fase di login dopo aver verificato che password e userid coincidono
        _chatWith.value=User(userid,"0","0",0,0,0)
    }

    @IgnoredOnParcel
    private var _onSearch=mutableStateOf(false)
    var onSearch:Boolean
    get(){
        return _onSearch.value
    }
    set(noSense){
        _onSearch.value=!_onSearch.value
    }


    @IgnoredOnParcel
    private var _txtSrc=mutableStateOf("")
    var txtSrc:String
    get(){
        return _txtSrc.value
    }
    set(edit){
        _txtSrc.value=edit
    }

}