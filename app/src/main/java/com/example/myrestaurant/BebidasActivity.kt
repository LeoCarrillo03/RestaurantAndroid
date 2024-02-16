package com.example.myrestaurant

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BebidasActivity : AppCompatActivity() {

    private val selectedBebidas = mutableListOf<Bebida>() // Reemplazar con tu tipo de datos específico para las bebidas
    private lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bebidas)

        totalTextView = findViewById(R.id.totalPriceTextView1)

        // Resto de tu código...

        val btnAdd1: Button = findViewById(R.id.btnAdd1)
        btnAdd1.setOnClickListener {
            val bebida = Bebida("Pisco Sour", 28.00)
            agregarBebida(bebida)
        }

        val btnAdd2: Button = findViewById(R.id.btnAdd2)
        btnAdd2.setOnClickListener {
            val bebida = Bebida("Maracuyá Sour", 18.00)
            agregarBebida(bebida)
        }

        val btnAdd3: Button = findViewById(R.id.btnAdd3)
        btnAdd3.setOnClickListener {
            val bebida = Bebida("Piña Colada", 25.00)
            agregarBebida(bebida)
        }

        val btnAdd4: Button = findViewById(R.id.btnAdd4)
        btnAdd4.setOnClickListener {
            val bebida = Bebida("Chicha Morada", 10.00)
            agregarBebida(bebida)
        }

        val btnAdd5: Button = findViewById(R.id.btnAdd5)
        btnAdd5.setOnClickListener {
            val bebida = Bebida("Inka Kola", 4.00)
            agregarBebida(bebida)
        }

        val btnAdd6: Button = findViewById(R.id.btnAdd6)
        btnAdd6.setOnClickListener {
            val bebida = Bebida("Cerveza Cusquea", 15.00)
            agregarBebida(bebida)
        }

    }

    private lateinit var resumenDialog: Dialog

    private fun mostrarResumenDialog() {
        resumenDialog = Dialog(this)
        resumenDialog.setContentView(R.layout.activity_bebidas)

        val summaryTextView: TextView = resumenDialog.findViewById(R.id.summaryTextView)
        val closeButton: Button = resumenDialog.findViewById(R.id.closeButton)

        // Generar el resumen de la boleta
        val summaryText = generarTextoResumen()
        summaryTextView.text = summaryText

        // Configurar el clic del botón Cerrar
        closeButton.setOnClickListener {
            resumenDialog.dismiss()
        }

        // Mostrar el cuadro de diálogo
        resumenDialog.show()
    }

    private fun generarTextoResumen(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Resumen de la Boleta:\n\n")

        for (bebida in selectedBebidas) {
            stringBuilder.append("${bebida.nombre} - S/ ${bebida.precio}\n")
        }

        val total = calcularTotal()
        stringBuilder.append("\nTotal a Pagar: S/ $total")

        return stringBuilder.toString()
    }

    private fun agregarBebida(bebida: Bebida) {
        selectedBebidas.add(bebida)
        actualizarTotal()
    }

    private fun actualizarTotal() {
        val total = calcularTotal()
        totalTextView.text = "Total: S/ $total"
    }

    private fun calcularTotal(): Double {
        var total = 0.0
        for (bebida in selectedBebidas) {
            total += bebida.precio
        }
        return total
    }
}
