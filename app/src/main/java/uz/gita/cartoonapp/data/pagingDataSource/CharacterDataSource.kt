package uz.gita.cartoonapp.data.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uz.gita.cartoonapp.data.remote.apies.CartoonApi
import uz.gita.cartoonapp.data.remote.responses.ResultsItem
import uz.gita.cartoonapp.domen.repositories.RoomRepository
import java.io.IOException
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val api : CartoonApi,
    private val repository: RoomRepository
) : PagingSource<Int, ResultsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {

        try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val response = api.getCharacterContents(page)
            return if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { repository.insertData(it) }
                val nextKey = if (body?.results?.size!! < pageSize) null else page + 1
                val prefKey = if (page == 1) null else page - 1
                LoadResult.Page(body.results, prefKey, nextKey)
            } else {
                var res = "There is no connection with the server!"
                LoadResult.Error(Throwable(res))
            }
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}