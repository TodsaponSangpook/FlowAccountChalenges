package com.todsapon.flowaccountchalenges.presentation.categories

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.todsapon.flowaccountchalenges.domain.model.Category
import com.todsapon.flowaccountchalenges.domain.usecase.GetCategoriesByParentUseCase
import com.todsapon.flowaccountchalenges.domain.usecase.GetTotalOfCategoriesUseCase
import com.todsapon.flowaccountchalenges.extension.InstantTaskExecutorExtension
import com.todsapon.flowaccountchalenges.extension.getOrAwaitValue
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class CategoriesViewModelTest {

    private lateinit var categoriesViewModel: CategoriesViewModel
    private val getCategoriesByParentUseCase: GetCategoriesByParentUseCase = mock()
    private val getTotalOfCategoriesUseCase: GetTotalOfCategoriesUseCase = mock()

    @BeforeEach
    fun setUp() {
        categoriesViewModel = CategoriesViewModel(
            getCategoriesByParentUseCase,
            getTotalOfCategoriesUseCase
        )
    }

    @Test
    fun getCurrentCategories_indexIsNull_notifyCurrentCategories() {
        val responseMockData = listOf<Category>(Category().apply {
            this.id = 1
            this.name = "name"
        })

        whenever(getCategoriesByParentUseCase.execute(any())).thenReturn(responseMockData)
        categoriesViewModel.getCurrentCategories()

        categoriesViewModel.currentCategories.getOrAwaitValue().also { categories ->
            assertEquals(categories.first().id, responseMockData.first().id)
            assertEquals(categories.first().name, responseMockData.first().name)
            assertNull(categories.first().children)
        }
    }

    @Test
    fun getCurrentCategories_indexIsNotNull_notifyCurrentCategories() {
        val responseMockData = listOf<Category>(Category().apply {
            this.id = 1
            this.name = "name"
        })

        whenever(getCategoriesByParentUseCase.execute(any())).thenReturn(responseMockData)
        categoriesViewModel.getCurrentCategories(1)

        categoriesViewModel.currentCategories.getOrAwaitValue().also { categories ->
            assertEquals(categories.first().id, responseMockData.first().id)
            assertEquals(categories.first().name, responseMockData.first().name)
            assertNull(categories.first().children)
        }
    }

    @Test
    fun getTotalOfCategories_totalOfCategoriesIsTen_notifyTotalOfCategories() {
        whenever(getTotalOfCategoriesUseCase.execute()).thenReturn(10)
        categoriesViewModel.getTotalOfCategories()

        assertEquals(categoriesViewModel.totalOfCategories.getOrAwaitValue(), 10)
    }

    @Test
    fun onBackClicked_parentIndexLessThanOrEqualZero_notifyOnParentSizeIsEmpty() {
        categoriesViewModel.clearParentIndex()
        categoriesViewModel.onBackClicked()
        assertEquals(categoriesViewModel.onParentSizeIsEmpty.getOrAwaitValue(), Unit)
    }

    @Test
    fun onBackClicked_parentIndexMoreThanZero_notifyCurrentCategories() {
        val responseMockData = listOf<Category>(Category().apply {
            this.id = 1
            this.name = "name"
        })
        categoriesViewModel.setParentIndex(listOf(0, 2))
        whenever(getCategoriesByParentUseCase.execute(any())).thenReturn(responseMockData)
        categoriesViewModel.onBackClicked()

        categoriesViewModel.currentCategories.getOrAwaitValue().also { categories ->
            assertEquals(categories.first().id, responseMockData.first().id)
            assertEquals(categories.first().name, responseMockData.first().name)
            assertNull(categories.first().children)
        }
    }
}