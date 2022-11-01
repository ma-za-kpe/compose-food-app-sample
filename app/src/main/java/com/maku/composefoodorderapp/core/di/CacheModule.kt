package com.maku.composefoodorderapp.core.di

import android.content.Context
import androidx.room.Room
import com.maku.composefoodorderapp.core.cache.Cache
import com.maku.composefoodorderapp.core.cache.CacheImpl
import com.maku.composefoodorderapp.core.cache.FoodDatabase
import com.maku.composefoodorderapp.core.cache.dao.CartDao
import com.maku.composefoodorderapp.core.cache.dao.MenuDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

  @Binds
  abstract fun bindCache(cache: CacheImpl): Cache

  companion object {

      @Provides
      @Singleton
      fun provideDatabase(
          @ApplicationContext context: Context
      ): FoodDatabase {
          return Room.databaseBuilder(
              context,
              FoodDatabase::class.java,
              "food.db"
          ).build()
      }

      @Provides
      fun provideMenuDao(
          foodDatabase: FoodDatabase
      ): MenuDao = foodDatabase.menuDao()

      @Provides
      fun provideCartDao(
          foodDatabase: FoodDatabase
      ): CartDao = foodDatabase.cartDao()
  }
}
