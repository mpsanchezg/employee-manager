package employeesmanagement.di

import RepositoryModule
import UseCaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import employeesmanagement.domain.usecase.DeleteEmployeesUseCase
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.domain.usecase.PopulateEmployeesUseCase
import employeesmanagement.presentation.EmployeeManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EmployeesManagerModule {
    @Provides
    @Singleton
    fun provideDataSource(
        populateEmployeesUseCase: PopulateEmployeesUseCase,
        getEmployeesUseCase: GetEmployeesUseCase,
        deleteEmployeesUseCase: DeleteEmployeesUseCase
    ): EmployeeManager =
        EmployeeManager(
            populateEmployeesUseCase,
            getEmployeesUseCase,
            deleteEmployeesUseCase
        )
}