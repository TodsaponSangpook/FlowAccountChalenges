package com.todsapon.flowaccountchalenges.presentation

import com.todsapon.flowaccountchalenges.extension.InstantTaskExecutorExtension
import com.todsapon.flowaccountchalenges.extension.getOrAwaitValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @BeforeEach
    fun setUp() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun onBlackClicked_notifyOnBack() {
        mainViewModel.onBackClicked()
        assertEquals(mainViewModel.onBack.getOrAwaitValue(), Unit)
    }

    @Test
    fun onCloseApp_notifyOnOnClose() {
        mainViewModel.onCloseApp()
        assertEquals(mainViewModel.onClose.getOrAwaitValue(), Unit)
    }
}