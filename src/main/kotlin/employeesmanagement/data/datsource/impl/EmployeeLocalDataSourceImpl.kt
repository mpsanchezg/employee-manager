package employeesmanagement.data.datsource.impl

import employeesmanagement.data.datsource.EmployeeLocalDataSource
import employeesmanagement.data.local.LocalStore
import employeesmanagement.domain.model.Employee
import javax.inject.Inject

class EmployeeLocalDataSourceImpl @Inject constructor(
    private val localStore: LocalStore
): EmployeeLocalDataSource {
    override fun addEmployees(employees: List<Employee>): String {
        return localStore.addEmployees(employees).name
    }

    override fun getEmployees(): List<Employee> {
        return localStore.getEmployees()
    }

    override fun saveEmployee(idEmployee: String, newOrEditedEmployee: Employee): String {
        return localStore.addEmployee(newOrEditedEmployee).name
    }

    override fun deleteEmployee(employee: Employee): String {
        return localStore.deleteEmployee(employee).name
    }
}
