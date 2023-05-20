package com.francesco.damata.letmeknowv1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(
    entities = [User::class,Message::class],
    version = 1
)
abstract class LetMeKnowDB : RoomDatabase() {
    abstract fun DaoUser():DaoUser
    abstract fun DaoMessage():DaoMessage
    companion object {
        private var db: LetMeKnowDB? = null
        fun getInstance(context: Context): LetMeKnowDB {
            if(db==null) {
                db = databaseBuilder(
                    context,
                    LetMeKnowDB::class.java, "letMeKnowdbv7.db"
                ).createFromAsset("letMeKnowdbv7.db")
                    .build()
            }

            return db as LetMeKnowDB //Oggetto statico tornato dalla singleton
        }
    }
}
