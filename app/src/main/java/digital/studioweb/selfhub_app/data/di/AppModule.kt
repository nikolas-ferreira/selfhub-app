package digital.studioweb.selfhub_app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.studioweb.selfhub_app.data.repositories.auth.AuthRepository
import digital.studioweb.selfhub_app.data.repositories.auth.AuthRepositoryImpl
import digital.studioweb.selfhub_app.data.repositories.home.HomeRepository
import digital.studioweb.selfhub_app.data.repositories.home.HomeRepositoryImpl
import digital.studioweb.selfhub_app.data.service.ApiService
import digital.studioweb.selfhub_app.data.usecases.GetCategoriesUseCase
import digital.studioweb.selfhub_app.data.usecases.GetProductsUseCase
import digital.studioweb.selfhub_app.data.usecases.LoginUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://selfhub-backend-dcf8eec84eed.herokuapp.com/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepository {
        return HomeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(homeRepository: HomeRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductsUseCase(homeRepository: HomeRepository): GetProductsUseCase {
        return GetProductsUseCase(homeRepository)
    }
}
