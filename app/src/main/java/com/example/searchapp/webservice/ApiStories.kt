package com.example.searchapp.webservice

import com.example.searchapp.model.AlbumList
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://ws.audioscrobbler.com/"
const val METHOD = "album.search"
const val API_KEY = "88dbdc8a175eb0840bf122dbcb8981cf"
const val FORMAT = "json"

interface ApiStories {
    @GET("2.0/")
    suspend fun getAlbumList(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("album") album: String,
        @Query("format") format: String
    ): AlbumList
}