package com.todsapon.flowaccountchalenges.domain.usecase

import com.todsapon.flowaccountchalenges.domain.model.Category
import com.todsapon.flowaccountchalenges.extension.InstantTaskExecutorExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class GetCategoriesUseCaseImplTest {

    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @BeforeEach
    fun setUp() {
        getCategoriesUseCase = GetCategoriesUseCaseImpl()
    }

    @Test
    fun execute_success_returnCorrectData() {
        val expectData = getCategories()
        val responseData = getCategoriesUseCase.execute()

        //first layer
        assertEquals(responseData[0].id, expectData[0].id)
        assertEquals(responseData[0].name, expectData[0].name)
        assertEquals(responseData[0].children?.size, expectData[0].children?.size)

        //second layer
        assertEquals(responseData[0].children?.get(0)?.id, expectData[0].children?.get(0)?.id)
        assertEquals(responseData[0].children?.get(0)?.name, expectData[0].children?.get(0)?.name)
        assertNull(responseData[0].children?.get(0)?.children)

        //third layer
        assertEquals(responseData[0].children?.get(2)?.id, expectData[0].children?.get(2)?.id)
        assertEquals(responseData[0].children?.get(2)?.name, expectData[0].children?.get(2)?.name)
        assertEquals(responseData[0].children?.get(2)?.children?.size, expectData[0].children?.get(2)?.children?.size)

        //fourth layer
        assertEquals(responseData[0].children?.get(2)?.children?.get(0)?.id, expectData[0].children?.get(2)?.children?.get(0)?.id)
        assertEquals(responseData[0].children?.get(2)?.children?.get(0)?.name, expectData[0].children?.get(2)?.children?.get(0)?.name)
        assertNull(responseData[0].children?.get(2)?.children?.get(0)?.children)
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