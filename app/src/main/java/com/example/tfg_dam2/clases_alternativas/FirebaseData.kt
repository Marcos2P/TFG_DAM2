package com.example.tfg_dam2.clases_alternativas

import java.io.Serializable

data class FirebaseData(
    val email: String,
    val nombreMascota: String,
    val fotoUrl: String
): Serializable