package uz.gita.cartoonapp.presentation.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.cartoonapp.data.remote.responses.ResultsItem
import uz.gita.cartoonapp.domen.repositories.ContentRepository
import uz.gita.cartoonapp.domen.repositories.RoomRepository
import uz.gita.cartoonapp.presentation.viewModel.MainScreenViewModel
import uz.gita.cartoonapp.utils.eventFlow
import uz.gita.cartoonapp.utils.eventValueFlow
import uz.gita.cartoonapp.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(private val content: ContentRepository,private val room: RoomRepository) : ViewModel(), MainScreenViewModel {
    override val contentPagerLiveData = eventValueFlow<PagingData<ResultsItem>>()
    override val pagingAAdapterFlow = eventFlow()
    init {
        if (!isConnected()) {
            pagingAAdapterFlow.tryEmit(Unit)
            room.getAllData(viewModelScope).onEach {
                delay(200)
                contentPagerLiveData.emit(it)
            }.launchIn(viewModelScope)
        } else {
            pagingAAdapterFlow.tryEmit(Unit)
            content.getCharacters(viewModelScope).onEach {
                delay(200)
                contentPagerLiveData.emit(it)

            }.launchIn(viewModelScope)
        }
    }

    override fun getContents() {
        if (!isConnected()) {
            room.getAllData(viewModelScope).onEach {
                delay(200)
                contentPagerLiveData.emit(it)
            }.launchIn(viewModelScope)
        } else {
            content.getCharacters(viewModelScope).onEach {
                delay(200)
                contentPagerLiveData.emit(it)
            }.launchIn(viewModelScope)
        }
    }

}