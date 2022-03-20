package com.example.demoarchitectcomponent.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demoarchitectcomponent.repository.XkcdDao


@Database(entities = [XKCDInitialDbResponseModel::class],  version = 2)
abstract class XkcdDatabase: RoomDatabase() {

    abstract fun xkcdDao(): XkcdDao

    companion object {

        @Volatile
        private var instance: XkcdDatabase? = null


        fun getInstance(ctx: Context): XkcdDatabase {
           return when (val temp = instance) {
                null -> synchronized(this) {
                    Room.databaseBuilder(
                        ctx.applicationContext, XkcdDatabase::class.java,
                        "xkcd_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                else -> temp
            }

        }

    /*    private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: XkcdDatabase) {
            val xkcdDao = db.xkcdDao()
        }*/
    }
}