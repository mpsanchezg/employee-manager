package employeesmanagement.di

import dagger.Module
import dagger.Provides
import employeesmanagement.domain.usecase.DeleteEmployeesUseCase
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.domain.usecase.PopulateEmployeesUseCase
import employeesmanagement.employeesmanagement.domain.usecase.AddNewEmployeeUseCase
import employeesmanagement.presentation.EmployeeManager
import javax.inject.Singleton

@Module
class EmployeesManagerModule {
    @Provides
    @Singleton
    fun provideDataSource(
        populateEmployeesUseCase: PopulateEmployeesUseCase,
        getEmployeesUseCase: GetEmployeesUseCase,
        deleteEmployeesUseCase: DeleteEmployeesUseCase,
        addNewEmployeeUseCase: AddNewEmployeeUseCase
    ): EmployeeManager =
        EmployeeManager(
            populateEmployeesUseCase,
            getEmployeesUseCase,
            deleteEmployeesUseCase,
            addNewEmployeeUseCase
        )
}