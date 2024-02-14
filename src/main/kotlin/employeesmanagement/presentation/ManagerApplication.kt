package employeesmanagement.employeesmanagement.presentation

import employeesmanagement.data.datsource.impl.EmployeeLocalDataSourceImpl
import employeesmanagement.data.datsource.impl.EmployeeRemoteDataSourceImpl
import employeesmanagement.data.local.LocalStore
import employeesmanagement.data.network.RandomUserApi
import employeesmanagement.data.network.api.ApiConfig
import employeesmanagement.data.network.api.BaseUrls
import employeesmanagement.data.network.retrofit.RetrofitClient
import employeesmanagement.data.repository.impl.EmployeeRepositoryImpl
import employeesmanagement.domain.model.Employee
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
import java.util.Random

class ManagerApplication {
    private val localStore = LocalStore()
    private val local = EmployeeLocalDataSourceImpl(localStore)
    private val remote = EmployeeRemoteDataSourceImpl(
        RetrofitClient(ApiConfig(
        baseUrl = BaseUrls.RANDOM_USER_BASE,
        token = ""
        ), RandomUserApi::class.java)
    )
    private val repository = EmployeeRepositoryImpl(remote, local)
    private val populateEmployeesUseCase: PopulateEmployeesUseCase = PopulateEmployeesUseCaseImpl(repository )
    private val getEmployeesUseCase: GetEmployeesUseCase = GetEmployeesUseCaseImpl(repository)
    private val deleteEmployeesUseCase: DeleteEmployeesUseCase = DeleteEmployeesUseCaseImpl(repository)
    private val addNewEmployeeUseCase: AddNewEmployeeUseCase = AddNewEmployeeUseCaseImpl(repository)

    var employeeManager: EmployeeManager = EmployeeManager(
        populateEmployeesUseCase,
        getEmployeesUseCase,
        deleteEmployeesUseCase,
        addNewEmployeeUseCase
    )

    fun start(): Boolean {
        println("Ingrese la acción que quiera realizar:\n")
        println("1. Agregar un nuevo empleado\n")
        println("2. Ver el detalle de un empleado\n")
        println("3. Eliminar un empleado\n")
        println("4. Mostrar lista de empleados")
        val action = readlnOrNull()

        if (action != null) {
            when (action) {
                "1" -> {
                    addNewEmployeeAction()
                }
                "2" -> {

                }
                "3" -> {}
                "4" -> {}
                else -> {
                    return@start false
                }
            }
        }
        return true
    }

    private fun addNewEmployeeAction() {
        println("---- NUEVO USUARIO ---")
        println("Si no desea continuar, ingrese \"c\" o \"C\"")
        val userContinue = readlnOrNull()
        if (userContinue != null && userContinue.lowercase() == "c") return

        println("Ingrese el nombre: ")
        val name = readlnOrNull()
        println("Ingrese la edad: ")
        val age = readlnOrNull()?.toInt().orZero()
        println("Ingrese el salario: ")
        val salary = readlnOrNull()?.toInt().orZero().toCurrency()
        println("Ingrese el email: ")
        val email = readlnOrNull()?.toInt().orZero().toCurrency()

        employeeManager.addNewEmployee(Employee(
            id = name.orEmpty(),
            name.orEmpty(),
            age,
            salary,
            email
        ))
    }
}
