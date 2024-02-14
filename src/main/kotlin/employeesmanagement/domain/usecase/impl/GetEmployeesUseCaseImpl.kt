package employeesmanagement.domain.usecase.impl

import employeesmanagement.domain.model.Employee
import employeesmanagement.domain.repository.EmployeeRepository
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.util.Either
import javax.inject.Inject

class GetEmployeesUseCaseImpl @Inject constructor(
    private val repository: EmployeeRepository
): GetEmployeesUseCase {
    override fun invoke(): List<Employee> {
        return Either.Success(repository.getLocalEmployees()).success
    }
}
