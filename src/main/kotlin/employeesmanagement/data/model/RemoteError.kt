package employeesmanagement.data.model

import com.google.gson.annotations.SerializedName

sealed class RemoteError(open val message: String)

object ConnectionError: RemoteError("No internet connection Error")

data class ApiError(@SerializedName("error") val exceptionError: String): RemoteError(exceptionError)

data class UnknownError(override val message: String): RemoteError(message)

data class UnexpectedNullBodyError(override val message: String): RemoteError(message)