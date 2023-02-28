package com.assignment.neosoftassignment.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.roomdataBase.MovieDao

import kotlinx.coroutines.delay

class MainPagingSource(private val dao: MovieDao, private  val search: String,private  val b: Boolean) : PagingSource<Int, MovieResponseItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseItem> {
        val page = params.key ?: 0

        return try {
            Log.d("MainPagingSource", "load: $page")
            val entities = if (b){
                dao.getSearchPagedList(params.loadSize, page * params.loadSize,search)

            }else{
                dao.getPagedList(params.loadSize, page * params.loadSize)

            }
          /*  val entities = dao.getPagedList(params.loadSize, page * params.loadSize)

            val entitie = dao.getSearchPagedList(params.loadSize, page * params.loadSize,"yojiop")*/
            Log.d("MainPagingSource", "load:List sera $entities")


            if (page != 0) delay(1000)
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
