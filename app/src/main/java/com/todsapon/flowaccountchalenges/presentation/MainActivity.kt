package com.todsapon.flowaccountchalenges.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.todsapon.flowaccountchalenges.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
    }

    override fun onBackPressed() {
        mainViewModel.onBackClicked()
    }

    private fun observeViewModel() {
        mainViewModel.onClose.observe(this) {
            super.onBackPressed()
        }
    }
}