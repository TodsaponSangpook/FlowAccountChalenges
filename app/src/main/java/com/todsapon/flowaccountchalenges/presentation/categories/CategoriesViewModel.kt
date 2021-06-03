package com.todsapon.flowaccountchalenges.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.todsapon.flowaccountchalenges.domain.model.Category
import com.todsapon.flowaccountchalenges.domain.usecase.GetCategoriesByParentUseCase

class CategoriesViewModel(
    private val getCategoriesByParentUseCase: GetCategoriesByParentUseCase
) : ViewModel() {

    private val _currentCategories = MutableLiveData<List<Category>>()
    val currentCategories: LiveData<List<Category>>
        get() = _currentCategories

    private val parentIndex = arrayListOf<Int>()

    fun getCurrentCategory(index: Int? = null) {
        index?.let {
            parentIndex.add(it)
        }

        _currentCategories.value = getCategoriesByParentUseCase.execute(parentIndex)
    }

    fun onBackClicked() {
        if (parentIndex.size > 0) {
            parentIndex.removeAt(parentIndex.lastIndex)
            _currentCategories.value = getCategoriesByParentUseCase.execute(parentIndex)
        }
    }
}