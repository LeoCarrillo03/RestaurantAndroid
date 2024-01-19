// AdminActivity.kt

package com.example.myrestaurant

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val textViewNumeroPersonas: TextView = findViewById(R.id.textViewNumeroPersonas)
        val textViewFechaReserva: TextView = findViewById(R.id.textViewFechaReserva)
        val textViewHorarioReserva: TextView = findViewById(R.id.textViewHorarioReserva)

        val numeroPersonas = intent.getStringExtra("numeroPersonas")
        val fechaReserva = intent.getStringExtra("fechaReserva")
        val horarioReserva = intent.getStringExtra("horarioReserva")

        textViewNumeroPersonas.text = "Número de personas: $numeroPersonas"
        textViewFechaReserva.text = "Fecha de reserva: $fechaReserva"
        textViewHorarioReserva.text = "Horario de reserva: $horarioReserva"
    }
}
