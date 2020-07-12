package com.marianpusk.fit_app.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.marianpusk.fit_app.database.entities.PersonalEntity

@Dao
interface PersonalDao {

    @Insert
    suspend fun insert(personalRecord: PersonalEntity)

    @Update
    suspend fun update(personalRecord: PersonalEntity)

    @Query("DELETE FROM PersonalEntity")
    suspend fun clear()

    @Query("SELECT * FROM PersonalEntity ORDER BY personal_record_id DESC")
    suspend fun getAllRecords():List<PersonalEntity>

    @Query("SELECT * FROM PersonalEntity ORDER BY timestamp DESC limit 1")
    suspend fun getLatestRecord(): PersonalEntity
}