package com.example.rahulkumar.githubproject.Views.interactors

import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.databinding.ActivityMainBinding

/**
 * Created by rahulkumar on 08/11/17.
 */
interface IMainView {

    fun initViews(binding:ActivityMainBinding)
    fun onResult(list:List<Item>)
}