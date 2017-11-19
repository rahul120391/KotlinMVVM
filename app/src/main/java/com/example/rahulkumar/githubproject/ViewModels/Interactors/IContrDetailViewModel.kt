package com.example.rahulkumar.githubproject.ViewModels.Interactors

/**
 * Created by rahulkumar on 14/11/17.
 */
interface IContrDetailViewModel {

    fun getImageUrl():String?
    fun setProgressViewVisibilty(boolean: Boolean)
    fun getProgressViewVisibility():Int
    fun executeUrlRequest()
}