package employeesmanagement

import employeesmanagement.employeesmanagement.presentation.ManagerApplication
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    var runApplication = true
    val application = ManagerApplication()

    while(runApplication) {
        runApplication = application.start()
    }
    application.finish()
    exitProcess(0)
}
