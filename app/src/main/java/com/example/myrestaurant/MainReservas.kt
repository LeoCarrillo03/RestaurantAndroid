// MainReservas.kt

package com.example.myrestaurant

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.database.FirebaseDatabase

class MainReservas : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var editTextNumeroPersonas: EditText

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val buttonMostrarPicker: Button = findViewById(R.id.btnReservar)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)
        editTextNumeroPersonas = findViewById(R.id.NumPersonasRe)

        buttonMostrarPicker.setOnClickListener {
            showDateTimePickers()
            saveReservationData()
        }
    }

    private fun showDateTimePickers() {
        datePicker.visibility = View.VISIBLE
        timePicker.visibility = View.VISIBLE
    }


    private fun saveReservationData() {
        val numeroPersonas = editTextNumeroPersonas.text.toString()
        val fechaReserva = "${datePicker.dayOfMonth}/${datePicker.month + 1}/${datePicker.year}"
        val horarioReserva = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            "${timePicker.hour}:${timePicker.minute}"
        } else {
            TODO("VERSION.SDK_INT < M")
        }

        // Guardar datos a Firebase
        val database = FirebaseDatabase.getInstance("https://projectrestaurant-1abd4-default-rtdb.firebaseio.com/")
        val referenciaReservas = database.getReference("reservas")

        val nuevaReserva = referenciaReservas.push()

        nuevaReserva.child("numPersonas").setValue(numeroPersonas)
        nuevaReserva.child("fecha").setValue(fechaReserva)
        nuevaReserva.child("horario").setValue(horarioReserva)
            .addOnSuccessListener {
                Toast.makeText(this, "Reserva realizada con éxito", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al realizar la reseva", Toast.LENGTH_SHORT).show()
            }
    }
}
