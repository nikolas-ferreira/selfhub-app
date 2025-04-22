package digital.studioweb.selfhub_app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.studioweb.selfhub_app.data.datasource.remote.HomeDataSource
import digital.studioweb.selfhub_app.data.datasource.remote.HomeDataSourceImpl
import digital.studioweb.selfhub_app.data.repositories.HomeRepository
import digital.studioweb.selfhub_app.data.repositories.HomeRepositoryImpl
import digital.studioweb.selfhub_app.data.usecases.FilterProductsByCategoryUseCase
import digital.studioweb.selfhub_app.data.usecases.GetMenuCategoriesUseCase
import digital.studioweb.selfhub_app.data.usecases.GetProductsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHomeDataSource(): HomeDataSource {
        return HomeDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeDataSource: HomeDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(homeDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMenuCategoriesUseCase(
        homeRepository: HomeRepository
    ): GetMenuCategoriesUseCase {
        return GetMenuCategoriesUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        homeRepository: HomeRepository
    ): GetProductsUseCase {
        return GetProductsUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideFilterProductsByCategoryUseCase(): FilterProductsByCategoryUseCase {
        return FilterProductsByCategoryUseCase()
    }
}
