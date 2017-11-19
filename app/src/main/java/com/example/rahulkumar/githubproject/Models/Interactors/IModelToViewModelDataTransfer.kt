package com.example.rahulkumar.githubproject.Models.Interactors

/**
 * Created by rahulkumar on 09/11/17.
 */
interface IModelToViewModelDataTransfer {

      fun Success(result:Any)
      fun Failure(e:Throwable)
}