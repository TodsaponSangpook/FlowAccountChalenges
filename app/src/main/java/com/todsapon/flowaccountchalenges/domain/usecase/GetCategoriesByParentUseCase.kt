package com.todsapon.flowaccountchalenges.domain.usecase

import com.todsapon.flowaccountchalenges.domain.model.Category
import javax.inject.Inject

interface GetCategoriesByParentUseCase {
    fun execute(parentIndex: List<Int>): List<Category>
}

class GetCategoriesByParentUseCaseImpl @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : GetCategoriesByParentUseCase {

    override fun execute(parentIndex: List<Int>): List<Category> {
        var newCurrentCategories = getCategoriesUseCase.execute()

        parentIndex.forEach { index ->
            newCurrentCategories.getOrNull(index)?.children?.let { category ->
                newCurrentCategories = category
            }
        }

        return newCurrentCategories
    }
}