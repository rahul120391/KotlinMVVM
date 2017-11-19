package com.example.rahulkumar.githubproject.ViewModels.Interactors

import android.support.v7.widget.SearchView

/**
 * Created by rahulkumar on 08/11/17.
 */
interface IActivityModelView {

    fun fetchSearchResult(searchKey:String)
    fun getRecyclerViewVisibility():Int
    fun getProgressViewVisibility():Int
    fun setRecyclerViewVisibilty(boolean: Boolean)
    fun setProgressViewVisibilty(boolean: Boolean)
    fun getErrorTextVisibilty():Int
    fun setErrorTextVisibilty(boolean: Boolean)
    fun getQueryTextListener(): SearchView.OnQueryTextListener
    fun setBackgroundColor():Int

}