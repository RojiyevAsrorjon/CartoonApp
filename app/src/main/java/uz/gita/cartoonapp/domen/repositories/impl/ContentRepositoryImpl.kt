package uz.gita.cartoonapp.domen.repositories.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.cartoonapp.data.pagingDataSource.CharacterDataSource
import uz.gita.cartoonapp.data.remote.apies.CartoonApi
import uz.gita.cartoonapp.data.remote.responses.ResultsItem
import uz.gita.cartoonapp.domen.repositories.ContentRepository
import uz.gita.cartoonapp.domen.repositories.RoomRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val api : CartoonApi, private val config: PagingConfig, private val roomRepository: RoomRepository) : ContentRepository{
    override fun getCharacters(coroutineScope: CoroutineScope): Flow<PagingData<ResultsItem>> = Pager(config){
        CharacterDataSource(api,roomRepository)
    }.flow.cachedIn(coroutineScope)
}