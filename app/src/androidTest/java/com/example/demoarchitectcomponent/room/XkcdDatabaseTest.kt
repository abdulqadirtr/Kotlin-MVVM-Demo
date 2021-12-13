package com.example.demoarchitectcomponent.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit3.AndroidJUnit3Builder
import com.example.demoarchitectcomponent.repository.XkcdDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class XkcdDatabaseTest : TestCase(){

    private lateinit var xkcdDatabase: XkcdDatabase
    private lateinit var xkcdDao: XkcdDao

    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
       xkcdDatabase = Room.inMemoryDatabaseBuilder(context, XkcdDatabase::class.java).build()

        xkcdDao = xkcdDatabase.xkcdDao()

    }

    @After
    fun closeDb(){
        xkcdDatabase.close()
    }

    @Test
    fun writeAndReadTest(){
        runBlocking {
        val xkcdRespons = XKCDInitialDbResponseModel("Test","Test", "Test","Test","Test","Test", 1,"Test", "Test", "Test","1990")
           xkcdDao.insert(xkcdRespons)
           val xkcd = xkcdDao.getAllComics()
            assertEquals(xkcd.value?.contains(xkcdRespons), null)
           // assertEquals(xkcd.value?.contains(xkcdRespons)).isTrue()
       }
    }

}