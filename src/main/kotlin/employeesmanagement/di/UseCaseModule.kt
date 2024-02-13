import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import employeesmanagement.domain.repository.EmployeeRepository
import employeesmanagement.domain.usecase.DeleteEmployeesUseCase
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.domain.usecase.PopulateEmployeesUseCase
import employeesmanagement.domain.usecase.impl.DeleteEmployeesUseCaseImpl
import employeesmanagement.domain.usecase.impl.PopulateEmployeesUseCaseImpl
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providePopulateEmployeesUseCase(
        repository: EmployeeRepository
    ): PopulateEmployeesUseCase = PopulateEmployeesUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetEmployeesUseCase(
        repository: EmployeeRepository
    ): GetEmployeesUseCase = GetEmployeesUseCase(repository::getLocalEmployees)

    @Provides
    @Singleton
    fun provideDeleteEmployeesUseCase(
        repository: EmployeeRepository
    ): DeleteEmployeesUseCase = DeleteEmployeesUseCaseImpl(repository)
}