package com.example.livedata_coroutine_example

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestViewModel(application: Application): AndroidViewModel(application) {

    var coroutineData: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>()
    var subscribeData: LiveData<Unit>
    init{
        coroutineData.value = false
        viewModelScope.launch(Dispatchers.IO){
            Log.d("Pending", "Pending")
            delay(5000)
            Log.d("Log:", "5000")
            coroutineData.postValue(true)
        }

        subscribeData = Transformations.map(coroutineData) {
                value -> Log.d("D", "This went off")
            if (value == true) test() }

    }
    fun test(){
        Log.d("test()" ,"Transformation map success")
    }
}
