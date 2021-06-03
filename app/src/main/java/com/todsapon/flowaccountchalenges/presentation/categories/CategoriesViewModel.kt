package com.todsapon.flowaccountchalenges.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.todsapon.flowaccountchalenges.domain.model.Category
import com.todsapon.flowaccountchalenges.domain.usecase.GetCategoriesUseCase

class CategoriesViewModel(
    val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _currentCategories = MutableLiveData<List<Category>>()
    val currentCategories: LiveData<List<Category>>
        get() = _currentCategories

    fun getCurrentCategory() {
        _currentCategories.value = getCategoriesUseCase.execute()
    }
}