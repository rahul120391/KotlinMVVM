package com.example.rahulkumar.mvvmarchitecture.Utils

import android.content.Context
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.text.style.StyleSpan
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.rahulkumar.mvvmarchitecture.View.SingletonContext

/**
 * Created by rahulkumar on 06/11/17.
 */
object Utility {


    fun checkNetworkConnectivity():Boolean{
        val connectivityManager= SingletonContext.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return networkInfo != null &&
                networkInfo.isConnectedOrConnecting();

    }

    fun loadImageFromUrl(view:ImageView,url:String?){
        if(url!=null){
            Glide.with(view.context).load(url).into(view)
        }

    }

    fun setStyleSpan():StyleSpan{
        return StyleSpan(Typeface.BOLD)
    }

}