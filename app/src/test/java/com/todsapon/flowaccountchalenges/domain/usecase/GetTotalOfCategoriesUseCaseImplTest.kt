package com.todsapon.flowaccountchalenges.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.todsapon.flowaccountchalenges.domain.model.Category
import com.todsapon.flowaccountchalenges.extension.InstantTaskExecutorExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class GetTotalOfCategoriesUseCaseImplTest {

    private lateinit var getTotalOfCategoriesUseCase: GetTotalOfCategoriesUseCase
    private val getCategoriesUseCase: GetCategoriesUseCase = mock()

    @BeforeEach
    fun setUp() {
        getTotalOfCategoriesUseCase = GetTotalOfCategoriesUseCaseImpl(getCategoriesUseCase)
    }

    @Test
    fun execute_totalOfCategoriesIsZero_returnCorrectData() {
        val responseMockData = listOf<Category>()

        whenever(getCategoriesUseCase.execute()).thenReturn(responseMockData)
        val responseData = getTotalOfCategoriesUseCase.execute()

        assertEquals(responseData, 0)
    }

    @Test
    fun execute_totalOfCategoriesIsOne_returnCorrectData() {
        val responseMockData = listOf(Category())

        whenever(getCategoriesUseCase.execute()).thenReturn(responseMockData)
        val responseData = getTotalOfCategoriesUseCase.execute()

        assertEquals(responseData, 1)
    }

    @Test
    fun execute_totalOfCategoriesIsSix_returnCorrectData() {
        val responseMockData = getCategories()

        whenever(getCategoriesUseCase.execute()).thenReturn(responseMockData)
        val responseData = getTotalOfCategoriesUseCase.execute()

        assertEquals(responseData, 6)
    }

    private fun getCategories(): List<Category> {
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