package com.example.tfg_dam2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    // Referenciar Firestore
   val fire : Firestore = Firestore()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //  Vistas
        val loguearse = findViewById<Button>(R.id.loguearse)
        val emailLog = findViewById<EditText>(R.id.addresLogin)
        val password = findViewById<EditText>(R.id.logPass)


        loguearse.setOnClickListener {
            if (emailLog.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                fire.comprobarUsuario(emailLog.text.toString())
            }else{
                showError2("Rellene los campos para continuar")
            }
        }
    }

    fun showError2(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.Login), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}