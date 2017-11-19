package com.example.rahulkumar.githubproject.ViewModels.Interactors

import android.content.Context
import android.view.View

/**
 * Created by rahulkumar on 10/11/17.
 */
interface ISearchViewModel {

    fun getName():String?
    fun getFullName():String?
    fun getWatchersCount():String?
    fun getCommitCount():String?
    fun getImageUrl():String?
    fun getItemClick(view: View)
}