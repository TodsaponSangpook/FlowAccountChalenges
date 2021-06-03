package com.todsapon.flowaccountchalenges.domain.usecase

import com.todsapon.flowaccountchalenges.domain.model.Category

interface GetTotalOfCategoriesUseCase {
    fun execute(): Int
}

class GetTotalOfCategoriesUseCaseImpl(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : GetTotalOfCategoriesUseCase {

    override fun execute(): Int {
        val categories = getCategoriesUseCase.execute()
        return calculateTotalOfCategory(categories)
    }

    private fun calculateTotalOfCategory(categories: List<Category>): Int {
        var total = categories.size
        categories.forEach { category ->
            category.children?.let { children ->
                total += calculateTotalOfCategory(children)
            }
        }

        return total
    }
}