package com.example.demoarchitectcomponent.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.demoarchitectcomponent.getOrAwaitValue
import com.example.demoarchitectcomponent.repository.XkcdDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class XkcdDatabaseTest : TestCase(){

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var xkcdDatabase: XkcdDatabase
    private lateinit var xkcdDao: XkcdDao

    @Before
    public override fun setUp() {

       xkcdDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), XkcdDatabase::class.java).allowMainThreadQueries().build()

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

           val xkcd = xkcdDao.getAllComic().getOrAwaitValue()
            assertEquals(xkcd.contains(xkcdRespons), true)
       }
    }

    @Test
    fun deleteXKCDItems(){
        runBlocking {
            val xkcdRespons = XKCDInitialDbResponseModel("Test","Test", "Test","Test","Test","Test", 1,"Test", "Test", "Test","1990")

            xkcdDao.insert(xkcdRespons)

            xkcdDao.delete(xkcdRespons)

            val xkcd = xkcdDao.getAllComic().getOrAwaitValue()
            assertEquals(xkcd.isNullOrEmpty(), true)

        }
    }

}