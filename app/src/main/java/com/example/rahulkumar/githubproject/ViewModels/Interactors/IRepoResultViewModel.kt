package com.example.rahulkumar.githubproject.ViewModels.Interactors

import android.view.View

/**
 * Created by rahulkumar on 14/11/17.
 */
interface IRepoResultViewModel {

    fun getName():String?
    fun getFullName():String?
    fun getWatchersCount():String?
    fun getCommitCount():String?
}