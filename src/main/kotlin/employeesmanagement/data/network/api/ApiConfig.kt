package employeesmanagement.data.network.api

data class ApiConfig(
    val baseUrl: String,
    val timeOut: TimeOut = TimeOut.DEFAULT,
    val token: String,
)

enum class TimeOut(val seconds: Long) {
    SHORT(2L),
    DEFAULT(5L),
    LARGE(10L)
}
