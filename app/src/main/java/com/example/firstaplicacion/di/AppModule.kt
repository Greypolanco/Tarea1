package com.example.firstaplicacion.di

import android.content.Context
import androidx.room.Room
import com.example.firstaplicacion.data.local.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun createdatabase(@ApplicationContext context: Context) : DataBase{
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            "Tarea1db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}