package com.example.tfg_dam2.registro_login


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.tfg_dam2.clases_alternativas.Firestore
import com.example.tfg_dam2.R
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    // Referenciar Firestore
    val fire : Firestore = Firestore()
    var context: Context = this


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //  Vistas
        val loguearse = findViewById<Button>(R.id.loguearse)
        val emailLog = findViewById<EditText>(R.id.addresLogin)
        val password = findViewById<EditText>(R.id.logPass)
        val linkRegister = findViewById<TextView>(R.id.linkRegister)

        loguearse.setOnClickListener {
            fire.loguerarUsuario(emailLog.text.toString(), password.text.toString(), context)
        }

        linkRegister.setOnClickListener {
            var cambio = Intent(this, RegisterActivity::class.java)
            startActivity(cambio)
        }


    }

    fun showError2(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.Login), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}