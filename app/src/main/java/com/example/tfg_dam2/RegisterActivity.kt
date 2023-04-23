package com.example.tfg_dam2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity() {

    val fire : Firestore = Firestore()
    var context: Context = this
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Constantes de programa
        val email = findViewById<EditText>(R.id.emailRegister)
        val nombre = findViewById<EditText>(R.id.nameRegister)
        val password = findViewById<EditText>(R.id.passwordRegister)
        val password2 = findViewById<EditText>(R.id.password2Register)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btngoLogin = findViewById<Button>(R.id.btnGoToLogin)


        btnRegister.setOnClickListener {
            if (email.text.toString().isNotEmpty() && nombre.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() && password2.text.toString().isNotEmpty()){
                if (comprobarEmail(email) && comprobarPassword(password) && comprobarPasswordIguales(password, password2)){
                    fire.agregarUsuario(email.text.toString(), nombre.text.toString(), password.text.toString(), context)
                }
            }else{
                showError("Rellene todos los campos")
            }
        }


        btngoLogin.setOnClickListener {
            val cambio = Intent(this, LoginActivity::class.java)
            startActivity(cambio)
        }
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

    fun comprobarPasswordIguales(p1 : EditText, p2 : EditText):Boolean{
        return if(p1.text.toString()==p2.text.toString()){
            true
        }else{
            showError("Las contraseñas no coinciden")
            false
        }
    }

    fun showError(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.Register), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }


}