package com.example.rahulkumar.githubproject.Views.activities

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.rahulkumar.githubproject.Views.interactors.IMainView
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.R
import com.example.rahulkumar.githubproject.ViewModels.VM.ActivityViewModel
import com.example.rahulkumar.githubproject.Views.adapters.SearchListAdapter
import com.example.rahulkumar.githubproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), IMainView {


    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: ActivityViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SearchListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        initViews(binding)
        viewmodel = ActivityViewModel(this)
        binding.avm = viewmodel


    }


    override fun initViews(dataBinding: ActivityMainBinding) {
        setSupportActionBar(dataBinding.root.toolbar)
        binding.root.toolbar.contentInsetStartWithNavigation=0
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.root.toolbar.setNavigationOnClickListener {
            finish()
        }

        recyclerView = binding.root.recyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager!!
        adapter = SearchListAdapter()
        recyclerView.adapter = adapter
    }


    override fun onResult(list: List<Item>) {
        adapter.setList(list)
    }

}
