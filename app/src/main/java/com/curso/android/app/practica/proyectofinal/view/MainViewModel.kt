package com.curso.android.app.practica.proyectofinal.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.curso.android.app.practica.proyectofinal.model.Comparar


class MainViewModel : ViewModel() {
    val comparar: LiveData<Comparar> get() = _comparar
    private var _comparar = MutableLiveData<Comparar>(Comparar("", ""))

    fun actualizarTextos(text1 : String, text2 : String){
        _comparar.value = Comparar(text1.trim(), text2.trim())
    }

    fun compararTextos() : Boolean{
        return _comparar.value?.text1.equals(_comparar.value?.text2)
    }

    fun sonTextosNoNulos() : Boolean{
        return !(_comparar.value?.text1.isNullOrEmpty() &&  _comparar.value?.text2.isNullOrEmpty() )
    }


}