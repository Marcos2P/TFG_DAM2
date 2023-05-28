package com.example.tfg_dam2.clases_alternativas

import java.io.Serializable

data class FirebaseData(
    val edadMascota : String,
    val email: String,
    val fotoUrl: String,
    val nombreUser : String,
    val nombreMascota: String,
    val  passwordUser : String,
    val pesoMascota : String,
    val razaMascota : String,
): Serializable