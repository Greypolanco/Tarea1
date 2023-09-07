package com.example.firstaplicacion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firstaplicacion.data.local.entity.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user:Usuario)

    @Query("SELECT * FROM Usuarios")
    fun getAll(): Flow<List<Usuario>>

}