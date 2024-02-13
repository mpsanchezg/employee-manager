package employeesmanagement.data.network.retrofit

import employeesmanagement.data.model.RemoteError
import employeesmanagement.data.network.helper.processRemoteException
import employeesmanagement.data.network.helper.processRemoteNonNullResponse
import employeesmanagement.util.Either
import java.lang.Exception
import retrofit2.Response

suspend inline fun <T> executeNotNullCall(
    crossinline retrofitCall: suspend () -> Response<T>
): Either<RemoteError, T> {
  return  try {
      retrofitCall().processRemoteNonNullResponse()
  } catch(e: Exception) {
      return Either.Error(e.processRemoteException())
  }
}
