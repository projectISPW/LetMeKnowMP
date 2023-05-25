package com.francesco.damata.letmeknowv1.db

import androidx.room.Entity

@Entity(primaryKeys = ["dateTime", "sender","reciver","text"])
data class Message(  var dateTime: String,
                     var sender: String,
                     var reciver: String,
                     var text: String
                   )