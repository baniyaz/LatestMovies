package com.zainab.evaluation.presentation.latestmovies

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class InfiniteScrollListener constructor(private val infiniteLoader: InfiniteLoader): RecyclerView.OnScrollListener(){
    private var isLoading = true
    private val visibleThreshold = 5
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var startingPageIndex = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) { isLoading = true; }
        }

        if (isLoading && (totalItemCount > previousTotalItemCount)) {
            isLoading = false
            previousTotalItemCount = totalItemCount
            currentPage++
        }

        if (!isLoading && (lastVisibleItemPosition  + visibleThreshold) >= totalItemCount ) {
                isLoading = infiniteLoader.loadNextPage(currentPage + 1 )
        }
    }

    interface InfiniteLoader{
         fun loadNextPage(page:Int): Boolean
    }
}