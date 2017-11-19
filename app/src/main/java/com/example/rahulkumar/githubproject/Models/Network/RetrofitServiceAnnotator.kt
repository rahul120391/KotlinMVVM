package com.example.rahulkumar.githubproject.Models.Network

import com.example.rahulkumar.githubproject.Models.Pojo.Contributor
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.Models.Pojo.SearchResult
import com.example.rahulkumar.mvvmarchitecture.Utils.Constants
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by rahulkumar on 09/11/17.
 */
interface RetrofitServiceAnnotator {

    @GET(Constants.FETCH_SEARCH_RESULTS_URL)
    fun getSearchResult(@Query("q") searchKey:String):Flowable<SearchResult>

    @GET
    fun getContributors(@Url url:String):Flowable<List<Contributor>>


    @GET
    fun getRepos(@Url url:String):Flowable<List<Item>>

}