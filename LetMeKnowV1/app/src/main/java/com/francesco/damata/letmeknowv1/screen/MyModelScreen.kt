package com.francesco.damata.letmeknowv1.screen
import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.francesco.damata.letmeknowv1.db.User
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
@Parcelize
class MyModelScreen :Parcelable{
    @IgnoredOnParcel
    private var _usr=mutableStateOf("")
    var usr:String
    get(){
        return _usr.value
    }
    set(edit){
        _usr.value=edit
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
    private var _onSearchEmo= mutableStateOf(1)
    var onSearchEmo: Int
        get(){
            return _onSearchEmo.value
        }
        set(emo){
            _onSearchEmo.value=emo
        }

    @IgnoredOnParcel
    private var _onSearchLv = mutableStateOf(1)
    var onSearchLv: Int
        get(){
            return _onSearchLv.value
        }
        set(lv){
            _onSearchLv.value=lv
        }

    @IgnoredOnParcel
    private var _onSearchOpt = mutableStateOf(1)
    var onSearchOpt: Int
        get(){
            return _onSearchOpt.value
        }
        set(opt){
            _onSearchOpt.value=opt
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

    @IgnoredOnParcel
    private var _onVisitUserClass=mutableStateOf(false)
    var onVisitUserClass:Boolean
        get(){
            return _onVisitUserClass.value
        }
        set(edit){
            _onVisitUserClass.value=edit
        }

    @IgnoredOnParcel
    private var _usrVisit=mutableStateOf(User("0000000","0","0",0,0,0))
    var usrVisit:User
        get(){
            return _usrVisit.value
        }
        set(edit){
            _usrVisit.value=edit
        }
}