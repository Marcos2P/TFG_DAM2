package com.example.tfg_dam2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore


class LoginActivity : AppCompatActivity() {
    // Referenciar Firestore
   val fire : Firestore = Firestore()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //  Vistas
        val loguearse = findViewById<Button>(R.id.loguearse)
        val emailLog = findViewById<EditText>(R.id.addresLogin)
        val password = findViewById<EditText>(R.id.logPass)


        loguearse.setOnClickListener {
            db.collection("users").document(emailLog.text.toString()).get().addOnSuccessListener { document ->
                if (document.exists())
                    //Comprobar contraseña
                else
                    Toast.makeText(this, "No existe", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun showError2(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.Login), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}