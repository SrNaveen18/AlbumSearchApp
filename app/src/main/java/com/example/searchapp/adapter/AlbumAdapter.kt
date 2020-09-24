package com.example.searchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp.R
import com.example.searchapp.model.AlbumItem

class AlbumAdapter() : RecyclerView.Adapter<AlbumViewHolder>() {
    private var albumList: List<AlbumItem>?= null

    fun setAlbumList(albumList: List<AlbumItem>?){
        this.albumList = albumList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_album, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.onBind(albumList?.get(position))
    }

    override fun getItemCount(): Int = albumList?.size ?: 0

}