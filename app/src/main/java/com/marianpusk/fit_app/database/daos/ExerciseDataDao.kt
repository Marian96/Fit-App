package com.marianpusk.fit_app.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.marianpusk.fit_app.database.entities.ExerciseDataEntity

@Dao
interface ExerciseDataDao{

    @Insert
    fun insertExerciseData(exercise: ExerciseDataEntity)

    @Update
    fun update(exercise: ExerciseDataEntity)

    @Query("DELETE FROM ExerciseDataEntity")
    fun deleteExerciseData()

    @Query("SELECT * FROM ExerciseDataEntity ORDER BY exerciseDataID DESC")
    fun getAllExercisesData():List<ExerciseDataEntity>

    @Query("SELECT date from ExerciseDataEntity WHERE exerciseDataID = 2")
    fun getDate(): Long

    @Query("SELECT * FROM ExerciseDataEntity AS ed LEFT JOIN ExerciseEntity AS ee ON ed.exerciseId = ee.exerciseID WHERE exerciseName = :name ORDER BY date ASC")
    fun getAllDataOfExercise(name : String) : List<ExerciseDataEntity>

    @Query("SELECT * FROM ExerciseDataEntity AS ed LEFT JOIN ExerciseEntity AS ee ON ed.exerciseId = ee.exerciseID WHERE exerciseName = :name ORDER BY ed.date DESC limit 1")
    fun getLatestWorkout(name: String) :ExerciseDataEntity

    @Query("SELECT date FROM ExerciseDataEntity")
    fun getDates(): List<Long>

//    @Query("SELECT ee.muscleGroup,ee.exerciseName,tp.planName,ee.sets,ee.reps,ed.weight FROM " +
//            "ExerciseDataEntity AS ed LEFT JOIN ExerciseEntity AS ee" +
//            " ON ed.exerciseId = ee.exerciseID LEFT JOIN TrainingPlanEntity AS tp ON " +
//            "ee.planID = tp.planId WHERE ed.date >= :date AND ed.date <:date + 86400000")
//    fun getExercisesDataByDate(date: Long) : List<HistoryEntity>

//    @Query("SELECT sets FROM ExerciseDataEntity AS ed" +
//            " LEFT JOIN ExerciseEntity AS e ON ed.exerciseId = e.exerciseID")
//    fun getSets(): Int


}

