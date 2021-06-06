package com.todsapon.flowaccountchalenges.domain.usecase

import com.todsapon.flowaccountchalenges.domain.model.Category
import javax.inject.Inject

interface GetCategoriesUseCase {
    fun execute(): List<Category>
}

class GetCategoriesUseCaseImpl @Inject constructor() : GetCategoriesUseCase {

    override fun execute(): List<Category> {
        return listOf(Category().apply {
            this.id = 10
            this.name = "Entertainment"
            this.children = listOf(
                Category().apply {
                    this.id = 20
                    this.name = "Office Dinner"
                },
                Category().apply {
                    this.id = 21
                    this.name = "Client Meeting"
                },
                Category().apply {
                    this.id = 22
                    this.name = "Team activies"
                    this.children = listOf(
                        Category().apply {
                            this.id = 30
                            this.name = "Movies"
                        },
                        Category().apply {
                            this.id = 31
                            this.name = "After work party"
                        },
                    )
                }
            )
        })
    }
}
