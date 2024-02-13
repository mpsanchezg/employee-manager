import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import employeesmanagement.data.datsource.EmployeeLocalDataSource
import employeesmanagement.data.datsource.EmployeeRemoteDataSource
import employeesmanagement.data.repository.impl.EmployeeRepositoryImpl
import employeesmanagement.domain.repository.EmployeeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideEmployeeRepository(
        remoteDataSource: EmployeeRemoteDataSource,
        localDataSource: EmployeeLocalDataSource
    ): EmployeeRepository = EmployeeRepositoryImpl(remoteDataSource, localDataSource)
}