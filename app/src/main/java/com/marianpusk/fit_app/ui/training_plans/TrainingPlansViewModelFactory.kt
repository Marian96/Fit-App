package com.marianpusk.fit_app.ui.training_plans

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marianpusk.fit_app.database.TrainingPlanDatabase
import com.marianpusk.fit_app.database.daos.TrainingPlanDao

class TrainingPlansViewModelFactory (
    private val dataSource: TrainingPlanDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainingPlansViewModel::class.java)) {
            return TrainingPlansViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}