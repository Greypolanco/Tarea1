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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {
    var nombre by mutableStateOf("")
    var telefono by mutableStateOf("")
    var celular by mutableStateOf("")
    var email by mutableStateOf("")
    var direccion by mutableStateOf("")
    var fecha by mutableStateOf("")
    var ocupacion by mutableStateOf("")
    val ocupacionlista = listOf("Ingeniero", "Abogado", "Profesor", "Contable")

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()

    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    val usuarios: StateFlow<List<Usuario>> = repository.dataBase.userdao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun save() {
        viewModelScope.launch {
            repository.save(user = Usuario(
                nombre = nombre,
                telefono = telefono,
                celular = celular,
                email = email,
                direccion = direccion,
                fecha = fecha,
                ocupacion = ocupacion))
        }
        setMessageShown()
        limpiar()
    }


    private fun limpiar() {
        nombre = ""
        telefono = ""
        celular = ""
        email = ""
        direccion = ""
        fecha = ""
        ocupacion =""
    }
}