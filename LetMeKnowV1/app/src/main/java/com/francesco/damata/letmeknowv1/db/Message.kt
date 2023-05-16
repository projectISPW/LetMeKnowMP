package com.francesco.damata.letmeknowv1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["dateTime", "sender","reciver","text"])
data class Message(  var dateTime: String,
                     var sender: String,
                     var reciver: String,
                     var text: String
                   )