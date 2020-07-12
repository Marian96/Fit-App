package com.marianpusk.fit_app.database.entities

import androidx.room.*


@Entity( indices = arrayOf(Index(value = ["exerciseId"])),foreignKeys = [ForeignKey(entity = ExerciseEntity::class,
    parentColumns = ["exerciseID"],
    childColumns = ["exerciseId"],
    onDelete = ForeignKey.CASCADE)])
data class ExerciseDataEntity (
    @PrimaryKey(autoGenerate = true)
    var exerciseDataID: Int = 0,

    @ColumnInfo
    var exerciseId:Int = 0,

    @ColumnInfo
    var weight: Double = 0.0,

    @ColumnInfo
    var workload: Double = 0.0,

    @ColumnInfo
    var date: Long = 0


                          )