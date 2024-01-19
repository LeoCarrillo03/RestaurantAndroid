package com.example.myrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button;


class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val BtnMenu = findViewById<Button>(R.id.btnMenu)
        val BtnReservas : Button = findViewById(R.id.butAmbiente)
        BtnReservas.setOnClickListener {
            // Abrir la actividad de reservas cuando se hace clic en el botón
            val intent = Intent(this, MainReservas::class.java)
            startActivity(intent)
        }

        BtnMenu.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
    }
}