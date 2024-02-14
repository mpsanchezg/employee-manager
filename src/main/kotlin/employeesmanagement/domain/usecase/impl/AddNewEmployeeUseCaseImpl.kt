package employeesmanagement.employeesmanagement.domain.usecase.impl

import employeesmanagement.domain.model.Employee
import employeesmanagement.domain.repository.EmployeeRepository
import employeesmanagement.employeesmanagement.domain.usecase.AddNewEmployeeUseCase

class AddNewEmployeeUseCaseImpl(
    private val repository: EmployeeRepository
): AddNewEmployeeUseCase {
    override fun invoke(employee: Employee): Boolean {
        return repository.addEmployee(employee).isSuccess
    }
}