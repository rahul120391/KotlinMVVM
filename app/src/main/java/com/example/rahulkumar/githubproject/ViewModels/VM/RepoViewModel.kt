package com.example.rahulkumar.githubproject.ViewModels.VM

import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.android.databinding.library.baseAdapters.BR
import com.example.rahulkumar.githubproject.Models.Interactors.IModelToViewModelDataTransfer
import com.example.rahulkumar.githubproject.Views.interactors.IRepoView
import com.example.rahulkumar.githubproject.ViewModels.Interactors.IRepoViewModel
import com.example.rahulkumar.githubproject.Models.Pojo.Contributor
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.Models.Network.WebServiceController
import com.example.rahulkumar.githubproject.Views.activities.GithubUrlLoadActivity
import com.example.rahulkumar.mvvmarchitecture.Utils.Utility
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by rahulkumar on 11/11/17.
 */
class RepoViewModel(item: Item, iRepoView: IRepoView) : BaseObservable(), IRepoViewModel, IModelToViewModelDataTransfer {


    val model = item
    val viewInteractor = iRepoView
    var recyclerViewShown = false
    var progressBarShown = true
    val webserviceController by lazy { WebServiceController(this) }
    override fun getName(): String? {
        return model.name
    }

    override fun getProjectLink(): String? {
        return model.htmlUrl
    }

    override fun getDescription(): String? {
        return model.description
    }


    override fun getImageUrl(): String? {
        return model.owner?.avatarUrl!!
    }

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, url: String?) {
            Utility.loadImageFromUrl(view, url)
        }
    }

    override fun fetchContributorList() {
        if (Utility.checkNetworkConnectivity()) {
            val url = model.contributorsUrl!!
            webserviceController.executeContributorApi(url)
        }

    }

    override fun Success(result: Any) {
        if(result!=null){
            val result = result as List<Contributor>
            if(result!=null && result.size>0){
                Flowable.just(result)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    list->
                                    viewInteractor.updateRecyclerView(list)
                                    setRecyclerViewVisibilty(true)
                                    setProgressViewVisibilty(false)
                                    notifyPropertyChanged(BR._all)
                                },
                                {
                                    error->
                                    Failure(error)
                                    setRecyclerViewVisibilty(false)
                                    setProgressViewVisibilty(false)
                                    notifyPropertyChanged(BR._all)
                                },
                                {

                                }

                        )
            }

        }

    }

    override fun Failure(e: Throwable) {

    }

    @Bindable
    override fun getRecyclerViewVisibility(): Int {
        if (recyclerViewShown == false) {
            return View.GONE
        }
        return View.VISIBLE
    }

    @Bindable
    override fun getProgressViewVisibility(): Int {
        if (progressBarShown == false) {
            return View.GONE
        }
        return View.VISIBLE
    }

    override fun setRecyclerViewVisibilty(recyclerViewShown: Boolean) {
        this.recyclerViewShown = recyclerViewShown
    }

    override fun setProgressViewVisibilty(boolean: Boolean) {
        progressBarShown = boolean
    }

    override fun moveToLink():View.OnClickListener{
        return View.OnClickListener {
            view->
            val urLink=Intent(view.context, GithubUrlLoadActivity::class.java)
            urLink.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            urLink.putExtra("url", model.htmlUrl)
            view.context.startActivity(urLink)

        }
    }

}