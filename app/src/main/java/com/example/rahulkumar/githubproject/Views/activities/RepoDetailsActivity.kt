package com.example.rahulkumar.githubproject.Views.activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.rahulkumar.githubproject.Views.interactors.IRepoView
import com.example.rahulkumar.githubproject.Models.Pojo.Contributor
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.R
import com.example.rahulkumar.githubproject.ViewModels.VM.RepoViewModel
import com.example.rahulkumar.githubproject.Views.adapters.ContributorListAdapter
import com.example.rahulkumar.githubproject.databinding.ActivityRepoDetailsBinding
import kotlinx.android.synthetic.main.activity_repo_details.view.*

class RepoDetailsActivity : AppCompatActivity(), IRepoView {



    lateinit var binding: ActivityRepoDetailsBinding
    lateinit var adapter: ContributorListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@RepoDetailsActivity, R.layout.activity_repo_details)
        initViews(binding)
    }


    override fun initViews(binding: ActivityRepoDetailsBinding) {

        if(intent!=null){
            val data:Item=intent.getSerializableExtra("data") as Item
            val repoviewmodel= RepoViewModel(data, this)
            binding.repo= repoviewmodel
            repoviewmodel.fetchContributorList()
        }
        val layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvContributors.layoutManager=GridLayoutManager(this,3)
        adapter= ContributorListAdapter()
        binding.rvContributors.adapter=adapter
        setSupportActionBar(binding.root.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.root.toolBar.setNavigationOnClickListener {
            finish()
        }

    }

    override fun updateRecyclerView(list: List<Contributor>) {
        println("sizeee"+list.size)
        adapter.setList(list)
    }

}
