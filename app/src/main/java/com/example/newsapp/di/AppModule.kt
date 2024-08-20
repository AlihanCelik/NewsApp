package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.data.manger.LocalUserMangerImpl
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.domain.usercases.app_entry.AppEntryUsesCases
import com.example.newsapp.domain.usercases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usercases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ):LocalUserManger=LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryCases(
        localUserManger: LocalUserManger
    )= AppEntryUsesCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
}