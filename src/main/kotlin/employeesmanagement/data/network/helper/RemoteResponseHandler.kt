package employeesmanagement.data.network.helper

import com.google.gson.Gson
import employeesmanagement.data.model.ApiError
import employeesmanagement.data.model.RemoteError
import employeesmanagement.data.model.UnexpectedNullBodyError
import employeesmanagement.data.model.UnknownError
import employeesmanagement.util.Either
import java.lang.Exception
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.processRemoteNonNullResponse(): Either<RemoteError, T> {
    return when {
        this.isSuccessful -> body()?.let {
            Either.Success(it)
        } ?: throw UnexpectedEmptyBodyException("Empty body")
        else ->
            throw HttpException(this)
    }
}

fun Throwable.processRemoteException(): RemoteError = when(this) {
    is HttpException -> response().processHttpException()
    is UnexpectedEmptyBodyException -> UnexpectedNullBodyError(message?: "Empty body")
    else -> UnknownError(message?: UNHANDLED_ERROR_MESSAGE)
}

private fun Response<*>?.processHttpException(): RemoteError {
    val errorBody: String = this?.errorBody()?.string().orEmpty()
    return when {
        this == null ->  UnknownError(UNKNOWN_ERROR_MESSAGE)
        this.hasError(errorBody) -> {
            try {
                Gson().fromJson(errorBody, ApiError::class.java)
            } catch(exception: Exception) { UnknownError(UNKNOWN_ERROR_MESSAGE) }
        }
        else -> UnknownError(UNHANDLED_ERROR_MESSAGE)
    }
}

private fun <T> Response<T>.hasError(body: String): Boolean =
    errorBody() != null && body.isNotBlank()

class UnexpectedEmptyBodyException(message: String): Exception(message)

private const val UNKNOWN_ERROR_MESSAGE = "Unknown error"
private const val UNHANDLED_ERROR_MESSAGE = "Unhandled unknown error"
