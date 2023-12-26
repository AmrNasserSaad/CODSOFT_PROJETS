package com.example.todolistapp

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class taskNetwork:Application() {
    override fun onCreate() {
        super.onCreate()
        // Enable offline persistence
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

}