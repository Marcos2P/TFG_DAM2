package com.example.tfg_dam2.clases_alternativas

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tfg_dam2.actividades_principales.PrincipalActivity
import com.example.tfg_dam2.registro_login.LoginActivity
import com.example.tfg_dam2.registro_login.RegisterActivity
import com.example.tfg_dam2.registro_login.RegisterPetActivity
import com.google.firebase.firestore.FirebaseFirestore

class Firestore {

    // Variables
    private val db = FirebaseFirestore.getInstance()
    private val users = db.collection("users")
    lateinit var firebaseData: FirebaseData

    //Constructores
    constructor()

    //Funciones

    fun agregarUsuario(email : String, nombre : String, password : String, context: Context, ){
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
                Toast.makeText(context, "No successful", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun loguerarUsuario(email: String, password: String, context: Context){
        val documento = users.document(email)
        documento.get().addOnCompleteListener{task ->
            if (task.isSuccessful){
                val documentComprueba = task.result
                if (documentComprueba != null && documentComprueba.exists()) {
                    if(password == documentComprueba.data?.get("password").toString()){
                        //Meter a FirebaseData
                        val edadMascota = documentComprueba.getString("edad_mascota")
                        val emailUser = documentComprueba.getString("email")
                        val fotoMascota = documentComprueba.getString("foto_Mascota")
                        val nombreUser = documentComprueba.getString("nombre")
                        val nombreMascota = documentComprueba.getString("nombre_mascota")
                        val passwordUser = documentComprueba.getString("password")
                        val pesoMascota = documentComprueba.getString("peso_mascota")
                        val razaMascota = documentComprueba.getString("raza_mascota")
                        firebaseData = FirebaseData(edadMascota!!,emailUser!!, fotoMascota!!, nombreUser!!, nombreMascota!!,  passwordUser!!, pesoMascota!!, razaMascota!!,)
                        //Cambiar de actividad
                        val cambio = Intent(context, PrincipalActivity::class.java)
                        cambio.putExtra("firebaseData", firebaseData)
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

    fun loguearMascota(email: String, nombre: String, peso: Float, edad: Int, raza : String, urlMascota : Uri, context: Context){
        val documento = users.document(email)
        documento.get().addOnCompleteListener{task ->
            if (task.isSuccessful){
                val documentComprueba = task.result
                if (documentComprueba != null && documentComprueba.exists()) {
                    var hM = hashMapOf<String, String>(
                        "nombre_mascota" to nombre,
                        "peso_mascota" to peso.toString(),
                        "edad_mascota" to edad.toString(),
                        "raza_mascota" to raza,
                        "foto_Mascota" to urlMascota.toString())
                    users.document(email).update(hM as Map<String, Any>)
                    val cambio = Intent(context, LoginActivity::class.java)
                    cambio.putExtra("email", email)
                    startActivity(context, cambio, null)
                }else{

                }
            }else{
                //No es sucesful
            }
        }
    }

}
