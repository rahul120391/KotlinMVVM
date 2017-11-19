package com.example.rahulkumar.githubproject.ViewModels.VM

import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.example.rahulkumar.githubproject.ViewModels.Interactors.ISearchViewModel
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.Views.activities.RepoDetailsActivity
import com.example.rahulkumar.mvvmarchitecture.Utils.Utility

/**
 * Created by rahulkumar on 09/11/17.
 */
class SearchResultViewModel(item: Item) : BaseObservable(), ISearchViewModel {


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


    override fun getImageUrl(): String? {
        println("image url"+model.owner?.avatarUrl!!)
        return model.owner?.avatarUrl!!
    }

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, url: String?) {
            Utility.loadImageFromUrl(view, url)
        }
    }

    override fun getItemClick(view: View) {
        val intent = Intent(view.context, RepoDetailsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("data", model)
        view.context.startActivity(intent)
    }

}