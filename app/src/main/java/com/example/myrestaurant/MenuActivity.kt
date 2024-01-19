package com.example.myrestaurant

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private val selectedItems = mutableListOf<com.example.myrestaurant.MenuItem>() // Usar el tipo específico de tu MenuItem
    private lateinit var totalPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        totalPriceTextView = findViewById(R.id.totalPriceTextView)

        // Resto de tu código...

        val btnAdd1: Button = findViewById(R.id.btnAdd1)
        btnAdd1.setOnClickListener {
            val item = com.example.myrestaurant.MenuItem("Pollo Doradito + Sopa", "Delicioso pollo a la braza, papas fritas con cremas + una sopa de verduras con fideo", 30.00)
            addItemToCart(item)
        }

        val btnAdd7: Button = findViewById(R.id.btnAdd2)
        btnAdd7.setOnClickListener {
            val item1 = com.example.myrestaurant.MenuItem("Costillar de Cordero", "La costilla de cordero es una pieza que se corresponde con la parte inferior del lomo", 25.00)
            addItemToCart(item1)
        }

        val btnAdd3: Button = findViewById(R.id.btnAdd3)
        btnAdd3.setOnClickListener {
            val item2 = com.example.myrestaurant.MenuItem("Arroz con pato norteño", "Un plato que consta de arroz, verduras, hojas de culantro, piezas de pato", 35.00)
            addItemToCart(item2)
        }

        val btnAdd4: Button = findViewById(R.id.btnAdd4)
        btnAdd4.setOnClickListener {
            val item3 = com.example.myrestaurant.MenuItem("Seco de Cabrito", "Delicioso pollo a la braza, papas fritas con cremas + una sopa de verduras con fideo", 35.00)
            addItemToCart(item3)
        }

        val btnAdd6: Button = findViewById(R.id.btnAdd5)
        btnAdd6.setOnClickListener {
            val item4 = com.example.myrestaurant.MenuItem("Pastas con Mote", "Variedad de caldo se prepara con mote que lleva trozos de carne de res, cerdo o cabrito", 15.00)
            addItemToCart(item4)
        }

        val btnAdd2: Button = findViewById(R.id.btnAdd6)
        btnAdd2.setOnClickListener {
            val item5 = com.example.myrestaurant.MenuItem("Shambar", "Una suculenta sopa que incluye todo tipo de legumbres y carnes", 20.00)
            addItemToCart(item5)
        }

        val btnPagar: Button = findViewById(R.id.button2)
        btnPagar.setOnClickListener {
            showSummaryDialog()
        }
    }

    private lateinit var summaryDialog: Dialog

    private fun showSummaryDialog() {
        summaryDialog = Dialog(this)
        summaryDialog.setContentView(R.layout.summary_layout)

        val summaryTextView: TextView = summaryDialog.findViewById(R.id.summaryTextView)
        val closeButton: Button = summaryDialog.findViewById(R.id.closeButton)

        // Generar el resumen de la boleta
        val summaryText = generateSummaryText()
        summaryTextView.text = summaryText

        // Configurar el clic del botn Cerrar
        closeButton.setOnClickListener {
            summaryDialog.dismiss()
        }

        // Mostrar el cuadro de dilogo
        summaryDialog.show()
    }

    private fun generateSummaryText(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Resumen de la Boleta:\n\n")

        for (item in selectedItems) {
            stringBuilder.append("${item.name} - S/ ${item.price}\n")
        }

        val totalPrice = calculateTotalPrice()
        stringBuilder.append("\nTotal a Pagar: S/ $totalPrice")

        return stringBuilder.toString()
    }

    private fun addItemToCart(item: com.example.myrestaurant.MenuItem) {
        selectedItems.add(item)
        updateTotalPrice()
    }

    private fun showSummary() {
        val totalPrice = calculateTotalPrice()
        val summary = "Total: S/ $totalPrice"
        totalPriceTextView.text = summary
    }

    private fun calculateTotalPrice(): Double {
        var total = 0.0
        for (item in selectedItems) {
            total += item.price
        }
        return total
    }

    private fun updateTotalPrice() {
        val totalPrice = calculateTotalPrice()
        val totalPriceText = "Total: S/ $totalPrice"
        totalPriceTextView.text = totalPriceText
    }
}
