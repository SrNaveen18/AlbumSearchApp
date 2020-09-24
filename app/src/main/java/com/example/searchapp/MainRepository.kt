package com.example.searchapp

import com.example.searchapp.helper.ApiResponse
import com.example.searchapp.model.AlbumList
import com.example.searchapp.webservice.API_KEY
import com.example.searchapp.webservice.ApiStories
import com.example.searchapp.webservice.FORMAT
import com.example.searchapp.webservice.METHOD

class MainRepository(private val apiStories: ApiStories) {

    suspend fun getAlbumList(albumName : String): ApiResponse<AlbumList> {
        return try {
            val callApi = apiStories.getAlbumList(METHOD, API_KEY,albumName,FORMAT)
            ApiResponse.Success(callApi)
        }catch (e : Exception){
            ApiResponse.Error(e)
        }
    }
}