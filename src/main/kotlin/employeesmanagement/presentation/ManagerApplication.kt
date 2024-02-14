package employeesmanagement.employeesmanagement.presentation

import employeesmanagement.data.datsource.impl.EmployeeLocalDataSourceImpl
import employeesmanagement.data.datsource.impl.EmployeeRemoteDataSourceImpl
import employeesmanagement.data.local.LocalStore
import employeesmanagement.data.network.RandomUserApi
import employeesmanagement.data.network.api.ApiConfig
import employeesmanagement.data.network.api.BaseUrls
import employeesmanagement.data.network.retrofit.RetrofitClient
import employeesmanagement.data.repository.impl.EmployeeRepositoryImpl
import employeesmanagement.domain.usecase.DeleteEmployeesUseCase
import employeesmanagement.domain.usecase.GetEmployeesUseCase
import employeesmanagement.domain.usecase.PopulateEmployeesUseCase
import employeesmanagement.domain.usecase.impl.DeleteEmployeesUseCaseImpl
import employeesmanagement.domain.usecase.impl.GetEmployeesUseCaseImpl
import employeesmanagement.domain.usecase.impl.PopulateEmployeesUseCaseImpl
import employeesmanagement.employeesmanagement.domain.usecase.AddNewEmployeeUseCase
import employeesmanagement.employeesmanagement.domain.usecase.impl.AddNewEmployeeUseCaseImpl
import employeesmanagement.presentation.EmployeeManager
import employeesmanagement.util.orZero
import employeesmanagement.util.toCurrency

class ManagerApplication {
    private val local = EmployeeLocalDataSourceImpl(LocalStore())
    private val remote = EmployeeRemoteDataSourceImpl(
        RetrofitClient(
            ApiConfig(baseUrl = BaseUrls.RANDOM_USER_BASE, token = ""),
            RandomUserApi::class.java
        )
    )
    private val repository = EmployeeRepositoryImpl(remote, local)
    private val populateEmployeesUseCase: PopulateEmployeesUseCase = PopulateEmployeesUseCaseImpl(repository)
    private val getEmployeesUseCase: GetEmployeesUseCase = GetEmployeesUseCaseImpl(repository)
    private val deleteEmployeesUseCase: DeleteEmployeesUseCase = DeleteEmployeesUseCaseImpl(repository)
    private val addNewEmployeeUseCase: AddNewEmployeeUseCase = AddNewEmployeeUseCaseImpl(repository)
    private val employeeManager: EmployeeManager

    init {
        println("-------------------------------------")
        println("BIENVENIDO/A AL GESTOR DE EMPLEADOS")
        println("-------------------------------------\n")

        employeeManager = EmployeeManager(
            populateEmployeesUseCase,
            getEmployeesUseCase,
            deleteEmployeesUseCase,
            addNewEmployeeUseCase
        )
    }

    fun start(): Boolean {
        printActionOptions()

        val action = readlnOrNull()
        return handleUserOption(action)
    }

    fun finish() {
        println("¡Vuelve pronto!")
    }

    private fun printActionOptions() {
        println("Ingrese el número de la acción que quiera realizar:")
        println("1. Agregar un nuevo empleado")
        println("2. Ver el detalle de un empleado")
        println("3. Eliminar un empleado")
        println("4. Mostrar lista de empleados")
        println("Presione cualquier otra tecla para salir.")
    }

    private fun handleUserOption(action: String?): Boolean {
        when (val actionType: ActionType? = ActionType.values().find { it.ordinal.toString() == action }) {
            ActionType.ADD_EMPLOYEE -> printActionWelcome(actionType, ::addNewEmployeeAction)
            ActionType.SHOW_EMPLOYEE_DETAILS -> printActionWelcome(actionType, ::showEmployeeDetailsAction)
            ActionType.REMOVE_EMPLOYEE -> printActionWelcome(actionType, ::removeEmployeeAction)
            ActionType.SHOW_ALL_EMPLOYEES -> printActionWelcome(actionType, ::showEmployeesListAction)
            ActionType.FINISH -> return false
            else -> return false
        }
        return true
    }

    private fun printActionWelcome(actionType: ActionType, action: () -> Unit) {
        println("--------------- ${actionType.title} --------------------")
        if (actionType != ActionType.SHOW_ALL_EMPLOYEES) {
            println("Presione Enter para continuar, para volver al menú ingrese cualquier otro comando.")
            val userContinue = readlnOrNull()
            if (!userContinue.isNullOrBlank()) return
        }
        action()
        println("-------------------------------------------------------")
    }

    private fun addNewEmployeeAction() {
        println("Ingrese el nombre: ")
        val name = readlnOrNull().orEmpty()
        println("Ingrese la edad: ")
        val age = readlnOrNull()?.toInt().orZero()
        println("Ingrese el salario: ")
        val salary = readlnOrNull()?.toInt().orZero().toCurrency()
        println("Ingrese el email: ")
        val email = readlnOrNull().orEmpty()

        employeeManager.addNewEmployee(name, age, salary, email)
        printEmployeesList("Lista de empleados actualizada")
    }

    private fun showEmployeeDetailsAction() {
        println("Ingrese el nombre del empleado:")
        val name = readlnOrNull()

        employeeManager.showEmployeeByName(name.orEmpty())
    }

    private fun removeEmployeeAction() {
        printEmployeesList("Lista de empleados")

        println("Ingrese el nombre del empleado que desea eliminar, presione Enter para volver al menú: ")
        val name = readlnOrNull()
        if (name.isNullOrBlank()) return

        employeeManager.removeEmployees(name)
    }

    private fun showEmployeesListAction() = employeeManager.showEmployeesList()

    private fun printEmployeesList(message: String) {
        println("$message:")
        employeeManager.showEmployeesList()
    }

    enum class ActionType(val title: String? = null) {
        FINISH,
        ADD_EMPLOYEE("NUEVO EMPLEADO"),
        SHOW_EMPLOYEE_DETAILS("DETALLES EMPLEADO"),
        REMOVE_EMPLOYEE("ELIMINAR EMPLEADO"),
        SHOW_ALL_EMPLOYEES("LISTA DE EMPLEADOS")
    }
}
