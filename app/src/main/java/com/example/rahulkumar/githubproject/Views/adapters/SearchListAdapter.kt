package com.example.rahulkumar.githubproject.Views.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rahulkumar.githubproject.Models.Pojo.Item
import com.example.rahulkumar.githubproject.R
import com.example.rahulkumar.githubproject.ViewModels.VM.SearchResultViewModel
import com.example.rahulkumar.githubproject.databinding.RowItemBinding

/**
 * Created by rahulkumar on 10/11/17.
 */
class SearchListAdapter(): RecyclerView.Adapter<SearchListAdapter.MyViewHolder>() {

    val searchList by lazy { ArrayList<Item>() }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        binding.adapt= SearchResultViewModel(searchList.get(position))
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RowItemBinding>(LayoutInflater.from(parent.context), R.layout.row_item, parent, false)
        return MyViewHolder(binding)
    }


    class MyViewHolder(val binding:RowItemBinding):RecyclerView.ViewHolder(binding.searchCard)

    fun setList(itemList:List<Item>){
        searchList.clear()
        searchList.addAll(itemList)
        notifyDataSetChanged()
    }


}