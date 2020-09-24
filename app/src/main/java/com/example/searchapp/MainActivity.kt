package com.example.searchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.searchapp.adapter.AlbumAdapter
import com.example.searchapp.databinding.ActivityMainBinding
import com.example.searchapp.extension.isNetConnected
import com.example.searchapp.helper.ApiResponse
import kotlinx.android.synthetic.main.activity_main.*

import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()
    private var albumAdapter : AlbumAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initAdapter()

        if (checkInternetAvailable()){
            mainViewModel.observerTextChange().observe(this,  {
                when (it) {
                    is ApiResponse.Success -> {
                        albumAdapter?.setAlbumList(it.response.results.albummatches.album)
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(applicationContext,it.exception.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                    ApiResponse.LOADING -> {
                        progressbar.visibility = View.VISIBLE
                    }
                    ApiResponse.COMPLETED -> {
                        progressbar.visibility = View.GONE
                    }
                }
            })
        }
    }

    private fun initDataBinding() {
        val mViewDataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewDataBinding.setVariable(BR.mainVM, mainViewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
    }

    private fun initAdapter(){
        albumAdapter = AlbumAdapter()
        recyclerView.adapter = albumAdapter
    }

    private fun checkInternetAvailable(): Boolean {
        val hasInternet = isNetConnected()
        return if (!hasInternet) {
            showMessageAlert(getString(R.string.error_no_internet))
            false
        } else {
            true
        }
    }

    private fun showMessageAlert(message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(message)
        dialog.setPositiveButton("Close") { dialog, _ ->
            finish()
            dialog?.dismiss()
        }
        dialog.create().show()
    }

}
