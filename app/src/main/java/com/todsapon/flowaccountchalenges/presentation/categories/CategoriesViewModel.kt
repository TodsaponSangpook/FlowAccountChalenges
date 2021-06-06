package com.todsapon.flowaccountchalenges.presentation.categories

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.todsapon.flowaccountchalenges.domain.model.Category
import com.todsapon.flowaccountchalenges.domain.usecase.GetCategoriesByParentUseCase
import com.todsapon.flowaccountchalenges.domain.usecase.GetTotalOfCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
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

    private val _onParentSizeIsEmpty = MutableLiveData<Unit>()
    val onParentSizeIsEmpty: LiveData<Unit>
        get() = _onParentSizeIsEmpty

    fun getCurrentCategories(index: Int? = null) {
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
        } else {
            _onParentSizeIsEmpty.value = Unit
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setParentIndex(parentIndexList: List<Int>) {
        parentIndex.clear()
        parentIndex.addAll(parentIndexList)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun clearParentIndex() {
        parentIndex.clear()
    }
}