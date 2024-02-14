package employeesmanagement.di

import dagger.Module
import dagger.Provides
import employeesmanagement.data.datsource.EmployeeLocalDataSource
import employeesmanagement.data.datsource.EmployeeRemoteDataSource
import employeesmanagement.data.datsource.impl.EmployeeLocalDataSourceImpl
import employeesmanagement.data.datsource.impl.EmployeeRemoteDataSourceImpl
import employeesmanagement.data.local.LocalStore
import employeesmanagement.data.network.RandomUserApi
import employeesmanagement.data.network.api.ApiClient
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideDataSource(
        apiClient: ApiClient<RandomUserApi>
    ): EmployeeRemoteDataSource =
        EmployeeRemoteDataSourceImpl(apiClient)

    @Provides
    @Singleton
    fun provideLocalDataSource(): EmployeeLocalDataSource = EmployeeLocalDataSourceImpl(LocalStore())
}
