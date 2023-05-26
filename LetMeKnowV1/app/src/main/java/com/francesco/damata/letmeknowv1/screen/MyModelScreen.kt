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

    //could be cancelled by the using of the only userClass?
    private var _usr=mutableStateOf("")
    var usr:String
    get(){
        return _usr.value
    }
    set(edit){
        _usr.value=edit
    }


    //used fot get the parameter of the logged user
    @IgnoredOnParcel
    private var _userClass=mutableStateOf(User("","","",1,1,1))
    var userClass:User
    get(){
        return _userClass.value
    }
    set(edit){
        _userClass.value=edit
    }



    //it is used for go back to the search statement
    @IgnoredOnParcel
    private var _onSearchUsr=mutableStateOf(User("","","",1,1,1))
    var onSearchUsr:User
        get(){
            return _userClass.value
        }
        set(edit){
            _userClass.value=edit
        }

    @IgnoredOnParcel
    private var _usrVisit=mutableStateOf(User("","","",1,1,1))
    var usrVisit:User
        get(){
            return _usrVisit.value
        }
        set(edit){
            _usrVisit.value=edit
        }














    //for the user that i chat with i only use his userid
    @IgnoredOnParcel
    private var _chatWith :String=""
    var chatWith:String
    get() {
        return _chatWith
    }
    set(userid) {
        _chatWith=userid
    }




    //________________SEARCH LAYOUT________________//
    //it is used for know if there was a result search
    @IgnoredOnParcel
    private var _onSearch=mutableStateOf(false)
    var onSearch:Boolean
    get(){
        return _onSearch.value
    }
    set(value){
        _onSearch.value=value
    }

    private var _fromChat=mutableStateOf(false)
    var fromChat:Boolean
        get(){
            return _fromChat.value
        }
        set(value){
            _fromChat.value=value
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