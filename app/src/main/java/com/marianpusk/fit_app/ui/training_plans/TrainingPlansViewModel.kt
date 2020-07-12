package com.marianpusk.fit_app.ui.training_plans

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marianpusk.fit_app.database.TrainingPlanDatabase
import com.marianpusk.fit_app.database.daos.TrainingPlanDao
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity
import kotlinx.coroutines.*

class TrainingPlansViewModel(
    val database: TrainingPlanDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val trainingPlans = database.getAllPlans()

    private suspend fun deletePlan(id: Int){
        withContext(Dispatchers.IO){
            database.deletePlan(id)
        }
    }

    private suspend fun deleteAll(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    private suspend fun insert(trainingPlan: TrainingPlanEntity) {
        withContext(Dispatchers.IO) {
            database.insert(trainingPlan)
        }
    }

    private suspend fun update(trainingPlan: TrainingPlanEntity){
        withContext(Dispatchers.IO){
            database.update(trainingPlan)
        }
    }


    fun onDelete(id: Int){
        uiScope.launch {
            deletePlan(id)
        }
    }

    fun clear(){
        uiScope.launch {
            deleteAll()
        }
    }

    fun insertPlan(trainingPlan: TrainingPlanEntity){
        uiScope.launch {
            insert(trainingPlan)
            update(trainingPlan)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}