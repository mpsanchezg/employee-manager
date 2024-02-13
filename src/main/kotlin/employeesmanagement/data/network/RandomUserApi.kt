package employeesmanagement.data.network

import employeesmanagement.data.model.RandomUsersResultDto
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserApi {
    @GET(Endpoints.RANDOM_USER)
    suspend fun getRandomUsers(): Response<RandomUsersResultDto>
}
