package com.marianpusk.fit_app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonalEntity(
    @PrimaryKey(autoGenerate = true)
    var personal_record_id: Int = 0,

    @ColumnInfo
    var weight: Int = 0,

    @ColumnInfo
    var height: Int = 0,

    @ColumnInfo
    var age: Int = 0,

    @ColumnInfo
    var timestamp: Long = 0,

    @ColumnInfo
    var bmi: Double = 0.0

                             )