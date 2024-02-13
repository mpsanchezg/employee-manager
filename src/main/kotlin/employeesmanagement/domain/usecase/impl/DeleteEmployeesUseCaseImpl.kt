package employeesmanagement.domain.usecase.impl

import employeesmanagement.domain.repository.EmployeeRepository
import employeesmanagement.domain.usecase.DeleteEmployeesUseCase
import javax.inject.Inject

class DeleteEmployeesUseCaseImpl @Inject constructor(
    private val repository: EmployeeRepository
): DeleteEmployeesUseCase {
    override fun byId(id: String): Boolean {
        val employees = repository.getLocalEmployees()
        val employeeToDelete = employees.find { it.id == id }

        employeeToDelete?.let {
            return repository.deleteEmployee(employeeToDelete).isSuccess
        }
        return  false
    }

    override fun byName(name: String): Boolean {
        val employees = repository.getLocalEmployees()
        val employeeToDelete = employees.find { it.name == name }

        employeeToDelete?.let {
            return repository.deleteEmployee(employeeToDelete).isSuccess
        }
        return  false
    }

}