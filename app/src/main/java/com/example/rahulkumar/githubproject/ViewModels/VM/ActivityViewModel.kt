package com.example.rahulkumar.githubproject.ViewModels.VM

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.graphics.Color
import android.support.v7.widget.SearchView
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.example.rahulkumar.githubproject.ViewModels.Interactors.IActivityModelView
import com.example.rahulkumar.githubproject.Views.interactors.IMainView
import com.example.rahulkumar.githubproject.Models.Interactors.IModelToViewModelDataTransfer
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.Models.Pojo.SearchResult
import com.example.rahulkumar.githubproject.Models.Network.WebServiceController
import com.example.rahulkumar.mvvmarchitecture.Utils.Utility
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by rahulkumar on 08/11/17.
 */
class ActivityViewModel(mainView: IMainView) : BaseObservable(), IActivityModelView, IModelToViewModelDataTransfer {


    var recyclerViewShown = false
    var progressBarShown = true
    var errorTextShow = false
    val viewInteractor = mainView
    val webserviceController by lazy { WebServiceController(this) }

    var error: String = "No Data Found"
    override fun fetchSearchResult(searchKey: String) {
        if (Utility.checkNetworkConnectivity()) {
            setRecyclerViewVisibilty(false)
            setProgressViewVisibilty(true)
            setErrorTextVisibilty(false)
            notifyPropertyChanged(BR._all)
            webserviceController.executeSearchApi(searchKey)
        }

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

    @Bindable
    override fun getErrorTextVisibilty(): Int {
        if (errorTextShow == false) {
            return View.GONE
        }
        return View.VISIBLE
    }

    override fun setErrorTextVisibilty(boolean: Boolean) {
        errorTextShow = boolean
    }


    override fun Success(result: Any) {
        if (result != null) {
            val searchresult = result as SearchResult
            val dataAvailable = searchresult?.items != null && !searchresult.items?.isEmpty()!!
            if (dataAvailable) {
                Flowable.just(searchresult.items)
                        .subscribeOn(Schedulers.io())
                        .map { list ->
                            list.sortedByDescending { item: Item ->
                                item.watchers
                            }

                        }.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ sortedList ->
                            setRecyclerViewVisibilty(true)
                            setProgressViewVisibilty(false)
                            notifyPropertyChanged(BR._all)
                            var sortedList = sortedList.take(10)

                            viewInteractor.onResult(sortedList)

                        }, { error ->
                            Failure(error)
                        }, {

                        })
            } else {
                setRecyclerViewVisibilty(false)
                setProgressViewVisibilty(false)
                setErrorTextVisibilty(true)
                notifyPropertyChanged(BR._all)
            }
        } else {
            setRecyclerViewVisibilty(false)
            setProgressViewVisibilty(false)
            setErrorTextVisibilty(true)
            notifyPropertyChanged(BR._all)
        }


    }

    @Bindable
    fun getApiError(): String {
        return error
    }

    fun setApiError(error: String) {
        this.error = error
    }


    override fun Failure(e: Throwable) {
        setApiError(e.localizedMessage)
        setRecyclerViewVisibilty(false)
        setProgressViewVisibilty(false)
        setErrorTextVisibilty(true)
        notifyPropertyChanged(BR._all)
    }


    override fun getQueryTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.trim().isNotEmpty()) {
                    fetchSearchResult(query)
                } else {
                    setRecyclerViewVisibilty(false)
                    setProgressViewVisibilty(false)
                    setErrorTextVisibilty(false)
                    notifyPropertyChanged(BR._all)
                }
                return false
            }
        }
    }

    override fun setBackgroundColor(): Int {
        return Color.GRAY
    }


}