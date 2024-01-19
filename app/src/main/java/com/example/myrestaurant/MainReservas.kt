// ReservasActivity.kt

package com.example.myrestaurant

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity

class MainReservas : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val buttonMostrarPicker: Button = findViewById(R.id.buttonMostrarPicker)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)

        buttonMostrarPicker.setOnClickListener {
            showDateTimePickers()
        }
    }

    private fun showDateTimePickers() {
        datePicker.visibility = View.VISIBLE
        timePicker.visibility = View.VISIBLE
    }
}
