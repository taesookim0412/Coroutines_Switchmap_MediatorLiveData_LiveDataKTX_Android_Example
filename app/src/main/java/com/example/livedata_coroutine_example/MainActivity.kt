package com.example.livedata_coroutine_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val testViewModel:TestViewModel = ViewModelProvider(this).get(TestViewModel::class.java)*/
        val testViewModel:TestViewModel by viewModels()
        Log.d("val", testViewModel.coroutineData.value.toString())
        testViewModel.subscribeData.observe(this, Observer { unit-> unit })
    }
}
