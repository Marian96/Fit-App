package com.marianpusk.fit_app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity()
data class TrainingPlanEntity(
    @PrimaryKey(autoGenerate = true)
    var planId: Int = 0,

    @ColumnInfo(name = "planName")
    var planName: String = "New plan",

    @ColumnInfo(name = "days")
    var days: Int = 3
)

