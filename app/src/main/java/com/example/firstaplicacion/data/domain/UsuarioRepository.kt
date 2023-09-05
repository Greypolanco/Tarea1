package com.example.firstaplicacion.data.domain

import com.example.firstaplicacion.data.local.DataBase
import com.example.firstaplicacion.data.local.entity.Usuario
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val dataBase: DataBase
) {
    suspend fun save(user: Usuario)=
        dataBase.userdao.save(user)
}