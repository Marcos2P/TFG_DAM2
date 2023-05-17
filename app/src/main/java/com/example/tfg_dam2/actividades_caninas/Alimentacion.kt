package com.example.tfg_dam2.actividades_caninas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tfg_dam2.R
import com.example.tfg_dam2.actividades_principales.PrincipalActivity

class Alimentacion : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alimentacion)

        val btnVolverAlimentacion = findViewById<Button>(R.id.btnVolverAlimentacion)
        btnVolverAlimentacion.setOnClickListener(){
            val intent=Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }
    }
}