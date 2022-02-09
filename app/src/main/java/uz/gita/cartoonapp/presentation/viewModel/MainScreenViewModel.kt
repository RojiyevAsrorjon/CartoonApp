package uz.gita.cartoonapp.presentation.viewModel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.cartoonapp.data.remote.responses.ResultsItem

interface MainScreenViewModel {
    val contentPagerLiveData : Flow<PagingData<ResultsItem>>
    val pagingAAdapterFlow : Flow<Unit>

    fun getContents()
}