package com.todsapon.flowaccountchalenges.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _onBack = MutableLiveData<Unit>()
    val onBack: LiveData<Unit>
        get() = _onBack

    private val _onClose = MutableLiveData<Unit>()
    val onClose: LiveData<Unit>
        get() = _onClose

    fun onBackClicked() {
        _onBack.value = Unit
    }

    fun onCloseApp() {
        _onClose.value = Unit
    }
}