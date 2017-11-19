package com.example.rahulkumar.githubproject.ViewModels.VM

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.view.View
import android.webkit.*
import com.example.rahulkumar.githubproject.BR
import com.example.rahulkumar.githubproject.ViewModels.Interactors.IGithubViewModel

/**
 * Created by rahulkumar on 13/11/17.
 */
class GithubUrlViewModel(url: String) : BaseObservable(), IGithubViewModel {


    val newUrl = url
    var progressBarShown = true



    inner class Client : WebViewClient() {


        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            println("page finished")
            this@GithubUrlViewModel.setProgressViewVisibilty(false)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            println("page started")
        }

    }


    override fun getClient(): WebViewClient {
        println("client set")
        return Client()
    }

    override fun getWebUrl(): String? {
        println("new url"+newUrl)
        return newUrl!!
    }

    companion object {

        @JvmStatic
        @BindingAdapter("load","setClient")
        fun setWebView(webView: WebView, url: String?,webViewClient: WebViewClient?) {
            webView.settings.javaScriptEnabled = true
            webView.webViewClient=webViewClient
            webView.loadUrl(url)

        }

    }

    override fun setProgressViewVisibilty(boolean: Boolean) {
        progressBarShown = boolean
        notifyPropertyChanged(BR.progressViewVisibility)
    }

    @Bindable
    override fun getProgressViewVisibility(): Int {
        if (progressBarShown == false) {
            return View.GONE
        }
        return View.VISIBLE
    }


}