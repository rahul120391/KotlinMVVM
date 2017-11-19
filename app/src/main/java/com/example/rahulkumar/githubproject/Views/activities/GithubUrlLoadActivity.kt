package com.example.rahulkumar.githubproject.Views.activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rahulkumar.githubproject.Views.interactors.IGithubView
import com.example.rahulkumar.githubproject.R
import com.example.rahulkumar.githubproject.ViewModels.VM.GithubUrlViewModel
import com.example.rahulkumar.githubproject.databinding.ActivityGithibUrlLoadBinding
import kotlinx.android.synthetic.main.activity_repo_details.view.*

class GithubUrlLoadActivity : AppCompatActivity(), IGithubView {

    lateinit var binding:ActivityGithibUrlLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this@GithubUrlLoadActivity,R.layout.activity_githib_url_load)
        initViews(binding)

    }

    override fun initViews(binding: ActivityGithibUrlLoadBinding) {
        if(intent!=null){
            val url=intent.getStringExtra("url")
            if(url!=null){

                binding.github= GithubUrlViewModel(url)
            }

        }

        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.root.toolBar.setNavigationOnClickListener {
            onBackPressed()
        }


    }

    override fun onBackPressed() {
        if(binding.webView.canGoBack()){
            binding.webView.goBack()
        }
        else{
            super.onBackPressed()
        }


    }



}
