// MainReservas.kt

package com.example.myrestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity

class MainReservas : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var editTextNumeroPersonas: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val buttonMostrarPicker: Button = findViewById(R.id.buttonMostrarPicker)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)
        editTextNumeroPersonas = findViewById(R.id.editTextNumeroPersonas)

        buttonMostrarPicker.setOnClickListener {
            showDateTimePickers()
            saveReservationData()
        }
    }

    private fun showDateTimePickers() {
        datePicker.visibility = View.VISIBLE
        timePicker.visibility = View.VISIBLE
    }

    @SuppressLint("NewApi")
    private fun saveReservationData() {
        val numeroPersonas = editTextNumeroPersonas.text.toString()
        val fechaReserva = "${datePicker.dayOfMonth}/${datePicker.month + 1}/${datePicker.year}"
        val horarioReserva = "${timePicker.hour}:${timePicker.minute}"

        val intent = Intent(this, AdminActivity::class.java).apply {
            putExtra("numeroPersonas", numeroPersonas)
            putExtra("fechaReserva", fechaReserva)
            putExtra("horarioReserva", horarioReserva)
        }
        startActivity(intent)
    }
}
