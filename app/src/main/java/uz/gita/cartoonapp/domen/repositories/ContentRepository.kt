package uz.gita.cartoonapp.domen.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.cartoonapp.data.remote.responses.ResultsItem

interface ContentRepository {
    fun getCharacters(coroutineScope : CoroutineScope) : Flow<PagingData<ResultsItem>>
}