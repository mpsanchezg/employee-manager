package employeesmanagement.data.datsource

import employeesmanagement.data.model.RandomUsersResultDto
import employeesmanagement.data.model.RemoteError
import employeesmanagement.util.Either

interface EmployeeRemoteDataSource {
    suspend fun getEmployees(): Either<RemoteError, RandomUsersResultDto>
}
