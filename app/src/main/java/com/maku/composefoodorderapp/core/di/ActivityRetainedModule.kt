package com.maku.composefoodorderapp.core.di

import com.maku.composefoodorderapp.core.repo.FoodRepo
import com.maku.composefoodorderapp.core.repo.FoodRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.disposables.CompositeDisposable

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindQuestionsRepository(repository: FoodRepoImpl): FoodRepo

    companion object {
        @Provides
        fun provideCompositeDisposable() = CompositeDisposable()
    }
}
