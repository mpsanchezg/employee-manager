package employeesmanagement.domain.usecase.impl

import employeesmanagement.domain.model.DomainError
import employeesmanagement.domain.model.DomainErrorType
import employeesmanagement.domain.repository.EmployeeRepository
import employeesmanagement.domain.usecase.PopulateEmployeesUseCase
import employeesmanagement.util.Either

class PopulateEmployeesUseCaseImpl(
    private val repository: EmployeeRepository,
): PopulateEmployeesUseCase {
    override suspend fun invoke(): Boolean {
        val getEmployees = repository.getRemoteEmployees().bimap(
            {},
            { employees ->
            val isSuccess = repository.addEmployees(employees)
            isSuccess.isSuccess
        })

        return getEmployees.isSuccess
    }
}