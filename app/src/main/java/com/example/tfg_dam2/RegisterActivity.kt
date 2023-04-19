package com.example.tfg_dam2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity() {
    // Referenciar Firestore
    private val db = FirebaseFirestore.getInstance()
    private val users = db.collection("users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Constantes de programa
        val email = findViewById<EditText>(R.id.emailLogin)
        val nombre = findViewById<EditText>(R.id.nameRegister)
        val password = findViewById<EditText>(R.id.passwordLogin)
        val password2 = findViewById<EditText>(R.id.password2Register)
        val btnRegister = findViewById<Button>(R.id.btnLogin)
        val btnLogin = findViewById<Button>(R.id.btnGoToLogin)


        btnRegister.setOnClickListener {
            if (email.text.toString().isNotEmpty() && nombre.text.toString().isNotEmpty() && password.text.toString().isNotEmpty() && password2.text.toString().isNotEmpty()){
                if (comprobarEmail(email) && comprobarPassword(password) && comprobarPasswordIguales(password, password2)){
                    val documento = users.document(email.text.toString())
                    documento.get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val documentComprueba = task.result
                            if (documentComprueba != null && documentComprueba.exists()) {
                                showError("Este correo ya esta en uso")
                            } else {
                                var hM = hashMapOf<String, String>(
                                    "email" to email.text.toString(),
                                    "nombre" to nombre.text.toString(),
                                    "password" to password.text.toString())

                                users.document(email.text.toString()).set(hM)
                                val cambio = Intent(this, LoginActivity::class.java)
                                cambio.putExtra("datos", hM)
                                startActivity(cambio)
                            }
                        } else {
                            showError("Error, por favor contacte con nosotros")
                        }
                    }
                }
            }else{
                showError("Rellene todos los campos")
            }
        }


        btnLogin.setOnClickListener {
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