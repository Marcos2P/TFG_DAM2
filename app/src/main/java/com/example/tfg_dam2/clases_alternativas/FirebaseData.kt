package com.example.tfg_dam2.clases_alternativas

import java.io.Serializable
import java.util.Date

data class FirebaseData(
    var edadMascota: String,
    var email: String,
    var fotoUrl: String,
    var nombreUser: String,
    var nombreMascota: String,
    var passwordUser: String,
    var pesoMascota: String,
    var razaMascota: String,
    var train1: Boolean,
    var train2: Boolean,
    var train3: Boolean,
    var paseo1: String,
    var paseo2: String,
    var paseo3: String,
    var fecha: Date,
    var fechaPelu : ArrayList<String>,
    var fechaVeterinario : ArrayList<String>,
    var fechaDeporte: ArrayList<String>
): Serializable