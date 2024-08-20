package com.example.newsapp.domain.usercases.app_entry

import com.example.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }
}