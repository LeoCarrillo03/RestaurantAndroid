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

    private lateinit var editTextFecha: EditText
    private lateinit var editTextHorario: EditText
    private lateinit var editspinnerMesas: EditText
    private lateinit var editTextNumeroPersonas: EditText

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val buttonMostrarPicker: Button = findViewById(R.id.btnReservar)
        editTextNumeroPersonas = findViewById(R.id.NumPersonasRe)
        editspinnerMesas = findViewById(R.id.TextMesas)
        editTextFecha = findViewById(R.id.editTextFecha)
        editTextHorario = findViewById(R.id.editTextHorario)

        buttonMostrarPicker.setOnClickListener {
            saveReservationData() // Guardar los datos de la reserva
        }
    }

    private fun saveReservationData() {
        val numeroPersonas = editTextNumeroPersonas.text.toString()
        val TextMesas = editspinnerMesas.text.toString()
        val fechaReserva = editTextFecha.text.toString()
        val horarioReserva = editTextHorario.text.toString()

        // Verificar que se hayan ingresado todos los datos
        if (numeroPersonas.isNotBlank() && fechaReserva.isNotBlank() && horarioReserva.isNotBlank()) {
            // Guardar datos a Firebase
            val database = FirebaseDatabase.getInstance("https://projectrestaurant-1abd4-default-rtdb.firebaseio.com/")
            val referenciaReservas = database.getReference("reservas")

            val nuevaReserva = referenciaReservas.push()

            nuevaReserva.child("numPersonas").setValue(numeroPersonas)
            nuevaReserva.child("fecha").setValue(fechaReserva)
            nuevaReserva.child("horario").setValue(horarioReserva)
            nuevaReserva.child("mesa").setValue(TextMesas)

                .addOnSuccessListener {
                    Toast.makeText(this, "Reserva realizada con éxito", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al realizar la reserva", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}
