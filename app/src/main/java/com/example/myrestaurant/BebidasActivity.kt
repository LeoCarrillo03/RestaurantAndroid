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

        // Agrega más botones según tus bebidas...

        val btnPagar: Button = findViewById(R.id.button2)
        btnPagar.setOnClickListener {
            mostrarResumenDialog()
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
