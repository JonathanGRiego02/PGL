package com.retokotlin.pruebaclase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        val nombreEditText = findViewById<EditText>(R.id.nombreEditText)

        val confirmarButton = findViewById<Button>(R.id.confirmarButton)
        confirmarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()

            val intent = Intent(this, saludoActivity::class.java)

            intent.putExtra("nombre_saludo", nombre)

            startActivity(intent)

            finish()
        }
    }
}