package com.example.myrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button;
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.security.Provider

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Setup
        setup()
    }
    private fun setup (){
        title = "Autenticación"

        var signUpButton = findViewById<Button>(R.id.signUpButton)
        var emailEditText = findViewById<EditText>(R.id.emailEditText)
        var passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        var loginButton = findViewById<Button>(R.id.loginButton)

        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showSuccessMessage("El usuario se registró correctamente")
                        } else {
                            val errorMessage = task.exception?.message
                            if (errorMessage?.contains("already in use") == true) {
                                // Usuario ya está registrado
                                showErrorMessage("El usuario ya existe")
                            } else {
                                // Otro error
                                showAlert()
                            }
                        }
                    }
            }
        }

        loginButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    ).addOnCompleteListener() {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Credenciales Erróneas")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
            val homeIntent = Intent (this,PrincipalActivity::class.java).apply {
                putExtra("Email", email)
                putExtra("provider", provider.name)
            }
        startActivity(homeIntent)
    }
    private fun showSuccessMessage(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registro exitoso")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showErrorMessage(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}