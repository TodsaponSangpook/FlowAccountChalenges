package com.todsapon.flowaccountchalenges.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.todsapon.flowaccountchalenges.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        mainViewModel.onBlackClicked()
    }
}