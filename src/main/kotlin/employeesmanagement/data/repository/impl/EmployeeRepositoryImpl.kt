package employeesmanagement.data.repository.impl

import employeesmanagement.data.datsource.EmployeeLocalDataSource
import employeesmanagement.data.datsource.EmployeeRemoteDataSource
import employeesmanagement.data.local.LocalStore
import employeesmanagement.data.mapper.toDomainError
import employeesmanagement.data.mapper.toDomainModel
import employeesmanagement.domain.model.DomainError
import employeesmanagement.domain.model.DomainErrorType
import employeesmanagement.domain.model.Employee
import employeesmanagement.domain.repository.EmployeeRepository
import employeesmanagement.util.Either
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
  private val remoteDataSource: EmployeeRemoteDataSource,
  private val localDataSource: EmployeeLocalDataSource
): EmployeeRepository {
  override fun getLocalEmployees(): List<Employee> {
    return localDataSource.getEmployees()
  }

  override suspend fun getRemoteEmployees(): Either<DomainError, List<Employee>>  {
    return remoteDataSource.getEmployees().bimap(
      { it.toDomainError() }, { it.toDomainModel() }
    )
  }

  override fun deleteEmployee(employee: Employee): Either<DomainError, String> {
    val isDeleted = localDataSource.deleteEmployee(employee)
    return if (isDeleted == LocalStore.Result.ERROR.name) {
      Either.Error(DomainError(DomainErrorType.LOCAL_STORAGE_ERROR, "Employee not deleted"))
    } else {
      Either.Success("Employee deleted successfully")
    }
  }

  override fun addEmployee(employee: Employee): Either<DomainError, String> {
    return if (localDataSource.saveEmployee(employee.id, employee) == "ERROR") {
      Either.Error(DomainError(DomainErrorType.LOCAL_STORAGE_ERROR, "Employee not added"))
    } else {
      Either.Success("Employee added successfully")
    }
  }

  override fun addEmployees(employees: List<Employee>): Either<DomainError, String> {
    val result = localDataSource.addEmployees(employees)
    return if (result == LocalStore.Result.ERROR.name) {
      Either.Error(DomainError(DomainErrorType.LOCAL_STORAGE_ERROR, "Employees not added"))
    } else {
      Either.Success(result)
    }
  }
}
