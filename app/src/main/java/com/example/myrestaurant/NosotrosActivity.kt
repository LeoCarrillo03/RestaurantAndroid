package com.example.myrestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NosotrosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nosotros)

        // Encuentra el botón por su ID
        val btnVolverInicio: Button = findViewById(R.id.btnVolverInicio)

        // Establece un listener para el clic del botón
        btnVolverInicio.setOnClickListener {
            // Crea un Intent para ir a la actividad principal (cambia MainActivity::class.java según tu caso)
            val intent = Intent(this, MainActivity::class.java)

            // Inicia la actividad principal
            startActivity(intent)
        }
    }
}
