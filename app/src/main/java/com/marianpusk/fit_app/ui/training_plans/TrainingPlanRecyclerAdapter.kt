package com.marianpusk.fit_app.ui.training_plans

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity
import com.marianpusk.fit_app.databinding.TrainingsBinding


class TrainingPlanRecyclerAdapter(val clickListener: TrainingPlanListener,
                                  val delete: DeletePlanListener) : ListAdapter<TrainingPlanEntity, TrainingPlanRecyclerAdapter.PlanViewHolder>(TrainingPlandDiffCallback()){

  //  private var items = listOf<TrainingPlanEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        return PlanViewHolder.from(parent)
    }

//    override fun getItemCount(): Int {
//        return items.size
//    }


//    fun submitList(planList:List<TrainingPlanEntity>) {
//        items = planList
//    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        when(holder) {
            is PlanViewHolder -> {

                holder.bind(getItem(position),clickListener, delete)
            }
        }

    }


    class PlanViewHolder(
        val binding: TrainingsBinding
    ): RecyclerView.ViewHolder(binding.root) {


        fun bind(
            item: TrainingPlanEntity,
            clickListener: TrainingPlanListener,
            deleteClickListener: DeletePlanListener
        ) {
            binding.clickListener = clickListener
            binding.imageClickListener = deleteClickListener
            binding.plan = item
            binding.executePendingBindings()


        }

        companion object {
            fun from(parent: ViewGroup): PlanViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TrainingsBinding.inflate(layoutInflater,parent,false)
                return PlanViewHolder(binding)
            }
        }
    }

}

class TrainingPlandDiffCallback : DiffUtil.ItemCallback<TrainingPlanEntity>() {
    override fun areItemsTheSame(oldItem: TrainingPlanEntity, newItem: TrainingPlanEntity): Boolean {
        return oldItem.planId == newItem.planId
    }

    override fun areContentsTheSame(oldItem: TrainingPlanEntity, newItem: TrainingPlanEntity): Boolean {
        return oldItem == newItem
    }
}

class TrainingPlanListener(val clickListener: (planId: Int) -> Unit){

    fun onClick(plan: TrainingPlanEntity) = clickListener(plan.planId)
}


class DeletePlanListener(val clickListener: (planId: Int) -> Unit){

    fun onClick(plan: TrainingPlanEntity) = clickListener(plan.planId)
}

