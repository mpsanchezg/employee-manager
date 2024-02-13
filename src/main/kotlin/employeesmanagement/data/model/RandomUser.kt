package employeesmanagement.data.model

import com.google.gson.annotations.SerializedName

data class RandomUsersResultDto(
    @SerializedName("results") val results: List<RandomUserDto>
)

data class RandomUserDto(
    @SerializedName("name") val name: NameDto? = NameDto(),
    @SerializedName("gender") val gender: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("dob") val dob: DobDto = DobDto(),
)

data class NameDto(
    @SerializedName("title") val title: String? = null,
    @SerializedName("first") val first: String? = null,
    @SerializedName("last") val last: String? = null
)

data class DobDto(
    @SerializedName("date")  val date: String? = null,
    @SerializedName("age") val age: Int? = null
)
