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
internal class GetCategoriesByParentUseCaseImplTest {

    private lateinit var getCategoriesByParentUseCase: GetCategoriesByParentUseCase
    private val getCategoriesUseCase: GetCategoriesUseCase = mock()

    @BeforeEach
    fun setUp() {
        getCategoriesByParentUseCase = GetCategoriesByParentUseCaseImpl(getCategoriesUseCase)
    }

    @Test
    fun execute_sizeOfParentIndexIsOne_returnChildrenLayerOne() {
        val parentIndexMockData = listOf<Int>(0)
        val responseMockData = getCategories()
        val expectData = responseMockData[parentIndexMockData.last()].children

        whenever(getCategoriesUseCase.execute()).thenReturn(responseMockData)
        val responseData = getCategoriesByParentUseCase.execute(parentIndexMockData)

        assertEquals(responseData.first().id, expectData?.first()?.id)
        assertEquals(responseData.first().name, expectData?.first()?.name)
        assertEquals(responseData.first().children?.size, expectData?.first()?.children?.size)
    }

    @Test
    fun execute_sizeOfParentIndexIsTwo_returnChildrenLayerTwo() {
        val parentIndexMockData = listOf<Int>(0, 2)
        val responseMockData = getCategories()
        val expectData = responseMockData[parentIndexMockData[0]].children?.get(parentIndexMockData[1])?.children

        whenever(getCategoriesUseCase.execute()).thenReturn(responseMockData)
        val responseData = getCategoriesByParentUseCase.execute(parentIndexMockData)

        assertEquals(responseData.first().id, expectData?.first()?.id)
        assertEquals(responseData.first().name, expectData?.first()?.name)
        assertEquals(responseData.first().children?.size, expectData?.first()?.children?.size)
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