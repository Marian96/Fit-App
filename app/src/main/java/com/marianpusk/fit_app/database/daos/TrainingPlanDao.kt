package com.marianpusk.fit_app.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity

@Dao
interface TrainingPlanDao {

    @Insert
    suspend fun insert(plan: TrainingPlanEntity)

    @Update
    fun update(plan: TrainingPlanEntity)

    @Query("DELETE FROM TrainingPlanEntity")
    fun clear()

    @Query("SELECT * FROM TrainingPlanEntity ORDER BY planId DESC")
    fun getAllPlans(): LiveData<List<TrainingPlanEntity>>

    @Query("SELECT planId FROM TrainingPlanEntity WHERE planName = :name")
    fun getPlanIdByName(name:String): Int

    @Query("SELECT planName FROM TrainingPlanEntity")
    fun getPlanNames(): List<String>

    @Query("SELECT days FROM TrainingPlanEntity where planName = :name")
    fun getDaysOfPlan(name:String) : Int

    @Query("SELECT * FROM TrainingPlanEntity WHERE planId = :key")
    fun getPlanWithId(key: Int): TrainingPlanEntity

    @Query("DELETE FROM TrainingPlanEntity WHERE planId = :key")
    fun deletePlan(key: Int)
}