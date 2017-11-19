package com.example.rahulkumar.mvvmarchitecture.View

import android.content.Context

/**
 * Created by rahulkumar on 05/11/17.
 */
object SingletonContext {

       var context:Context?=null
       fun init(applicationContext: Context)
       {
           if(context==null){
              context=applicationContext
           }

       }

       fun getAppContext():Context{
           return context!!
       }


}