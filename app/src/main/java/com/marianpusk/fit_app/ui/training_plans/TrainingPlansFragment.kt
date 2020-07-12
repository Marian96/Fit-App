package com.marianpusk.fit_app.ui.training_plans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.marianpusk.fit_app.R
import com.marianpusk.fit_app.database.TrainingPlanDatabase
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity
import com.marianpusk.fit_app.databinding.FragmentGalleryBinding
import com.marianpusk.fit_app.ui.adding_plan.AddingPlanFragment
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.adding_plan.view.*

class TrainingPlansFragment : Fragment() {

    private lateinit var trainingPLansViewModel: TrainingPlansViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentGalleryBinding>(inflater, R.layout.fragment_gallery, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = TrainingPlanDatabase.getInstance(application).trainingPlanDatabase
        val viewModelFactory = TrainingPlansViewModelFactory(dataSource,application)

        trainingPLansViewModel =
            ViewModelProviders.of(this,viewModelFactory).get(TrainingPlansViewModel::class.java)

        binding.setLifecycleOwner(this)



        val add = binding.add
        add.setOnClickListener { view ->

           this.findNavController().navigate(
               R.id.action_nav_training_plans_to_addingPlanFragment
           )
//
//            val mDialogView = LayoutInflater.from(application).inflate(R.layout.adding_plan,null)
//
//            val mBuilder = AlertDialog.Builder(application)
//                .setView(mDialogView)
//
//            val numberPicker = mDialogView.numberPicker
//
//
//            numberPicker.displayedValues = arrayOf("1 day","2 days","3 days","4 days","5 days","6 days","7 days")
//            numberPicker.minValue = 1
//            numberPicker.maxValue = 7
//            // numberPicker.displayedValues = numDays
//            var numOfDays = 3
//
//
//            val mAlertDialog = mBuilder.show()
//
//
//            mDialogView.ConfirmBtn.setOnClickListener {
//
//
//                    var newTraining = mDialogView.name.text.toString()
//                    var plan = TrainingPlanEntity()
//
//                    if( numOfDays > 0 && newTraining.isNotEmpty()){
//                        plan.days = numOfDays
//                        plan.planName = newTraining
//                    }
//
//                    trainingPLansViewModel.insertPlan(plan)
//
////                    withContext(Dispatchers.Main){
////                        addDataSet()
////                        planAdapter.notifyDataSetChanged()
////                    }
//
//
//
//
//
//                mAlertDialog.dismiss()
//
//                Toast.makeText(application, "training added ", Toast.LENGTH_SHORT).show()
//
//
//            }
//
//            mDialogView.CancelBtn.setOnClickListener {
//
//                mAlertDialog.dismiss()
//            }

      }





        val planAdapter = TrainingPlanRecyclerAdapter( TrainingPlanListener {
                planId ->

//            val intent = Intent(application,
//                TrainingPlan::class.java)
//            intent.putExtra("planid",planId.toString())
//            startActivity(intent)
            Toast.makeText(application,"Nazdar",Toast.LENGTH_SHORT).show()

        }, DeletePlanListener {
                planId ->

            val deleteDialog = LayoutInflater.from(application).inflate(R.layout.delete_dialog,null)
            val mBuilder = AlertDialog.Builder(application)
                .setView(deleteDialog)
            val alertDialog = mBuilder.show()

            deleteDialog.dialogConfirmBtn.setOnClickListener{
                trainingPLansViewModel.onDelete(planId)
                alertDialog.dismiss()
//                    withContext(Dispatchers.Main){
//                        addDataSet()
//                        planAdapter.notifyDataSetChanged()
//                        alertDialog.dismiss()
//                    }
                }
            deleteDialog.declineBtn.setOnClickListener{
                alertDialog.dismiss()
            }




        })

        binding.recycleView.adapter = planAdapter

        trainingPLansViewModel.trainingPlans.observe(this, Observer {
            it?.let {
                planAdapter.submitList(it)
            }
        })



        return binding.root
    }
}