package com.todsapon.flowaccountchalenges.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.todsapon.flowaccountchalenges.domain.model.Category
import com.todsapon.flowaccountchalenges.domain.usecase.GetCategoriesByParentUseCase
import com.todsapon.flowaccountchalenges.domain.usecase.GetTotalOfCategoriesUseCase

class CategoriesViewModel(
    private val getCategoriesByParentUseCase: GetCategoriesByParentUseCase,
    private val getTotalOfCategoriesUseCase: GetTotalOfCategoriesUseCase
) : ViewModel() {
    private val parentIndex = arrayListOf<Int>()

    private val _currentCategories = MutableLiveData<List<Category>>()
    val currentCategories: LiveData<List<Category>>
        get() = _currentCategories

    private val _totalOfCategories = MutableLiveData<Int>()
    val totalOfCategories: LiveData<Int>
        get() = _totalOfCategories

    fun getCurrentCategory(index: Int? = null) {
        index?.let {
            parentIndex.add(it)
        }

        _currentCategories.value = getCategoriesByParentUseCase.execute(parentIndex)
    }

    fun getTotalOfCategories() {
        _totalOfCategories.value = getTotalOfCategoriesUseCase.execute()
    }

    fun onBackClicked() {
        if (parentIndex.size > 0) {
            parentIndex.removeAt(parentIndex.lastIndex)
            _currentCategories.value = getCategoriesByParentUseCase.execute(parentIndex)
        }
    }
}