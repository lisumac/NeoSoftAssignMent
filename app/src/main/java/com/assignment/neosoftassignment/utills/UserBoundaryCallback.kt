package com.assignment.neosoftassignment.utills

import androidx.paging.PagedList
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.roomdataBase.MovieDao
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ContributionBoundaryCallback{
/*constructor(val userDao: MovieDao?) :
    PagedList.BoundaryCallback<MovieResponseItem?>() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    *//**
     * It is triggered when the list has no items User's Contributions are then fetched from the
     * network
     *//*
    override fun onZeroItemsLoaded() {
        fetchUsers()
    }

    *//**
     * It is triggered when the user scrolls to the top of the list User's Contributions are then
     * fetched from the network
     * *//*
    override fun onItemAtFrontLoaded(itemAtFront: MovieResponseItem) {
        fetchUsers()
    }

    *//**
     * It is triggered when the user scrolls to the end of the list. User's Contributions are then
     * fetched from the network
     *//*
    override fun onItemAtEndLoaded(itemAtEnd: MovieResponseItem) {
        fetchUsers()
    }

    *//**
     * Fetches contributions using the MediaWiki API
     *//*
    fun fetchUsers() {
        compositeDisposable.add(
            mockGetUsers()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    ::saveUsersToDb
                ) { error: Throwable ->
                    // do nothing
                }
        )
    }

    fun mockGetUsers(): Observable<MutableList<MovieResponseItem>> {
        val users = mutableListOf<MovieResponseItem>()
        for (i in 0..9) {
            users.add(i, getUser())
        }
        return Observable.just(users)
    }

   *//* fun getUser(): MovieResponseItem {
      //  return MovieResponseItem(0, getRandomString(5), getRandomString(8))
    }*//*

    fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    *//**
     * Saves the users to the DB
     *//*
    private fun saveUsersToDb(contributions: List<MovieResponseItem>) {
        compositeDisposable.add(
            userDao!!.save(contributions)
                .subscribeOn(Schedulers.io())
                .subscribe { longs: List<Long?>? ->
                    //do nothing
                }
        )
    }*/
}