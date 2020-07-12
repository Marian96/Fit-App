

package com.example.myfitnessapp.activities.trainingPlans

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.marianpusk.fit_app.R
import com.marianpusk.fit_app.database.entities.ExerciseEntity
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity


@BindingAdapter("planImage")
fun ImageView.setImage(item: TrainingPlanEntity?) {
    item?.let {
            setImageResource(R.drawable.ic_delete_forever)
    }
}

@BindingAdapter("exerciseDelete")
fun ImageView.setImage(item: ExerciseEntity?) {
    item?.let {
        setImageResource((R.drawable.ic_delete_forever))
    }
}

@BindingAdapter("planName")
fun TextView.setPlanName(item: TrainingPlanEntity?) {
    item?.let {
        text = item.planName
    }
}

@BindingAdapter("days")
fun TextView.setDaysString(item: TrainingPlanEntity?) {
    item?.let {
        if (item.days > 1){
            text = item.days.toString() + " days"
        }
        else {
            text = item.days.toString() + " day"
        }

    }
}

@BindingAdapter("muscleGroupName")
fun TextView.setMucleName(item: ExerciseEntity?) {
    item?.let {
        text = item.muscleGroup
    }
}

@BindingAdapter("exerciseName")
fun TextView.setExirciseName(item: ExerciseEntity?) {
    item?.let {
        text = item.exerciseName
    }
}

@BindingAdapter("reps")
fun TextView.setReps(item: ExerciseEntity?) {
    item?.let {
        text = "Reps: " + item.reps.toString()
    }
}

@BindingAdapter("sets")
fun TextView.setSets(item: ExerciseEntity?) {
    item?.let {
        text = "Sets: " + item.sets.toString()
    }
}


// Binding adapters for HistoryLIstRecycleAdapter


//@BindingAdapter("historyMuscleGroup")
//fun TextView.setMucleName(item: HistoryEntity?) {
//    item?.let {
//        text = item.muscleGroup
//    }
//}
//
//@BindingAdapter("historyExerciseName")
//fun TextView.setExirciseName(item: HistoryEntity?) {
//    item?.let {
//        text = item.exerciseName
//    }
//}
//
//@BindingAdapter("historyReps")
//fun TextView.setReps(item: HistoryEntity?) {
//    item?.let {
//        text = "Reps: " + item.reps.toString()
//    }
//}
//
//@BindingAdapter("historySets")
//fun TextView.setSets(item: HistoryEntity?) {
//    item?.let {
//        text = "Sets: " + item.sets.toString()
//    }
//}
//
//@BindingAdapter("historyPlanName")
//fun TextView.setPlanName(item: HistoryEntity?) {
//    item?.let {
//        text = item.planName
//    }
//}
//
//@BindingAdapter("historyWeight")
//fun TextView.setWeight(item: HistoryEntity?) {
//    item?.let {
//        text = "Weight: " + item.weight.toString()
//    }
//}