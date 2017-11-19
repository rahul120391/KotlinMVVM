package com.example.rahulkumar.githubproject.ViewModels.VM

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.android.databinding.library.baseAdapters.BR
import com.example.rahulkumar.githubproject.Views.interactors.IContrDetailView
import com.example.rahulkumar.githubproject.ViewModels.Interactors.IContrDetailViewModel
import com.example.rahulkumar.githubproject.Models.Interactors.IModelToViewModelDataTransfer
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.Models.Network.WebServiceController
import com.example.rahulkumar.mvvmarchitecture.Utils.Utility
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by rahulkumar on 14/11/17.
 */
class ContributorDetailViewModel(bundle: Bundle, iContrDetailView: IContrDetailView) : BaseObservable(), IContrDetailViewModel, IModelToViewModelDataTransfer {


    val bundle = bundle;
    var progressBarShown = true
    val viewInteractor = iContrDetailView
    val webserviceController by lazy { WebServiceController(this) }
    override fun getImageUrl(): String? {
        return bundle.getString("imageUrl")
    }


    @Bindable
    override fun getProgressViewVisibility(): Int {
        if (progressBarShown == false) {
            return View.GONE
        }
        return View.VISIBLE
    }

    override fun setProgressViewVisibilty(boolean: Boolean) {
        progressBarShown = boolean
    }

    override fun executeUrlRequest() {
        if (Utility.checkNetworkConnectivity()) {
            webserviceController.executeReposApi(bundle.getString("repoUrl"))
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(viewImage: ImageView, url: String?) {
            Utility.loadImageFromUrl(viewImage, url)
        }

    }


    override fun Success(result: Any) {
        if (result != null) {
            val searchresult = result as List<Item>
            val dataAvailable = searchresult != null && !searchresult.isEmpty()
            println("size issss" + searchresult.size)
            if (dataAvailable) {
                Flowable.just(searchresult)
                        .subscribeOn(Schedulers.io())
                        .map { list ->
                            list.sortedBy { item: Item ->
                                item.watchers
                            }

                        }.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ sortedList ->
                            setProgressViewVisibilty(false)
                            notifyPropertyChanged(BR._all)
                            viewInteractor.setList(sortedList)

                        }, { error ->
                            setProgressViewVisibilty(false)
                            notifyPropertyChanged(BR._all)
                        }, {

                        })
            } else {
                setProgressViewVisibilty(false)
                notifyPropertyChanged(BR._all)
            }
        } else {
            setProgressViewVisibilty(false)
            notifyPropertyChanged(BR._all)
        }


    }

    override fun Failure(e: Throwable) {


    }


}