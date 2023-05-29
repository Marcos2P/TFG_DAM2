package com.example.tfg_dam2.clases_alternativas

import java.io.Serializable

data class FirebaseData(
    var edadMascota : String,
    var email: String,
    var fotoUrl: String,
    var nombreUser : String,
    var nombreMascota: String,
    var  passwordUser : String,
    var pesoMascota : String,
    var razaMascota : String,
    var train1 : Boolean,
    var train2 : Boolean,
    var train3 : Boolean,
): Serializable