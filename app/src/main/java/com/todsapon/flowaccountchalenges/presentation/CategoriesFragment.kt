package com.todsapon.flowaccountchalenges.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.todsapon.flowaccountchalenges.R
import com.todsapon.flowaccountchalenges.databinding.FragmentCategoriesBinding
import com.todsapon.flowaccountchalenges.extension.viewBinding

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding: FragmentCategoriesBinding by viewBinding(FragmentCategoriesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}