package com.example.demoarchitectcomponent.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demoarchitectcomponent.room.XKCDInitialDbResponseModel
import com.example.demoarchitectcomponent.xkcdapi.XKCDInitialResponseModel

@Dao
interface XkcdDao {
    /**
     * Insert countries into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(comic: XKCDInitialDbResponseModel)

    @Update
    fun update(comic: XKCDInitialDbResponseModel)

    @Delete
    fun delete(comic: XKCDInitialDbResponseModel)

    @Query("select * from xkcd_table")
    fun getAllComic(): LiveData<List<XKCDInitialDbResponseModel>>

    @Query("delete from xkcd_table")
    fun deleteAllComics(){}

  /*  @Query("select * from xkcd_table order by title desc  LIMIT 1")
    fun getAllComics(): LiveData<List<XKCDInitialDbResponseModel>>*/
}