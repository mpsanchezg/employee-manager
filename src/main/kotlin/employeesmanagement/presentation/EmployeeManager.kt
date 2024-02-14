package employeesmanagement.presentation

import employeesmanagement.domain.model.Employee
import employeesmanagement.domain.usecase.DeleteEmployeesUseCase
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.domain.usecase.PopulateEmployeesUseCase
import employeesmanagement.employeesmanagement.domain.usecase.AddNewEmployeeUseCase
import employeesmanagement.util.randomString
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EmployeeManager @Inject constructor(
    private val populateEmployeesUseCase: PopulateEmployeesUseCase,
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val deleteEmployeesUseCase: DeleteEmployeesUseCase,
    private val addNewEmployeeUseCase: AddNewEmployeeUseCase
) {
    private val employeeList = arrayListOf<Employee>()

    init { populateEmployees() }

    fun showEmployeesList() {
        getEmployeesList()
        var index = 1
        employeeList.forEach {
            println("$index ${it.name}")
            index += 1
        }
    }

    fun addNewEmployee(name: String, age: Int, salary: String, email: String) = runBlocking {
        val employee = Employee(id = randomString(), name, age, salary, email)

        launch {
            delay(1000)
            if (addNewEmployeeUseCase(employee)) {
                println("¡Empleado agregado con éxito!\n")
            } else {
                println("¡Lo sentimos! :(\nHubo un error. Por favor, intente más tarde.\n")
            }
        }
        println("Agregando nuevo empleado...")
    }

    fun showEmployeeByName(name: String) = runBlocking {
        val employees = employeeList.filter { it.name.contains(name) }
        launch {
            delay(1000)
            if (employees.isEmpty()) {
                println("¡Lo sentimos! No encontramos empleados con ese nombre. Por favor, intente más tarde.")
            } else {
                println("Encontramos ${employees.size} empleado(s) con ese nombre.")
                println("A continuación se mostrarán los detalles.")
                delay(500)
                println("-------- Empleado --------")
                employees.forEach {
                    printEmployee(it)
                    println("--------------------------")
                    delay(500)
                }
            }
        }
        println("Buscando empleados...")
    }

    fun removeEmployees(name: String) = runBlocking {
        val employee = employeeList.find { it.name.contains(name) }
        employee?.let {
            println("¿Está seguro/a que quiere eliminar a ${employee.name}?")
            println("Presione Enter para continuar. Para cancelar la acción, ingrese cualquier otro comando.")
            val option = readlnOrNull()
            if (!option.isNullOrBlank()) return@let

            launch {
                delay(1000)
                    if (deleteEmployeesUseCase.byId(it.id)) {
                    println("Empleado borrado con éxito")
                } else {
                    println("¡Lo sentimos! :(\nNo pudimos borrar el empleado. Por favor, intente más tarde.")
                }
            }
        } ?: println("¡Lo sentimos! :(\nNo encontramos el empleado. Por favor, intente más tarde.")
    }

    private fun populateEmployees() = runBlocking {
        launch {
            populateEmployeesUseCase()
            delay(1000)
            println("Listo!\n")
        }
        println("Cargando empleados...")
    }

    private fun getEmployeesList() {
        employeeList.clear()
        employeeList.addAll(getEmployeesUseCase())
    }

    private fun printEmployee(employee: Employee) {
        println("Nombre: ${employee.name}")
        println("Edad: ${employee.age}")
        println("Correo electrónico: ${employee.email}")
        println("Salario: ${employee.salary}")
    }
}