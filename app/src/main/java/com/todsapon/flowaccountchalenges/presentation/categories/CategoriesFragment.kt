package com.todsapon.flowaccountchalenges.presentation.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.todsapon.flowaccountchalenges.R
import com.todsapon.flowaccountchalenges.databinding.FragmentCategoriesBinding
import com.todsapon.flowaccountchalenges.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding: FragmentCategoriesBinding by viewBinding(FragmentCategoriesBinding::bind)
    private val categoriesViewModel: CategoriesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        categoriesViewModel.getCurrentCategory()
    }

    private fun observeViewModel() {
        categoriesViewModel.currentCategories.observe(viewLifecycleOwner, Observer {
            it
        })
    }
}