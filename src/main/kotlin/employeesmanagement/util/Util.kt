package employeesmanagement.util

import java.text.NumberFormat
import java.util.Currency
import java.util.UUID

fun Int.toCurrency(): String {
  val format = NumberFormat.getCurrencyInstance()
  format.maximumFractionDigits = 0
  format.currency = Currency.getInstance("EUR")

  return format.format(this)
}

fun Int?.orZero() = this ?: 0

fun randomString(): String {
  return UUID.randomUUID().toString()
}
