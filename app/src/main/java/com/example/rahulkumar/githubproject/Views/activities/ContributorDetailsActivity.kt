package com.example.rahulkumar.githubproject.Views.activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.rahulkumar.githubproject.Views.interactors.IContrDetailView
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.R
import com.example.rahulkumar.githubproject.ViewModels.VM.ContributorDetailViewModel
import com.example.rahulkumar.githubproject.Views.adapters.RepoAdapter
import com.example.rahulkumar.githubproject.databinding.ActivityContributorDetailsBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_repo_details.view.*

class ContributorDetailsActivity : AppCompatActivity(), IContrDetailView {


    lateinit var binding: ActivityContributorDetailsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@ContributorDetailsActivity, R.layout.activity_contributor_details)
        initViews(binding)
    }


    override fun initViews(binding: ActivityContributorDetailsBinding) {
        if (intent != null) {
            val bundle = intent.getBundleExtra("bundle")
            val model= ContributorDetailViewModel(bundle, this)
            binding.contrDetail =model
            model.executeUrlRequest()
        }
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.root.toolBar.setNavigationOnClickListener {
            onBackPressed()
        }

        recyclerView = binding.root.recyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager!!
        adapter = RepoAdapter()
        recyclerView.adapter = adapter

    }

    override fun setList(list: List<Item>) {
        adapter.setList(list)
    }


}
