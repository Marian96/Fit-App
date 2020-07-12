package com.marianpusk.fit_app.ui.adding_plan

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marianpusk.fit_app.database.daos.TrainingPlanDao
import com.marianpusk.fit_app.ui.training_plans.TrainingPlansViewModel

class AddingPlanViewModelFactory (
    private val dataSource: TrainingPlanDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddingPlanViewModel::class.java)) {
            return AddingPlanViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}