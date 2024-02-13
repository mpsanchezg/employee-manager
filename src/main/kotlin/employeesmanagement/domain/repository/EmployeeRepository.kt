package employeesmanagement.domain.repository

import employeesmanagement.domain.model.DomainError
import employeesmanagement.domain.model.Employee
import employeesmanagement.util.Either

interface EmployeeRepository {
  suspend fun getRemoteEmployees(): Either<DomainError, List<Employee>>
  fun getLocalEmployees(): List<Employee>
  fun deleteEmployee(employee: Employee): Either<DomainError, String>
  fun addEmployee(employee: Employee): Either<DomainError, String>
  fun addEmployees(employees: List<Employee>): Either<DomainError, String>
}
