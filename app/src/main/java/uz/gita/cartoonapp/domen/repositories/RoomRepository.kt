package uz.gita.cartoonapp.domen.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.cartoonapp.data.remote.responses.ResultsItem

interface RoomRepository {
    fun getAllData(coroutineScope: CoroutineScope) : Flow<PagingData<ResultsItem>>
    suspend fun insertData(list : List<ResultsItem>)
}