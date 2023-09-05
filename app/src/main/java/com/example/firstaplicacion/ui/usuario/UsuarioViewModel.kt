package com.example.firstaplicacion.ui.usuario

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstaplicacion.data.domain.UsuarioRepository
import com.example.firstaplicacion.data.local.entity.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val repository: UsuarioRepository
):ViewModel(){
    var nombre by mutableStateOf("")
    fun save (){
        viewModelScope.launch {
            repository.save(user = Usuario(nombre = nombre))
        }
        limpiar()
    }

    private fun limpiar(){
        nombre = ""
    }
}