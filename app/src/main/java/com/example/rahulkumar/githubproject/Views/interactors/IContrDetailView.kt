package com.example.rahulkumar.githubproject.Views.interactors

import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.databinding.ActivityContributorDetailsBinding

/**
 * Created by rahulkumar on 14/11/17.
 */
interface IContrDetailView {

    fun initViews(binding:ActivityContributorDetailsBinding)
    fun setList(list:List<Item>)
}