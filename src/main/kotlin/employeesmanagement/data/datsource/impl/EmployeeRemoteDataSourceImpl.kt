package employeesmanagement.data.datsource.impl

import employeesmanagement.data.datsource.EmployeeRemoteDataSource
import employeesmanagement.data.model.RandomUsersResultDto
import employeesmanagement.data.model.RemoteError
import employeesmanagement.data.network.api.ApiClient
import employeesmanagement.data.network.RandomUserApi
import employeesmanagement.data.network.retrofit.executeNotNullCall
import employeesmanagement.util.Either
import javax.inject.Inject

class EmployeeRemoteDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient<RandomUserApi>
): EmployeeRemoteDataSource {
    override suspend fun getEmployees(): Either<RemoteError, RandomUsersResultDto> {
        return executeNotNullCall {
            apiClient.endpoints.getRandomUsers()
        }
    }
}
