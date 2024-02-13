package employeesmanagement.domain.usecase.impl

import employeesmanagement.domain.model.DomainError
import employeesmanagement.domain.model.Employee
import employeesmanagement.domain.repository.EmployeeRepository
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.util.Either
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GetEmployeesUseCaseImpl @Inject constructor(
    private val repository: EmployeeRepository
): GetEmployeesUseCase {
    override fun invoke(): Either<DomainError, List<Employee>> {
        return Either.Success(repository.getLocalEmployees())
    }
}
