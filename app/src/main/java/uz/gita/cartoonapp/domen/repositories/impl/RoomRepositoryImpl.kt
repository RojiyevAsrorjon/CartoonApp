package uz.gita.cartoonapp.domen.repositories.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.cartoonapp.data.local.daos.CartoonDao
import uz.gita.cartoonapp.data.remote.responses.ResultsItem
import uz.gita.cartoonapp.domen.repositories.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val dao : CartoonDao, private val config : PagingConfig) : RoomRepository {
    override fun getAllData(coroutineScope: CoroutineScope): Flow<PagingData<ResultsItem>>  = Pager(config){
        dao.getSavedData()
    }.flow.cachedIn(coroutineScope)

    override suspend fun insertData(list: List<ResultsItem>) {
        dao.insert(list)
    }
}