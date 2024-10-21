package com.proyecto.retoskotlin.ejercicio2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyecto.retoskotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        botDeSeguridad()
    }

    private fun botDeSeguridad() {
        val jonathan = Persona("Jonathan", "21", listOf("leer", "baloncesto", "videojuegos"))

        // Comprobamos nombre
        if (jonathan.name != "Jonathan"){
            Log.e("Error", "El nombre no es el correcto.")
        } else {
            Log.i("Success", "El nombre es el correcto.")
            // Comprobamos edad
            val edad = jonathan.age.toInt()
            if (edad in 0..17) {
                Log.e("Error", "Eres menor de edad.")
            } else if (edad in 65..100) {
                Log.e("Error", "Eres demasiado mayor")
            } else {
                var contador = 1
                Log.i("Success", "Hobbies:")
                for (hobbies in jonathan.hobbies) {
                    Log.i("Success", "$contador.- $hobbies")
                    contador++
                }
            }
        }


    }
}