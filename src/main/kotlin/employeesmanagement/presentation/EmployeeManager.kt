package employeesmanagement.presentation

import dagger.Component
import employeesmanagement.domain.model.Employee
import employeesmanagement.domain.usecase.DeleteEmployeesUseCase
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.domain.usecase.PopulateEmployeesUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Component
class EmployeeManager @Inject constructor(
    private val populateEmployeesUseCase: PopulateEmployeesUseCase,
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val deleteEmployeesUseCase: DeleteEmployeesUseCase
){
    private val employeeList = arrayListOf<Employee>()
    init { populateEmployees() }


    fun listEmployees() {
        getEmployeesList()
        println("Lista de Empleados:")
        var index = 1
        employeeList.forEach {
            print("$index ${it.name}")
            index += 1
        }
    }

    fun removeEmployees(name: String) {
        if (deleteEmployeesUseCase.byName(name)) {
            print("Empleado borrado con éxito")
        } else {
            println("Lo sentimos, no pudimos borrar el empleado. Intente nuevamente")
        }

    }

    fun removeEmployee(id: String) {
        if (deleteEmployeesUseCase.byId(id)) {
            print("Empleado borrado con éxito")
        } else {
            println("Lo sentimos, no pudimos borrar el empleado. Intente nuevamente")
        }

    }

    private fun populateEmployees() = runBlocking {
        launch {
            populateEmployeesUseCase()
            println("Listo!")
        }
        println("Cargando...")
    }


    private fun getEmployeesList() {
        employeeList.addAll(getEmployeesUseCase())
    }
}