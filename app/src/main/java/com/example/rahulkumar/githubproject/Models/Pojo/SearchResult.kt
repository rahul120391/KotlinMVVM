package com.example.rahulkumar.githubproject.Models.Pojo

import com.squareup.moshi.Json
import java.io.Serializable

class SearchResult : Serializable{

    @Json(name = "total_count")
    var totalCount: Int? = null
    @Json(name = "incomplete_results")
    var incompleteResults: Boolean? = null
    @Json(name = "items")
    var items: List<Item>? = null

}