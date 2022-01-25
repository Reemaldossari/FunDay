package com.reemsd.day.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.reemsd.day.network.PlanData
import com.reemsd.day.network.RealTimeData
import com.reemsd.day.network.TopPlaces
import kotlinx.coroutines.launch

// enum class
enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel:ViewModel() {
    val auth = FirebaseAuth.getInstance().currentUser
    var userId = auth?.uid.toString()
    private val _status = MutableLiveData<ApiStatus>(ApiStatus.DONE)
    val status: LiveData<ApiStatus> = _status

 private val _listPlaces = MutableLiveData<List<TopPlaces?>>()
     val listPlaces : LiveData<List<TopPlaces?>> = _listPlaces

    private val _listUserPlan = MutableLiveData<List<PlanData?>>()
    val listUserPlan : LiveData<List<PlanData?>> = _listUserPlan

    init {
        getTopTenList()
        getUserPlan(userId)
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
              Log.e("list","${_listPlaces}")
              _status.value=ApiStatus.DONE
          }catch (e :Exception){
              _status.value = ApiStatus.ERROR
              _listPlaces.value = mutableListOf()

          }

       }
     }
fun getUserPlan(userId :String =auth?.uid.toString() ){
    viewModelScope.launch {
        _status.value = ApiStatus.LOADING
        try {
            var list = mutableListOf<PlanData>()
            RealTimeData.retrofitServices.userPlan().forEach {
                list.add(it.value)
            }
            _listUserPlan.value = list.filter { it.userid == userId }
            _status.value=ApiStatus.DONE
        }catch (e :Exception){
            _status.value = ApiStatus.ERROR
            _listUserPlan.value = mutableListOf()
        }
    }
    }
}
