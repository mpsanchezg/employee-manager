package employeesmanagement.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import employeesmanagement.data.network.api.ApiClient
import employeesmanagement.data.network.RandomUserApi
import employeesmanagement.data.network.api.ApiConfig
import employeesmanagement.data.network.api.BaseUrls.RANDOM_USER_BASE
import employeesmanagement.data.network.retrofit.RetrofitClient
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideRetrofitApiClient(): ApiClient<RandomUserApi> = RetrofitClient(
        ApiConfig(
            baseUrl = RANDOM_USER_BASE,
            token = ""
        ), RandomUserApi::class.java
    )
}
