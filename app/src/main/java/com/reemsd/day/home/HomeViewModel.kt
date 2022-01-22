package com.reemsd.day.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reemsd.day.network.RealTimeData
import com.reemsd.day.network.TopPlaces
import kotlinx.coroutines.launch

// enum class
enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel:ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

 private val _listPlaces = MutableLiveData<List<TopPlaces?>>()
     val listPlaces : LiveData<List<TopPlaces?>> = _listPlaces

    init {

    }
     fun getTopTenList (){
       viewModelScope.launch {
           _status.value = ApiStatus.LOADING
          try {
              var list = mutableListOf<TopPlaces>()
              RealTimeData.retrofitServices.getTopten().forEach {
                  list.add(it.value)
              }
              _listPlaces.value=list
              _status.value=ApiStatus.DONE
          }catch (e :Exception){
              _status.value = ApiStatus.ERROR
              _listPlaces.value = mutableListOf()

          }



       }
     }

}