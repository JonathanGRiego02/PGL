package com.proyecto.retoskotlin.ejercicio1


class Programador : ProgramadorInterface {

    private fun getNombre(): String {
        return "Jonathan" // or another name of your choice
    }

    private fun getEdad(): Int {
        return 21 // or another age of your choice
    }

    private fun getLanguage(): String {
        return "Python" // or another language of your choice
    }

    override fun getProgrammerData(): ProgrammerData {
        return ProgrammerData(getNombre(), getEdad(), getLanguage())
    }
}