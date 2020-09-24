package com.example.searchapp

import androidx.lifecycle.*
import com.example.searchapp.helper.ApiResponse
import com.example.searchapp.helper.DefaultDispatcherProvider
import com.example.searchapp.helper.DispatcherProvider
import com.example.searchapp.model.AlbumList
import kotlinx.coroutines.delay

class MainViewModel(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) :
    ViewModel() {

    var albumName = MutableLiveData<String>()
    private var searchFor = ""

    fun getAlbumList(searchText: String): LiveData<ApiResponse<AlbumList>> =
        liveData(dispatchers.io()) {
            if (searchText.isNotBlank()){
                if (searchText != searchFor) {
                    searchFor = searchText
                    delay(300) // Debounce time
                    if (searchText == searchFor) {
                        emit(ApiResponse.LOADING)
                        emit(repository.getAlbumList(albumName = searchText))
                    }
                }
            }
            emit(ApiResponse.COMPLETED)
        }

    fun observerTextChange() = Transformations.switchMap(albumName) { searchText ->
        getAlbumList(searchText)
    }

}