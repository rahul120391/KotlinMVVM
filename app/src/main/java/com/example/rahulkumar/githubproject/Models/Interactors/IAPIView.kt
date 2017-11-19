package com.example.rahulkumar.githubproject.Models.Interactors

/**
 * Created by rahulkumar on 09/11/17.
 */
interface IAPIView {

    fun executeSearchApi(searchKey:String)
    fun executeContributorApi(url:String)
    fun executeReposApi(url: String)
}