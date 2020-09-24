package com.example.searchapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp.databinding.AdapterAlbumBinding
import com.example.searchapp.model.AlbumItem

class AlbumViewHolder(private val adapterAlbumBinding: AdapterAlbumBinding) :
    RecyclerView.ViewHolder(adapterAlbumBinding.root) {
    fun onBind(albumItem: AlbumItem?) {
        adapterAlbumBinding.albumItem = albumItem
        adapterAlbumBinding.txtAlbumName.setOnClickListener {
            albumItem?.let {
                albumItem.expand = !albumItem.expand
                adapterAlbumBinding.albumItem = albumItem
            }
        }
    }
}