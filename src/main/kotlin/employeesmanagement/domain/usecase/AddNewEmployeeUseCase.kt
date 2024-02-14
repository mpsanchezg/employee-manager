package employeesmanagement.employeesmanagement.domain.usecase

import employeesmanagement.domain.model.Employee

fun interface AddNewEmployeeUseCase: (Employee) -> Boolean
