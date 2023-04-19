package com.example.tfg_dam2

import com.google.firebase.firestore.FirebaseFirestore

class Firestore {

    // Variables
    private val db = FirebaseFirestore.getInstance()
    private val users = db.collection("users")

    //Constructores
    constructor()

    //Funciones

    fun agregarUsuario(email : String, nombre : String, password : String){
        val documento = users.document(email)
        documento.get().addOnCompleteListener{task ->
            if (task.isSuccessful){
                val documentComprueba = task.result
                if (documentComprueba != null && documentComprueba.exists()) {

                }else{
                    var hM = hashMapOf<String, String>(
                        "email" to email,
                        "nombre" to nombre,
                        "password" to password)

                    users.document(email).set(hM)
                }
            }else{
                //No es sucesful
            }
        }
    }


    fun comprobarUsuario(email : String){
        val documento = users.document(email)
        documento.get().addOnCompleteListener{task ->
            if (task.isSuccessful){
                val documentComprueba = task.result
                if (documentComprueba != null && documentComprueba.exists()) {

                }else{
                    var hM = hashMapOf<String, String>(
                        "email" to email,
                        )

                    users.document(email).set(hM)
                }
            }else{
                //No es sucesful
            }
        }
    }
}