package employeesmanagement.domain.usecase

interface DeleteEmployeesUseCase {
    fun byId(id: String): Boolean
    fun byName(name: String): Boolean
}
