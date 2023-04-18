package com.example.tfg_dam2

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity() {
    // Referenciar Firestore
    private val db = FirebaseFirestore.getInstance()
    private val users = db.collection("users")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Constantes de programa
        var email = findViewById<EditText>(R.id.emailRegister)
        var nombre = findViewById<EditText>(R.id.nameRegister)
        var password = findViewById<EditText>(R.id.passwordRegister)
        var password2 = findViewById<EditText>(R.id.password2Register)
        var btnRegister = findViewById<Button>(R.id.btnRegister)
        var btnLogin = findViewById<Button>(R.id.btnGoToLogin)


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
                                var hM = hashMapOf<String, String>("email" to email.text.toString(),
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


        //Comprobar que funcionan las funciones, SERA BORRADO
        /*if (comprobarEmail(email))
            Toast.makeText(this, "Siiii", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "NOOOOOOO", Toast.LENGTH_LONG).show()*/
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
            showError("Las Contraseñas no coinciden")
            false
        }
    }

    fun showError(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.Register), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }


}