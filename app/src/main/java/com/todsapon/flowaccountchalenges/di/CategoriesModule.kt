package com.todsapon.flowaccountchalenges.di

import com.todsapon.flowaccountchalenges.domain.usecase.*
import com.todsapon.flowaccountchalenges.presentation.MainViewModel
import com.todsapon.flowaccountchalenges.presentation.categories.CategoriesViewModel
import com.todsapon.flowaccountchalenges.presentation.categories.list.CategoriesAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule = module {

    factory<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl()
    }

    factory<GetCategoriesByParentUseCase> {
        GetCategoriesByParentUseCaseImpl(
            getCategoriesUseCase = get()
        )
    }

    factory<GetTotalOfCategoriesUseCase> {
        GetTotalOfCategoriesUseCaseImpl(
            getCategoriesUseCase = get()
        )
    }

    factory<CategoriesAdapter> { (onItemClicked: (index: Int) -> Unit) ->
        CategoriesAdapter(onItemClicked)
    }

    viewModel {
        CategoriesViewModel(
            getCategoriesByParentUseCase = get(),
            getTotalOfCategoriesUseCase = get()
        )
    }

    viewModel {
        MainViewModel()
    }
}