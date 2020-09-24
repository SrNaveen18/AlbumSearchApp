package com.example.searchapp.main

import com.example.searchapp.MainRepository
import com.example.searchapp.helper.ApiResponse
import com.example.searchapp.model.AlbumList
import com.example.searchapp.webservice.ApiStories
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

const val METHOD = "album.search"
const val API_KEY = "88dbdc8a175eb0840bf122dbcb8981cf"
const val FORMAT = "json"
const val ALBUM = "Believe"

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {
    @Mock
    lateinit var apiStories: ApiStories
    private lateinit var mainRepository: MainRepository

    private val albumList = Mockito.mock(AlbumList::class.java)
    private var exception = Mockito.mock(Exception::class.java)
    private val expectedSuccess = ApiResponse.Success(albumList)
    private val expectedError = ApiResponse.Error(exception)

    @Before
    fun init() {
        mainRepository = MainRepository(apiStories)
    }

    @Test
    fun `Success Response`() = runBlockingTest {
        Mockito.`when`(apiStories.getAlbumList(METHOD, API_KEY, ALBUM, FORMAT))
            .thenReturn(albumList)
        mainRepository.getAlbumList(ALBUM)
        Mockito.verify(apiStories).getAlbumList(METHOD, API_KEY, ALBUM, FORMAT)
        Assert.assertEquals(mainRepository.getAlbumList(ALBUM), expectedSuccess)
    }

    @Test
    fun `Failure Response`() = runBlockingTest {
        Mockito.`when`(apiStories.getAlbumList(METHOD, API_KEY, ALBUM, FORMAT))
            .then { throw exception }
        mainRepository.getAlbumList(ALBUM)
        Mockito.verify(apiStories).getAlbumList(METHOD, API_KEY, ALBUM, FORMAT)
        Assert.assertEquals(mainRepository.getAlbumList(ALBUM), expectedError)
    }
}