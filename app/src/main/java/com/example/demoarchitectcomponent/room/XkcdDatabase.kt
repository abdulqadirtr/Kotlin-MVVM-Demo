package com.example.demoarchitectcomponent.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demoarchitectcomponent.repository.XkcdDao


@Database(entities = [XKCDInitialDbResponseModel::class], version = 1)
abstract class XkcdDatabase: RoomDatabase() {

    abstract fun xkcdDao(): XkcdDao

    companion object {
        private var instance: XkcdDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): XkcdDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, XkcdDatabase::class.java,
                    "xkcd_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: XkcdDatabase) {
            val xkcdDao = db.xkcdDao()
        }
    }
}