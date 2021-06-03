package com.todsapon.flowaccountchalenges.presentation.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.todsapon.flowaccountchalenges.R
import com.todsapon.flowaccountchalenges.databinding.FragmentCategoriesBinding
import com.todsapon.flowaccountchalenges.extension.viewBinding
import com.todsapon.flowaccountchalenges.presentation.MainViewModel
import com.todsapon.flowaccountchalenges.presentation.categories.list.CategoriesAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding: FragmentCategoriesBinding by viewBinding(FragmentCategoriesBinding::bind)
    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val categoriesAdapter: CategoriesAdapter by inject {
        parametersOf(onItemClicked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeViewModel()

        categoriesViewModel.getCurrentCategory()
        categoriesViewModel.getTotalOfCategories()
    }

    private fun initView() {
        binding.categoriesRecyclerView.adapter = categoriesAdapter
    }

    private fun observeViewModel() {
        categoriesViewModel.currentCategories.observe(viewLifecycleOwner, Observer { categories ->
            categoriesAdapter.submitList(categories)
        })
        categoriesViewModel.totalOfCategories.observe(viewLifecycleOwner, Observer { totalOfCategories ->
            val totalText = getString(R.string.total_of_categories) + totalOfCategories.toString()
            binding.totalTextView.text = totalText
        })
        mainViewModel.onBack.observe(viewLifecycleOwner, Observer {
            categoriesViewModel.onBackClicked()
        })
    }

    private val onItemClicked: (Int) -> Unit = { index ->
        categoriesViewModel.getCurrentCategory(index)
    }
}