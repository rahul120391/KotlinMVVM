package com.example.rahulkumar.githubproject.ViewModels.Interactors

import android.webkit.WebViewClient

/**
 * Created by rahulkumar on 13/11/17.
 */
interface IGithubViewModel{

    fun getClient(): WebViewClient
    fun getWebUrl():String?
    fun setProgressViewVisibilty(boolean: Boolean)
    fun getProgressViewVisibility():Int
}