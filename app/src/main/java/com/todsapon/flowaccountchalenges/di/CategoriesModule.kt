package com.todsapon.flowaccountchalenges.di

import com.todsapon.flowaccountchalenges.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
abstract class CategoriesModule {

    @Binds
    abstract fun bindGetCategoriesUseCase(getCategoriesUseCaseImpl: GetCategoriesUseCaseImpl): GetCategoriesUseCase

    @Binds
    abstract fun bindGetCategoriesByParentUseCase(getCategoriesByParentUseCaseImpl: GetCategoriesByParentUseCaseImpl): GetCategoriesByParentUseCase

    @Binds
    abstract fun bindGetTotalOfCategoriesUseCase(getTotalOfCategoriesUseCaseImpl: GetTotalOfCategoriesUseCaseImpl): GetTotalOfCategoriesUseCase
}