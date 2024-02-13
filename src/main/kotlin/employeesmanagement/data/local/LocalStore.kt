package employeesmanagement.data.local

import employeesmanagement.domain.model.Employee

class LocalStore {
    private val employees: ArrayList<Employee> = arrayListOf()

    fun getEmployees(): List<Employee> {
        return employees.toList()
    }

    fun addEmployees(newEmployees: List<Employee>): Result {
        employees.addAll(newEmployees)
        return Result.SUCCESS
    }

    fun addEmployee(newEmployee: Employee): Result {
        return if (employees.contains(newEmployee)) {
            Result.ERROR
        } else {
            employees.add(newEmployee)
            Result.SUCCESS
        }
    }

    fun deleteEmployee(employee: Employee): Result {
        return if (!employees.contains(employee)) {
            Result.ERROR
        } else {
            employees.remove(employee)
            Result.SUCCESS
        }
    }

    enum class Result {
        SUCCESS,
        ERROR
    }
}
