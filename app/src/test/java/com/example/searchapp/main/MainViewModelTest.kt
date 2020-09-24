package com.example.searchapp.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.searchapp.MainRepository
import com.example.searchapp.MainViewModel
import com.example.searchapp.coroutines.CoroutineTestRule
import com.example.searchapp.helper.ApiResponse
import com.example.searchapp.model.AlbumList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()
    private lateinit var mainViewModel: MainViewModel
    @Mock
    lateinit var mainRepository: MainRepository
    @Mock
    lateinit var observer: Observer<ApiResponse<AlbumList>>

    private val albumsList = Mockito.mock(AlbumList::class.java)
    private val expectedSuccess = ApiResponse.Success(albumsList)
    private val expectedError = ApiResponse.Error(Exception())


    @Before
    fun init() {
        mainViewModel = MainViewModel(mainRepository, coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun albumListSuccess() = coroutinesTestRule.testDispatcher.runBlockingTest {
        Mockito.`when`(mainRepository.getAlbumList(ALBUM)).thenReturn(expectedSuccess)
        mainViewModel.getAlbumList(ALBUM).observeForever(observer)
        mainViewModel.getAlbumList(ALBUM)
        delay(300)
        Mockito.verify(mainRepository).getAlbumList(ALBUM)
        Mockito.verify(observer).onChanged(ApiResponse.LOADING)
        Mockito.verify(observer).onChanged(expectedSuccess)
        Mockito.verify(observer).onChanged(ApiResponse.COMPLETED)
    }

    @Test
    fun albumListFailure() = coroutinesTestRule.testDispatcher.runBlockingTest {
        Mockito.`when`(mainRepository.getAlbumList(ALBUM)).thenReturn(expectedError)
        mainViewModel.getAlbumList(ALBUM).observeForever(observer)
        mainViewModel.getAlbumList(ALBUM)
        delay(300)
        Mockito.verify(mainRepository).getAlbumList(ALBUM)
        Mockito.verify(observer).onChanged(ApiResponse.LOADING)
        Mockito.verify(observer).onChanged(expectedError)
        Mockito.verify(observer).onChanged(ApiResponse.COMPLETED)
    }

}