package com.todsapon.flowaccountchalenges.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.todsapon.flowaccountchalenges.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
    }

    override fun onBackPressed() {
        mainViewModel.onBackClicked()
    }

    private fun observeViewModel() {
        mainViewModel.onClose.observe(this, Observer {
            super.onBackPressed()
        })
    }
}