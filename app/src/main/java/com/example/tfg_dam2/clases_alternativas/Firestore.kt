package com.example.tfg_dam2.clases_alternativas

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.tfg_dam2.actividades_principales.PrincipalActivity
import com.example.tfg_dam2.registro_login.LoginActivity
import com.example.tfg_dam2.registro_login.RegisterPetActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import java.util.Calendar
import java.util.Date


class Firestore {

    // Variables
    private val db = FirebaseFirestore.getInstance()
    private val users = db.collection("users")
    lateinit var firebaseData: FirebaseData

    //Constructores
    constructor()

    //Funciones

    fun agregarUsuario(email : String, nombre : String, password : String, context: Context, ){
        var calendar  = Calendar.getInstance()
        var fecha = calendar.time
        val listaHorariosVeterinario = ArrayList<String>()
        val listaHorariosPeluqueria= ArrayList<String>()
        val listaHorariosDeporte = ArrayList<String>()
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
                    var hM2 = hashMapOf<String, Boolean>(
                        "Train1" to false,
                        "Train2" to false,
                        "Train3" to false
                        )
                    var hm3 = hashMapOf<String, String>(
                        "Paseo1" to "red",
                        "Paseo2" to "red",
                        "Paseo3" to "red"
                    )
                    var hm4= hashMapOf(
                        "Fecha" to fecha
                    )
                    var hm5 = hashMapOf<String,
                            kotlin.collections.ArrayList<String>>(
                        "fechasVeterinario" to listaHorariosVeterinario,
                        "fechasPeluqueria" to listaHorariosPeluqueria,
                        "fechasDeporte" to listaHorariosDeporte
                    )
                    users.document(email).set(hM)
                    users.document(email).update(hM2 as Map<String, Any>)
                    users.document(email).update(hm3 as Map<String, String>)
                    users.document(email).update(hm4 as Map<String, Date>)
                    users.document(email).update(hm5 as Map<String,
                            kotlin.collections.ArrayList<String>>)
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
                        val train1 = documentComprueba.getBoolean("Train1")
                        val train2 = documentComprueba.getBoolean("Train2")
                        val train3 = documentComprueba.getBoolean("Train3")
                        val paseo1 = documentComprueba.getString("Paseo1")
                        val paseo2 = documentComprueba.getString("Paseo2")
                        val paseo3 = documentComprueba.getString("Paseo3")
                        var horario = documentComprueba.getDate("Fecha")
                        var fechaPelu = documentComprueba.get("fechasPeluqueria") as ArrayList<String>
                        var fechaVeterinario = documentComprueba.get("fechasVeterinario") as ArrayList<String>
                        var fechasDeporte = documentComprueba.get("fechasDeporte")as ArrayList<String>
                        firebaseData = FirebaseData(edadMascota!!,emailUser!!, fotoMascota!!, nombreUser!!,
                            nombreMascota!!,  passwordUser!!, pesoMascota!!, razaMascota!!,
                            train1!!, train2!!, train3!!, paseo1!!, paseo2!!, paseo3!!,
                            horario!!, fechaPelu, fechaVeterinario, fechasDeporte
                        )
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

    fun changeEmail(emailViejo: String, emailNuevo : String){
        val documento = users.document(emailViejo)

        // Primero
        val actualizacion = HashMap<String,Any>()
        actualizacion["email"] = emailNuevo
        documento.update(actualizacion)

        //Segundo
        documento.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()){
                val datos = documentSnapshot.data

                val documentNew = users.document(emailNuevo)
                documentNew.set(datos!!).addOnSuccessListener {
                    documento.delete().addOnSuccessListener {

                    }.addOnFailureListener{

                    }
                }
            }
        }
    }

    fun changePassword(email : String, password: String){
        val documento = users.document(email)

        val actualizacion = HashMap<String,Any>()
        actualizacion["password"] = password
        documento.update(actualizacion)
    }

    fun changePeso(email : String, peso : String){
        val documento = users.document(email)

        val actualizacion = HashMap<String,Any>()
        actualizacion["peso_mascota"] = peso
        documento.update(actualizacion)
    }

    fun changeEdad(email : String, edad : String){
        val documento = users.document(email)

        val actualizacion = HashMap<String,Any>()
        actualizacion["edad_mascota"] = edad
        documento.update(actualizacion)
    }

    fun changeEstado(email: String, flag : Boolean, number: Int){
        val documento = users.document(email)

        val actualizacion = HashMap<String,Any>()
        actualizacion["Train"+number] = flag
        documento.update(actualizacion)
    }

    fun cambiarColor(email: String, color: String, number: Int){
        val documento = users.document(email)

        val actualizacion = HashMap<String,Any>()
        actualizacion["Paseo"+number] = color
        documento.update(actualizacion)
    }

    fun cambiaFecha(email: String, fecha : Date){
        val documento = users.document(email)

        val actualizacion = HashMap<String,Date>()
        actualizacion["Fecha"] = fecha
        documento.update(actualizacion as Map<String, Any>)
    }

    fun añadirPelu(email: String, date : String){
        val documento = users.document(email)
        documento.update("fechasPeluqueria", FieldValue.arrayUnion(date))
    }
    fun añadirVeterinario(email: String, date : String){
        val documento = users.document(email)
        documento.update("fechasVeterinario", FieldValue.arrayUnion(date))
    }
    fun añadirDeporte(email: String, date : String){
        val documento = users.document(email)
        documento.update("fechasDeporte", FieldValue.arrayUnion(date))
    }

}
