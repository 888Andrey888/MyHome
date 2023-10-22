package com.example.myhome.presintation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhome.R
import com.example.myhome.core.network.RemoteDataSource
import com.example.myhome.core.network.RetrofitClient
import com.example.myhome.domain.repository.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private val retrofitClient = RetrofitClient().createApiService()
        private val remoteDataSource = RemoteDataSource(retrofitClient)
        internal val repository = Repository(remoteDataSource)
    }
}