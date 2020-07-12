package com.marianpusk.fit_app.database.entities

import androidx.room.*

@Entity( indices = arrayOf(Index(value = ["planID"])),foreignKeys = [ForeignKey(entity = TrainingPlanEntity::class,
    parentColumns = ["planId"],
    childColumns = ["planID"],
    onDelete = ForeignKey.CASCADE)])
data class ExerciseEntity (
    @PrimaryKey(autoGenerate = true)
    var exerciseID: Int = 0,

    @ColumnInfo
    var planID:Int = 0,

    @ColumnInfo
    var trainingDay: String = "day 1",

    @ColumnInfo
    var muscleGroup: String = "",

    @ColumnInfo
    var exerciseName: String = "",

    @ColumnInfo
    var sets:Int = 0,

    @ColumnInfo
    var reps: Int = 0

                          )