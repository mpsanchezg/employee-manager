package employeesmanagement.util

import java.text.NumberFormat
import java.util.Currency

fun Int.toCurrency(): String {
  val format = NumberFormat.getCurrencyInstance()
  format.maximumFractionDigits = 0
  format.currency = Currency.getInstance("EUR")

  return format.format(this)
}

fun Int?.orZero() = this ?: 0