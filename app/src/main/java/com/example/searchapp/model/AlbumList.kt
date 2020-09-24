package com.example.searchapp.model


import com.google.gson.annotations.SerializedName

data class AlbumList(@SerializedName("results")
                     val results: Results)


data class Results(@SerializedName("opensearch:Query")
                   val opensearchQuery: OpensearchQuery,
                   @SerializedName("opensearch:itemsPerPage")
                   val opensearchItemsPerPage: String = "",
                   @SerializedName("albummatches")
                   val albummatches: Albummatches,
                   @SerializedName("opensearch:startIndex")
                   val opensearchStartIndex: String = "",
                   @SerializedName("opensearch:totalResults")
                   val opensearchTotalResults: String = "")


data class AlbumItem(@SerializedName("image")
                     val image: List<ImageItem>? = listOf(),
                     @SerializedName("mbid")
                     val mbid: String = "",
                     @SerializedName("artist")
                     val artist: String = "",
                     @SerializedName("streamable")
                     val streamable: String = "",
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("url")
                     val url: String = "",
                     var expand : Boolean =false)


data class ImageItem(@SerializedName("#text")
                     val Text: String = "",
                     @SerializedName("size")
                     val size: String = "")


data class OpensearchQuery(@SerializedName("startPage")
                           val startPage: String = "",
                           @SerializedName("#text")
                           val Text: String = "",
                           @SerializedName("role")
                           val role: String = "",
                           @SerializedName("searchTerms")
                           val searchTerms: String = "")


data class Albummatches(@SerializedName("album")
                        val album: List<AlbumItem>?)

