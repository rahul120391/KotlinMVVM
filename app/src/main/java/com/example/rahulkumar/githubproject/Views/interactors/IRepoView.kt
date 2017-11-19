package com.example.rahulkumar.githubproject.Views.interactors

import com.example.rahulkumar.githubproject.Models.Pojo.Contributor
import com.example.rahulkumar.githubproject.databinding.ActivityRepoDetailsBinding

/**
 * Created by rahulkumar on 11/11/17.
 */
interface IRepoView {

    fun initViews(binding: ActivityRepoDetailsBinding)
    fun updateRecyclerView(list: List<Contributor>)
}