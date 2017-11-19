package com.example.rahulkumar.githubproject.ViewModels.VM

import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.rahulkumar.githubproject.Models.Interactors.IContributorView
import com.example.rahulkumar.githubproject.Models.Pojo.Contributor
import com.example.rahulkumar.githubproject.Views.activities.ContributorDetailsActivity
import com.example.rahulkumar.mvvmarchitecture.Utils.Utility

/**
 * Created by rahulkumar on 12/11/17.
 */
class ContributorListViewModel(contributor: Contributor) : BaseObservable(), IContributorView {


    val contr = contributor
    override fun getName(): String? {
        return contr.login
    }

    override fun getImageUrl(): String? {
        return contr.avatarUrl
    }

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, url: String) {
            Utility.loadImageFromUrl(view, url)
        }
    }

    override fun onItemClickListener(view: View) {
         val bundle=Bundle()
         val url=contr.avatarUrl
         val reposUrl=contr.reposUrl
         bundle.putString("imageUrl",url)
         bundle.putString("repoUrl",reposUrl)
         val intent=Intent(view.context, ContributorDetailsActivity::class.java)
         intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
         intent.putExtra("bundle",bundle)
         view.context.startActivity(intent)
    }


}