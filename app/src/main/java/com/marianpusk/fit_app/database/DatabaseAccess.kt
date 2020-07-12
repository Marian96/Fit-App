package com.marianpusk.fit_app.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory


class DatabaseAccess private constructor(context: Context) {

    private val openHelper: SQLiteOpenHelper
    private var db: SQLiteDatabase? = null
    private var c: Cursor? = null


    init {
        this.openHelper = DatabaseOpenHelper(context)
    }

    fun open() {
        this.db = openHelper.writableDatabase
    }

    fun close() {
        if (db != null) {
            this.db!!.close()
        }

    }

    companion object {
        private var instance: DatabaseAccess? = null

        fun getInstance(context: Context): DatabaseAccess {
            if (instance == null) {
                instance =
                    DatabaseAccess(context)
            }

            return instance!!
        }
    }



     fun getMuscleGroups(): ArrayList<String> {

              val muscles = ArrayList<String>()
              c = null
              c = db?.rawQuery("SELECT * FROM MuscleGroups",null)

              val nameIx: Int? = c?.getColumnIndex("name")

              c?.moveToFirst()

              while (!c!!.isAfterLast) {

                  if(c!!.getString(nameIx!!) != null) {
                      muscles.add(c!!.getString(nameIx))
                  }

                  c?.moveToNext()

              }

              c?.close()

         return muscles
    }

    fun getExercises(name: String) :ArrayList<String>{
        val exercises = ArrayList<String>()
        c = null

        c = db?.rawQuery("SELECT * FROM Exercises where muscle = ?",arrayOf(name))

        val exerciseIx = c?.getColumnIndex("exercise")
        c?.moveToFirst()

        while (!c!!.isAfterLast) {

            if(c!!.getString(exerciseIx!!) != null) {
                exercises.add(c!!.getString(exerciseIx))
            }

            c?.moveToNext()

        }
        c?.close()
        return exercises
    }

    fun addMuscleGroup(name: String) {

        val sqlString = "INSERT INTO MuscleGroups (name) VALUES (?)"

        val statement = db!!.compileStatement(sqlString)
        statement.bindString(1,name)
        statement.execute()

    }




    //funkcia na ziskanie obrazkov daneho cviku z databazy

    fun getExerciseImages(name: String) :ArrayList<Bitmap> {

        val exerciseImages = ArrayList<Bitmap>()
        c = null

        c = db?.rawQuery("SELECT * FROM Exercise where name = ?",arrayOf(name))

        val imageIx = c?.getColumnIndex("image")

        c?.moveToFirst()

        while (!c!!.isAfterLast) {

            val byteArray = c!!.getBlob(imageIx!!)
            val image = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)

            if(image != null) {
                exerciseImages.add(image)
            }

            c?.moveToNext()

        }

        c?.close()

        return exerciseImages
    }

    fun getMuscleGroupName(exercise: String):String{

        c = db!!.rawQuery("SELECT * FROM Exercises where exercise = ?", arrayOf(exercise))

        val muscleIx = c!!.getColumnIndex("muscle")

        c!!.moveToFirst()
        var muscleName = "nic"
        val muscle = c!!.getString(muscleIx)
        if( muscle.isNotEmpty()) {
             muscleName = muscle

        }
        c!!.close()

        return muscleName
    }

    fun getExerciseDescription(name: String) :String {
        var description = ""

        c = db?.rawQuery("SELECT * FROM Exercises where exercise = ?",arrayOf(name))

        val descrIx = c?.getColumnIndex("description")

        c?.moveToFirst()

        if (c!!.getString(descrIx!!) != null) {

            description = c!!.getString(descrIx!!)
        }

        c?.close()

        return description
    }
}
