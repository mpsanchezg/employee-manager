package employeesmanagement.data.network.api

interface ApiClient<T> {
    val endpoints: T
}