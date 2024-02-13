package employeesmanagement.domain.model

class DomainError(
  val type: DomainErrorType,
  val message: String? = null
)

enum class DomainErrorType {
  API_ERROR,
  LOCAL_STORAGE_ERROR,
  UNKNOWN_ERROR,
  INTERNET_CONNECTION_ERROR
}
