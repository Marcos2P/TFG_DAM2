package com.example.tfg_dam2.clases_alternativas

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.tfg_dam2.registro_login.RegisterActivity
import com.example.tfg_dam2.registro_login.RegisterPetActivity
import com.google.firebase.firestore.FirebaseFirestore

class Firestore {

    // Variables
    private val db = FirebaseFirestore.getInstance()
    private val users = db.collection("users")

    //Constructores
    constructor()

    //Funciones

    fun agregarUsuario(email : String, nombre : String, password : String, context: Context){
        val documento = users.document(email)
        documento.get().addOnCompleteListener{task ->
            if (task.isSuccessful){
                val documentComprueba = task.result
                if (documentComprueba != null && documentComprueba.exists()) {
                    Toast.makeText(context, "Ya existe", Toast.LENGTH_SHORT).show()
                }else{
                    var hM = hashMapOf<String, String>(
                        "email" to email,
                        "nombre" to nombre,
                        "password" to password)
                    users.document(email).set(hM)
                    val cambio = Intent(context, RegisterPetActivity::class.java)
                    cambio.putExtra("email", email)
                    startActivity(context, cambio, null)
                }
            }else{
                //No es sucesful
            }
        }
    }


    fun loguerarUsuario(email: String, password: String, context: Context){
        val documento = users.document(email)
        documento.get().addOnCompleteListener{task ->
            if (task.isSuccessful){
                val documentComprueba = task.result
                if (documentComprueba != null && documentComprueba.exists()) {
                    if(password == documentComprueba.data?.get("nombre").toString()){
                        val cambio = Intent(context, RegisterActivity::class.java)
                        startActivity(context, cambio, null)
                    }else{
                        Toast.makeText(context, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, "No existe esta cuenta", Toast.LENGTH_SHORT).show()
                }
            }else{
                //No es sucesful
            }
        }
    }

}
