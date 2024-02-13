package employeesmanagement.domain.usecase

import employeesmanagement.domain.model.Employee

fun interface GetEmployeesUseCase: () -> List<Employee>
