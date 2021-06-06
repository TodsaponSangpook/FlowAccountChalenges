package com.todsapon.flowaccountchalenges.presentation.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.todsapon.flowaccountchalenges.R
import com.todsapon.flowaccountchalenges.databinding.FragmentCategoriesBinding
import com.todsapon.flowaccountchalenges.extension.viewBinding
import com.todsapon.flowaccountchalenges.presentation.MainViewModel
import com.todsapon.flowaccountchalenges.presentation.categories.list.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val binding: FragmentCategoriesBinding by viewBinding(FragmentCategoriesBinding::bind)
    private val categoriesViewModel: CategoriesViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    @Inject lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeViewModel()

        categoriesViewModel.getCurrentCategories()
        categoriesViewModel.getTotalOfCategories()
    }

    private fun initView() {
        categoriesAdapter.setItemClick(onItemClicked)
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

        binding.categoriesRecyclerView.addItemDecoration(dividerItemDecoration)
        binding.categoriesRecyclerView.adapter = categoriesAdapter
    }

    private fun observeViewModel() {
        categoriesViewModel.currentCategories.observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.submitList(categories)
        }

        categoriesViewModel.totalOfCategories.observe(viewLifecycleOwner) { totalOfCategories ->
            val totalText = getString(R.string.total_of_categories, totalOfCategories.toString())
            binding.totalTextView.text = totalText
        }

        categoriesViewModel.onParentSizeIsEmpty.observe(viewLifecycleOwner) {
            mainViewModel.onCloseApp()
        }

        mainViewModel.onBack.observe(viewLifecycleOwner) {
            categoriesViewModel.onBackClicked()
        }
    }

    private val onItemClicked: (Int) -> Unit = { index ->
        categoriesViewModel.getCurrentCategories(index)
    }
}