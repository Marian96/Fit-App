package com.marianpusk.fit_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marianpusk.fit_app.database.daos.PersonalDao
import com.marianpusk.fit_app.database.entities.PersonalEntity

@Database(entities = [PersonalEntity::class],version = 2,exportSchema = false)
//@TypeConverters(Converters::class)
abstract class PersonalDatabase : RoomDatabase() {

    abstract val personalDatabase: PersonalDao


    companion object {

        @Volatile
        private var INSTANCE: PersonalDatabase? = null

        fun getInstance(context: Context): PersonalDatabase {
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
                        PersonalDatabase::class.java,
                        "personal_database"
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