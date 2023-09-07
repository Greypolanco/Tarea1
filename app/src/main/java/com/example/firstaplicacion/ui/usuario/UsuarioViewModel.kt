package com.example.firstaplicacion.ui.usuario

import android.media.CamcorderProfile.getAll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstaplicacion.data.domain.UsuarioRepository
import com.example.firstaplicacion.data.local.entity.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val repository: UsuarioRepository
):ViewModel(){
    var nombre by mutableStateOf("")

    val usuarios: StateFlow<List<Usuario>> = repository.dataBase.userdao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
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