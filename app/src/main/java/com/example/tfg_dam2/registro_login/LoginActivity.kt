package com.example.tfg_dam2.registro_login


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.tfg_dam2.clases_alternativas.Firestore
import com.example.tfg_dam2.R
import com.example.tfg_dam2.actividades_principales.FirebaseViewModel
import com.example.tfg_dam2.clases_alternativas.FirebaseData
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    // Referenciar Firestore
    val fire : Firestore = Firestore()
    var context: Context = this
    private lateinit var firebaseViewModel: FirebaseViewModel
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
            if (emailLog.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                if (comprobarEmail(emailLog) && comprobarPassword(password)){
                    firebaseViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
                    fire.loguerarUsuario(emailLog.text.toString(), password.text.toString(), context, firebaseViewModel)

                }
            }else{
                showError("Rellene todos los campos")
            }
        }

        linkRegister.setOnClickListener {
            var cambio = Intent(this, RegisterActivity::class.java)
            startActivity(cambio)
        }


    }

    fun showError(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.Login), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    fun comprobarEmail(email : EditText): Boolean{
        return if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            true
        }else{
            showError("Email incorrecto")
            false
        }
    }


    fun comprobarPassword(password : EditText):Boolean{
        return if(password.text.toString().length>=6){
            true
        }else{
            showError("La contraseña debe tener al menos 6 caracteres")
            false
        }
    }
}