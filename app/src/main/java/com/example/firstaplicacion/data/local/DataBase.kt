package com.example.firstaplicacion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firstaplicacion.data.local.dao.UsuarioDao
import com.example.firstaplicacion.data.local.entity.Usuario

@Database(
    entities = [Usuario::class],
    exportSchema = false,
    version = 2
)
abstract class DataBase: RoomDatabase()  {
    abstract val userdao: UsuarioDao
}