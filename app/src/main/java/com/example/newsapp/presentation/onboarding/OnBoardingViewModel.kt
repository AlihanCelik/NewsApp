package com.example.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usercases.app_entry.AppEntryUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUsesCases: AppEntryUsesCases
):ViewModel(){
    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUsesCases.saveAppEntry()
        }

    }
}