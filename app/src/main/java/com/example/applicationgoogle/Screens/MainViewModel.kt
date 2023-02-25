package com.example.applicationgoogle.Screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val isLoading = MutableLiveData(false)

    //Evita que la variable sea modificada
    fun isLoading() : LiveData<Boolean> = isLoading

    //Permite cambiar el valor de la variable con el metodo post
    fun LoginWithGoogle(){
        isLoading.postValue(true)

        //Para retrasar usamos el "delay"

        viewModelScope.launch {
            delay(5000)
            isLoading.postValue(false)
        }
    }
}