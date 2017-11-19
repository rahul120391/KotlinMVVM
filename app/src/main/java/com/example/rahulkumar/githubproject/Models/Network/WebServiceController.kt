package com.example.rahulkumar.githubproject.Models.Network

import com.example.rahulkumar.githubproject.Models.Interactors.IAPIView
import com.example.rahulkumar.githubproject.Models.Interactors.IModelToViewModelDataTransfer
import com.example.rahulkumar.githubproject.Models.Pojo.Contributor
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.Models.Pojo.SearchResult
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by rahulkumar on 09/11/17.
 */
class WebServiceController(modelToViewModelDataTransfer: IModelToViewModelDataTransfer) : IAPIView {


    val transferResult = modelToViewModelDataTransfer
    var disposeConnection: Disposable? = null
    lateinit var retrofitService: RetrofitServiceAnnotator

    init {
        retrofitService = RetrofitAdapter.create()
    }

    override fun executeSearchApi(searchKey: String) {

        val search: Flowable<SearchResult> = retrofitService.getSearchResult(searchKey).cache()
        if (disposeConnection != null) {
            disposeConnection?.dispose()
        }
        disposeConnection = search.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({

                    searchresult ->
                    transferResult.Success(searchresult)
                },
                        { error ->
                            transferResult.Failure(error)
                            disposeConnection?.dispose()
                        },
                        {
                            disposeConnection?.dispose()
                        })


    }

    override fun executeContributorApi(url: String) {
        val contr: Flowable<List<Contributor>> = retrofitService.getContributors(url).cache()
        disposeConnection = contr.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        { list ->
                            transferResult.Success(list)
                        },
                        { error ->
                            println("error message" + error.localizedMessage)
                            transferResult.Failure(error)
                            disposeConnection?.dispose()
                        },
                        {
                            disposeConnection?.dispose()
                        }
                )

    }

    override fun executeReposApi(url: String) {
        var repo: Flowable<List<Item>> = retrofitService.getRepos(url)
        disposeConnection = repo.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        { list ->
                            transferResult.Success(list)

                        },
                        { error ->
                            transferResult.Failure(error)
                            disposeConnection?.dispose()
                        },
                        {
                            disposeConnection?.dispose()
                        }

                )
    }
}