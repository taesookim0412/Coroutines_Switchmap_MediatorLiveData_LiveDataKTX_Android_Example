package com.example.livedata_coroutine_example

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SwitchmapViewModel: ViewModel() {
    var first = MutableLiveData<Long>()
    var second = MutableLiveData<Int>()

    var prefs = MediatorLiveData<Pair<Long, Int>>()

    init {
        first.value = 10L
        second.value = 8
        addSources()
    }

    fun addSources() {
        prefs.addSource(first) { data ->
            prefs.value = Pair(data, second.value!!)
        }
        prefs.addSource(second) { data ->
            prefs.value = Pair(first.value!!, data)
        }
    }

    fun backgroundLiveData() = liveData<Boolean> {
        withContext(Dispatchers.IO) {
            launch { delay(10000) }
            launch { delay(8000) }
        }
        emit(true)
    }

    var switchMap = Transformations.switchMap(backgroundLiveData()) { data ->
        prefs
    }
}
