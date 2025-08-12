package digital.studioweb.selfhub_app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import digital.studioweb.selfhub_app.data.features.auth.AuthAPI
import digital.studioweb.selfhub_app.data.features.auth.AuthRepositoryImpl
import digital.studioweb.selfhub_app.data.features.home.HomeRepositoryImpl
import digital.studioweb.selfhub_app.data.features.home.HomeAPI
import digital.studioweb.selfhub_app.domain.features.auth.AuthRepository
import digital.studioweb.selfhub_app.domain.features.auth.LoginUseCase
import digital.studioweb.selfhub_app.domain.features.home.HomeGetCategoriesUseCase
import digital.studioweb.selfhub_app.domain.features.home.HomeGetProductsUseCase
import digital.studioweb.selfhub_app.domain.features.home.HomeCreateOrderUseCase
import digital.studioweb.selfhub_app.domain.features.home.HomeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //region Retrofit

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

    //endregion

    //region Auth

    @Provides
    @Singleton
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authAPI: AuthAPI): AuthRepository {
        return AuthRepositoryImpl(authAPI)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    //endregion

    //region Home

    @Provides
    @Singleton
    fun provideHomeAPI(retrofit: Retrofit): HomeAPI {
        return retrofit.create(HomeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeAPI: HomeAPI): HomeRepository {
        return HomeRepositoryImpl(homeAPI)
    }

    @Provides
    @Singleton
    fun provideHomeGetCategoriesUseCase(homeRepository: HomeRepository): HomeGetCategoriesUseCase {
        return HomeGetCategoriesUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideHomeGetProductsUseCase(homeRepository: HomeRepository): HomeGetProductsUseCase {
        return HomeGetProductsUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideHomeCreateOrderUseCase(homeRepository: HomeRepository): HomeCreateOrderUseCase {
        return HomeCreateOrderUseCase(homeRepository)
    }

    //endregion
}
