package com.example.tfg_dam2.actividades_caninas

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tfg_dam2.R
import com.example.tfg_dam2.actividades_principales.PrincipalActivity
import com.example.tfg_dam2.clases_alternativas.FirebaseData
import org.w3c.dom.Text

class Alimentacion : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alimentacion)
        val firebaseData = intent.getSerializableExtra("firebaseData") as FirebaseData

        val razaAlimento = findViewById<TextView>(R.id.razaAlimento)
        razaAlimento.text = firebaseData.razaMascota+" "

        val edadAlimento = findViewById<TextView>(R.id.edadAlimento)
        if(firebaseData.edadMascota>"2"){
            edadAlimento.text="adulto"
        }else{
            edadAlimento.text="cachorro"
        }

        val pesoAlimento = findViewById<TextView>(R.id.pesoAlimento)
        pesoAlimento.text = firebaseData.pesoMascota+" gramos"

        var comidauno = findViewById<ImageView>(R.id.comidauno)
        var comidados = findViewById<ImageView>(R.id.comidados)
        var url : String? = null
        var contextUno = ""
        var contextDos = ""


        when(firebaseData.razaMascota){
            "Pastor Aleman" -> if (edadAlimento.text=="cachorro"){
                comidauno.setImageResource(R.drawable.comidapastoruno)
                comidados.setImageResource(R.drawable.comidapastordos)
                contextUno = "https://www.tiendanimal.es/royal-canin-adult-5-pastor-aleman-pienso-para-perros-/ROY1340800_M.html"
                contextDos = "https://www.tiendanimal.es/criadores-pollo-y-arroz-pienso-para-cachorros-de-razas-grandes/CRD1129_M.html"
            }else{
                comidauno.setImageResource(R.drawable.comidapastortres)
                comidados.setImageResource(R.drawable.comidapastorcuatro)
                contextUno = "https://www.tiendanimal.es/royal-canin-adult-pastor-aleman-pienso-para-perros/ROY146464_M.html"
                contextDos = "https://www.tiendanimal.es/affinity-advance-pastor-aleman-adult-pienso-para-perros/ADV520410_M.html"
            }
            "BullDog" -> if (edadAlimento.text=="cachorro"){
                comidauno.setImageResource(R.drawable.comidabulldoguno)
                comidados.setImageResource(R.drawable.comidabulldogdos)
                contextUno = "https://www.tiendanimal.es/royal-canin-french-bulldog-adult-pienso-para-perros/ROY159868_M.html"
                contextDos = "https://www.tiendanimal.es/criadores-mini-pollo-y-arroz-en-pate-para-cachorros/CRD43972_M.html"
            }else{
                comidauno.setImageResource(R.drawable.comidabulldogtres)
                comidados.setImageResource(R.drawable.comidabulldogcuatro)
                contextUno = "https://www.tiendanimal.es/affinity-advance-bulldog-frances-active-defense-pato-y-arroz-pienso-para-perros/ADV500749_M.html"
                contextDos = "https://crazypet.es/royal-canin-perros-adultos/100271-royal-canin-bulldog-ingles-adult.html?gad=1&gclid=Cj0KCQjw98ujBhCgARIsAD7QeAjevKO1B9BvF3dQZbhAfQUiEncgas6ITQWT3M6OjQd-DKQrS3UXgsMaAofCEALw_wcB"
            }
            "Rottweiler" -> if (edadAlimento.text=="cachorro"){
                comidauno.setImageResource(R.drawable.comidarotuno)
                comidados.setImageResource(R.drawable.comidarotdos)
                contextUno = "https://www.tiendanimal.es/royal-canin-puppy-rottweiler-pienso-para-perros-/ROY146964_M.html"
                contextDos = "https://www.tiendanimal.es/acana-puppy-large-breed-pienso-para-perros/ACAAPL13_M.html"
            }else{
                comidauno.setImageResource(R.drawable.comidarottres)
                comidados.setImageResource(R.drawable.comidarotcuatro)
                contextUno = "https://www.tiendanimal.es/royal-canin-adult-rottweiler-pienso-para-perros/ROY146864_M.html"
                contextDos = "https://www.tiendanimal.es/eukanuba-adult-rottweiler-pienso-para-perros/EUK81378312_M.html"
            }
        }

        comidauno.setOnClickListener(){
            url = contextUno
        }
        comidados.setOnClickListener(){
            url = contextDos
        }

        var btnBuyFood = findViewById<Button>(R.id.btnBuyFood)
        btnBuyFood.setOnClickListener() {
            if (url.isNullOrEmpty()) {
                Toast.makeText(this, "Primero selecciona comida para comprar", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }


        val pesoAlimentoKg = findViewById<TextView>(R.id.pesoAlimentokg)
        var num = firebaseData.pesoMascota.substringBefore(".")
        pesoAlimentoKg.text = (num.toInt()*2/100).toString()



        val btnVolverAlimentacion = findViewById<Button>(R.id.btnVolverAlimentacion)
        btnVolverAlimentacion.setOnClickListener(){
            val intent=Intent(this, PrincipalActivity::class.java)
            intent.putExtra("firebaseData", firebaseData)
            startActivity(intent)
        }
    }
}