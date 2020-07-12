package com.marianpusk.fit_app.database

import android.content.Context
import androidx.room.*
import com.marianpusk.fit_app.database.daos.ExerciseDao
import com.marianpusk.fit_app.database.daos.ExerciseDataDao
import com.marianpusk.fit_app.database.daos.TrainingPlanDao
import com.marianpusk.fit_app.database.entities.ExerciseDataEntity
import com.marianpusk.fit_app.database.entities.ExerciseEntity
import com.marianpusk.fit_app.database.entities.TrainingPlanEntity


//class Converters {
//    @TypeConverter
//    fun fromTimestamp(value: Long?): Date? {
//        return value?.let { Date(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: Date?): Long? {
//        return date?.time?.toLong()
//    }
//}


@Database(entities = [TrainingPlanEntity::class, ExerciseEntity::class, ExerciseDataEntity::class],version = 5,exportSchema = false)
//@TypeConverters(Converters::class)
abstract class TrainingPlanDatabase : RoomDatabase() {

    abstract val trainingPlanDatabase: TrainingPlanDao

    abstract val exerciseDao: ExerciseDao

    abstract val exerciseData: ExerciseDataDao

    companion object {

        @Volatile
        private var INSTANCE: TrainingPlanDatabase? = null

        fun getInstance(context: Context): TrainingPlanDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrainingPlanDatabase::class.java,
                        "training_plans_database"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}