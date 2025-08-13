package digital.studioweb.selfhub_app.data.di

import android.content.Context
import digital.studioweb.selfhub_app.data.sources.SecureSharedLocalDataSource
import digital.studioweb.selfhub_app.data.sources.SecureSharedLocalDataSourceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import digital.studioweb.selfhub_app.data.features.auth.AuthAPI
import digital.studioweb.selfhub_app.data.features.auth.AuthAPIDataSource
import digital.studioweb.selfhub_app.data.features.auth.AuthAPIDataSourceImpl
import digital.studioweb.selfhub_app.data.features.auth.AuthLocalDataSource
import digital.studioweb.selfhub_app.data.features.auth.AuthLocalDataSourceImpl
import digital.studioweb.selfhub_app.data.features.auth.AuthRepositoryImpl
import digital.studioweb.selfhub_app.data.features.cart.CartAPI
import digital.studioweb.selfhub_app.data.features.cart.CartRepositoryImpl
import digital.studioweb.selfhub_app.data.features.home.HomeRepositoryImpl
import digital.studioweb.selfhub_app.data.features.home.HomeAPI
import digital.studioweb.selfhub_app.data.sources.encryption.EncryptionManager
import digital.studioweb.selfhub_app.data.sources.encryption.EncryptionManagerInterface
import digital.studioweb.selfhub_app.domain.features.auth.AssociateDeviceUseCase
import digital.studioweb.selfhub_app.domain.features.auth.AuthRepository
import digital.studioweb.selfhub_app.domain.features.auth.GetCNPJUseCase
import digital.studioweb.selfhub_app.domain.features.auth.GetRestaurantByCNPJUseCase
import digital.studioweb.selfhub_app.domain.features.auth.SaveCNPJUseCase
import digital.studioweb.selfhub_app.domain.features.auth.SaveRestaurantIdUseCase
import digital.studioweb.selfhub_app.domain.features.cart.CartCreateOrderUseCase
import digital.studioweb.selfhub_app.domain.features.cart.CartRepository
import digital.studioweb.selfhub_app.domain.features.home.HomeGetCategoriesUseCase
import digital.studioweb.selfhub_app.domain.features.home.HomeGetProductsUseCase
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
    fun provideEncryptionManager(): EncryptionManagerInterface {
        return EncryptionManager()
    }

    @Provides
    @Singleton
    fun provideSecureSharedLocalDataSource(
        @ApplicationContext context: Context,
        encryptionManagerInterface: EncryptionManagerInterface
    ): SecureSharedLocalDataSourceInterface {
        return SecureSharedLocalDataSource(context, encryptionManagerInterface)
    }

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(secureSharedLocalDataSourceInterface: SecureSharedLocalDataSourceInterface): AuthLocalDataSource {
        return AuthLocalDataSourceImpl(secureSharedLocalDataSourceInterface)
    }

    @Provides
    @Singleton
    fun provideAuthAPIDataSource(api: AuthAPI): AuthAPIDataSource {
        return AuthAPIDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authAPIDataSource: AuthAPIDataSource,
        authLocalDataSource: AuthLocalDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(authAPIDataSource, authLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideSaveCNPJUseCase(authRepository: AuthRepository): SaveCNPJUseCase {
        return SaveCNPJUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSaveRestaurantIdUseCase(authRepository: AuthRepository): SaveRestaurantIdUseCase {
        return SaveRestaurantIdUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetRestaurantByCNPJUseCase(authRepository: AuthRepository): GetRestaurantByCNPJUseCase {
        return GetRestaurantByCNPJUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetCNPJUseCase(authRepository: AuthRepository): GetCNPJUseCase {
        return GetCNPJUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideAssociateDeviceUseCase(authRepository: AuthRepository): AssociateDeviceUseCase {
        return AssociateDeviceUseCase(authRepository)
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
    fun provideHomeRepository(homeAPI: HomeAPI, secureSharedLocalDataSourceInterface: SecureSharedLocalDataSourceInterface): HomeRepository {
        return HomeRepositoryImpl(homeAPI, secureSharedLocalDataSourceInterface)
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

    //endregion

    //region Cart

    @Provides
    @Singleton
    fun provideCartAPI(retrofit: Retrofit): CartAPI {
        return retrofit.create(CartAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartAPi: CartAPI): CartRepository {
        return CartRepositoryImpl(cartAPi)
    }

    @Provides
    @Singleton
    fun provideCartCreateOrderUseCase(cartRepository: CartRepository): CartCreateOrderUseCase {
        return CartCreateOrderUseCase(cartRepository)
    }

    //endregion
}
