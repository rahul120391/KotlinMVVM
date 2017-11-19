package com.example.rahulkumar.mvvmarchitecture.View

import android.app.Application

/**
 * Created by rahulkumar on 05/11/17.
 */
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        SingletonContext.init(this)
    }
}