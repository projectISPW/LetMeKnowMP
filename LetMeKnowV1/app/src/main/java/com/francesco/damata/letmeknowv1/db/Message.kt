package com.francesco.damata.letmeknowv1.db

import androidx.room.Entity

@Entity(primaryKeys = ["dateTime", "sender","receiver","text"])
data class Message(var dateTime: String,
                   var sender: String,
                   var receiver: String,
                   var text: String
                   )