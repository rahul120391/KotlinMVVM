package com.example.rahulkumar.githubproject.ViewModels.VM

import android.databinding.BaseObservable
import com.example.rahulkumar.githubproject.ViewModels.Interactors.IRepoResultViewModel
import com.example.rahulkumar.githubproject.Models.Pojo.Item

/**
 * Created by rahulkumar on 14/11/17.
 */
class RepoResultViewModel(item: Item) : BaseObservable(), IRepoResultViewModel {


    val model = item


    override fun getName(): String? {
        return StringBuilder().append(" ").append(model.name).toString()
    }

    override fun getFullName(): String? {
        return StringBuilder().append(" ").append(model.fullName).toString()
    }

    override fun getWatchersCount(): String? {
        return StringBuilder().append(" ").append(model.watchers).toString()
    }

    override fun getCommitCount(): String? {
        return StringBuilder().append(" ").append(model.forksCount).toString()
    }


}