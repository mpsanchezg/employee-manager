package employeesmanagement.data.mapper

import employeesmanagement.data.model.ApiError
import employeesmanagement.data.model.RandomUsersResultDto
import employeesmanagement.data.model.RemoteError
import employeesmanagement.data.model.UnexpectedNullBodyError
import employeesmanagement.data.model.UnknownError
import employeesmanagement.domain.model.DomainError
import employeesmanagement.domain.model.DomainErrorType
import employeesmanagement.domain.model.Employee
import employeesmanagement.util.orZero
import employeesmanagement.util.randomString
import employeesmanagement.util.toCurrency

fun RemoteError.toDomainError(): DomainError {
    return when (this) {
        is ApiError -> DomainError(DomainErrorType.API_ERROR, exceptionError)
        is UnexpectedNullBodyError -> DomainError(DomainErrorType.API_ERROR, message)
        is UnknownError -> DomainError(DomainErrorType.UNKNOWN_ERROR, message)
        else -> DomainError(DomainErrorType.INTERNET_CONNECTION_ERROR, message)
    }
}

fun RandomUsersResultDto?.toDomainModel(): List<Employee> {
    val randomUsers = this?.results
    val employees = arrayListOf<Employee>()
    randomUsers?.forEach { randomUser ->
        randomUser.let {
            val salaryRange = (1000..1000000)
            employees.add(
                Employee(
                    id = randomString(),
                    name = "${it.name?.first.orEmpty()} ${it.name?.last.orEmpty()}",
                    salary = salaryRange.random().toCurrency(),
                    age = it.dob.age.orZero(),
                    email = it.email.orEmpty()
                )
            )
        }
    }

    return employees.toList()
}
