package employeesmanagement.data.datsource

import employeesmanagement.domain.model.Employee

interface EmployeeLocalDataSource {
    fun addEmployees(employees: List<Employee>): String
    fun getEmployees(): List<Employee>
    fun saveEmployee(idEmployee: String, newOrEditedEmployee: Employee): String
    fun deleteEmployee(employee: Employee): String
}
