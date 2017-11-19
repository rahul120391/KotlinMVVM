package com.example.rahulkumar.githubproject.Models.Interactors

import android.view.View

/**
 * Created by rahulkumar on 12/11/17.
 */
interface IContributorView {

    fun getName():String?
    fun getImageUrl():String?
    fun onItemClickListener(view: View)
}