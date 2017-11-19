package com.example.rahulkumar.githubproject.ViewModels.Interactors

import android.content.Context
import android.view.View

/**
 * Created by rahulkumar on 11/11/17.
 */
interface IRepoViewModel {

    fun getImageUrl(): String?
    fun getName():String ?
    fun getProjectLink():String ?
    fun getDescription():String ?
    fun fetchContributorList()
    fun getRecyclerViewVisibility():Int
    fun getProgressViewVisibility():Int
    fun setRecyclerViewVisibilty(boolean: Boolean)
    fun setProgressViewVisibilty(boolean: Boolean)
    fun moveToLink():View.OnClickListener
}