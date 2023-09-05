package com.example.firstaplicacion.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")

data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val nombre : String
)
