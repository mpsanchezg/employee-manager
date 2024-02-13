package employeesmanagement.util

sealed class Either <out L, out R> {
  data class Error<out L>(val error: L): Either<L, Nothing>()
  data class Success<out R>(val success: R): Either<Nothing, R>()

  val isSuccess get() = this is Success<R>
  val isError get() = this is Error<L>

  fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T {
   return when (this) {
     is Error -> fnL(error)
     is Success -> fnR(success)
   }
  }

  inline fun <C, D> bimap(
    crossinline leftOperation: (L) -> C,
    crossinline rightOperation: (R) -> D
  ): Either<C, D> = fold({ Error(leftOperation(it)) }, { Success(rightOperation(it)) })
}
