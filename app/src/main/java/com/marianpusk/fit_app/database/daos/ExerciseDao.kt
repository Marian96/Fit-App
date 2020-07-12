package com.marianpusk.fit_app.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.marianpusk.fit_app.database.entities.ExerciseEntity

@Dao
interface ExerciseDao{

    @Insert
    fun insertExercise(exercise: ExerciseEntity)

    @Update
    fun update(exercise: ExerciseEntity)

    @Query("DELETE FROM ExerciseEntity")
    fun delete()

    @Query("DELETE FROM ExerciseEntity where planID = :id")
    fun deleteFromPlan(id: Int)

    @Query("SELECT * FROM ExerciseEntity ORDER BY exerciseID DESC")
    fun getAllExercises():List<ExerciseEntity>

    @Query("SELECT * FROM ExerciseEntity WHERE planID = :key ORDER BY exerciseID DESC")
    fun getExercisesinPlan(key: Int): ExerciseEntity

    @Query("SELECT * FROM ExerciseEntity WHERE planID = :key AND trainingDay = :day ORDER BY exerciseID ASC")
    fun getExercisesInTrainingDay(key: Int,day: String) : List<ExerciseEntity>

    @Query("SELECT * FROM ExerciseEntity LEFT JOIN ExerciseDataEntity on ExerciseEntity.exerciseID = ExerciseDataEntity.exerciseId WHERE ExerciseDataEntity.date = :date")
    fun getAllExercisesonDate(date: Long) : List<ExerciseEntity>

    @Query("DELETE FROM ExerciseEntity WHERE exerciseID = :id")
    fun deleteExercise(id: Int)

    @Query("SELECT * FROM ExerciseEntity WHERE exerciseID = :id")
    fun getExerciseById(id: Int): ExerciseEntity
}
