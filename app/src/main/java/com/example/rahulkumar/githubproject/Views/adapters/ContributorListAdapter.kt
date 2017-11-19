package com.example.rahulkumar.githubproject.Views.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rahulkumar.githubproject.Models.Pojo.Contributor
import com.example.rahulkumar.githubproject.R
import com.example.rahulkumar.githubproject.ViewModels.VM.ContributorListViewModel
import com.example.rahulkumar.githubproject.databinding.RowItemContributorListBinding

/**
 * Created by rahulkumar on 12/11/17.
 */
class ContributorListAdapter:RecyclerView.Adapter<ContributorListAdapter.MyViewHolder>(){

    val list by lazy { ArrayList<Contributor>() }
    lateinit var binding:RowItemContributorListBinding
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding=DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_item_contributor_list,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding=holder.binding
        binding.contr= ContributorListViewModel(list.get(position))
    }


    class MyViewHolder(val binding:RowItemContributorListBinding):RecyclerView.ViewHolder(binding.root)

    fun setList(listt: List<Contributor>){
        list.clear()
        list.addAll(listt)
        notifyDataSetChanged()
    }
}