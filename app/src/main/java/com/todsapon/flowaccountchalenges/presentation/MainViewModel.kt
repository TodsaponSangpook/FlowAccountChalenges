package com.todsapon.flowaccountchalenges.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _onBack = MutableLiveData<Unit>()
    val onBack: LiveData<Unit>
        get() = _onBack

    fun onBlackClicked() {
        _onBack.value = Unit
    }
}