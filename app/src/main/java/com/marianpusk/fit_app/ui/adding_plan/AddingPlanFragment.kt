package com.marianpusk.fit_app.ui.adding_plan



import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.marianpusk.fit_app.R
import com.marianpusk.fit_app.database.TrainingPlanDatabase
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity
import com.marianpusk.fit_app.databinding.FragmentAddingPlanBinding


/**
 * A simple [Fragment] subclass.
 */
class AddingPlanFragment : Fragment() {

    private lateinit var addingPlanViewModel: AddingPlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddingPlanBinding>(inflater, R.layout.fragment_adding_plan, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = TrainingPlanDatabase.getInstance(application).trainingPlanDatabase
        val viewModelFactory = AddingPlanViewModelFactory(dataSource,application)

        addingPlanViewModel =
            ViewModelProviders.of(this,viewModelFactory).get(AddingPlanViewModel::class.java)

        binding.setLifecycleOwner(this)

        val numberPicker = binding.numberPicker
        numberPicker.displayedValues = arrayOf("1 day","2 days","3 days","4 days","5 days","6 days","7 days")
        numberPicker.minValue = 1
        numberPicker.maxValue = 7
        // numberPicker.displayedValues = numDays

        var numOfDays = 0
        val newTraining = binding.name.text.toString()
        val newPlan = TrainingPlanEntity()


        numberPicker.setOnValueChangedListener { _, _, newVal ->
//
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)

            numOfDays = newVal


        }

        binding.ConfirmBtn.setOnClickListener {

            if (numOfDays > 0 && newTraining.isNotEmpty()){
                newPlan.days = numOfDays
                newPlan.planName = newTraining
            }

            Log.v("novy plan",newPlan.toString())
            addingPlanViewModel.insertPlan(newPlan)
            this.findNavController().navigate(R.id.action_addingPlanFragment_to_nav_training_plans)
        }

        binding.CancelBtn.setOnClickListener{

            this.findNavController().navigate(R.id.action_addingPlanFragment_to_nav_training_plans)
        }



        return binding.root
    }


}
