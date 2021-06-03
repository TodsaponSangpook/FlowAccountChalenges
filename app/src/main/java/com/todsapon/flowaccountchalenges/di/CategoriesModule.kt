package com.todsapon.flowaccountchalenges.di

import com.todsapon.flowaccountchalenges.domain.usecase.GetCategoriesUseCase
import com.todsapon.flowaccountchalenges.domain.usecase.GetCategoriesUseCaseImpl
import com.todsapon.flowaccountchalenges.presentation.categories.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule = module {

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl()
    }

    viewModel {
        CategoriesViewModel(
            getCategoriesUseCase = get()
        )
    }
}