package com.marianpusk.fit_app.ui.adding_plan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.marianpusk.fit_app.database.daos.TrainingPlanDao
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity
import kotlinx.coroutines.*

class AddingPlanViewModel(
    val database: TrainingPlanDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val trainingPlans = database.getAllPlans()

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