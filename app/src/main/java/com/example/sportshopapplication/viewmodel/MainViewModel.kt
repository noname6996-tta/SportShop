package com.example.sportshopapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportshopapplication.model.User
import com.example.sportshopapplication.model.local.Cart
import com.example.sportshopapplication.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repostitory: Repository): ViewModel() {

    val userInfoLogin = MutableLiveData<List<User>>()
//    val DownloadHome = MutableLiveData<List<Music>>()
//    val RankingHome = MutableLiveData<List<Music>>()
//    val TopDownload = MutableLiveData<List<Music>>()
//    val GenresList = MutableLiveData<List<Genre>>()
//    val MusicByGenres = MutableLiveData<List<Music>>()
//    val searchByString = MutableLiveData<List<Music>>()
//
    fun getUserInfo(){
        viewModelScope.launch {
            val response = repostitory.getUserInfo()
            userInfoLogin.value = listOf(response)
        }
    }

//    fun getDownloadHome(){
//        viewModelScope.launch {
//            val response = repostitory.getDownloadHome()
//            DownloadHome.value = response.data
//        }
//    }
//
//    fun getRanking(){
//        viewModelScope.launch {
//            val response = repostitory.getRanking()
//            RankingHome.value = response.data
//        }
//    }
//
//    fun getTopDownload(){
//        viewModelScope.launch {
//            val response = repostitory.getTopDownload()
//            TopDownload.value = response.data
//        }
//    }
//
//    fun getGenres(){
//        viewModelScope.launch {
//            val response = repostitory.getGenres()
//            GenresList.value = response.data
//        }
//    }
//
//    fun getMusicByGenres(query : String){
//        viewModelScope.launch {
//            val response = repostitory.getMusicByGenres(query)
//            MusicByGenres.value = response.data
//        }
//    }
//
//    fun searchByString(query : String){
//        viewModelScope.launch {
//            val response = repostitory.searchByString(query)
//            searchByString.value = response.data
//        }
//    }
}