package com.example.tfg_dam2.actividades_caninas

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tfg_dam2.R
import com.example.tfg_dam2.actividades_principales.PrincipalActivity
import com.example.tfg_dam2.clases_alternativas.Circle
import com.example.tfg_dam2.clases_alternativas.FirebaseData
import com.example.tfg_dam2.clases_alternativas.Firestore
import java.util.Calendar


class Salud : AppCompatActivity() {

     lateinit var listAdapterPeluqueria: ArrayAdapter<String>
    lateinit var listAdapterVeterinario: ArrayAdapter<String>
    lateinit var listAdapterEventos: ArrayAdapter<String>
    private lateinit var listViewPelu: ListView
    private lateinit var listViewVeterinario : ListView
    private lateinit var listViewEventos : ListView
    private lateinit var paseo1: Circle
    private lateinit var paseo2: Circle
    private lateinit var paseo3: Circle
    var firestore = Firestore()
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salud)


        val firebaseData = intent.getSerializableExtra("firebaseData") as FirebaseData
        val itemListVeterinario: ArrayList<String> = firebaseData.fechaVeterinario
        val itemListPelu: ArrayList<String> = firebaseData.fechaPelu
        val itemListEventos: ArrayList<String> = firebaseData.fechaDeporte

        //Variables

        paseo1 = findViewById(R.id.paseo1)
        paseo2= findViewById(R.id.paseo2)
        paseo3 = findViewById(R.id.paseo3)
        var currentDate = Calendar.getInstance()
        var year = currentDate.get(Calendar.YEAR)
        var month = currentDate.get(Calendar.MONTH)
        var day = currentDate.get(Calendar.DAY_OF_MONTH)
        var btnVolver = findViewById<Button>(R.id.saludVolver)

        var calendar  = Calendar.getInstance()
        var fecha = calendar.time


        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()

        calendar1.time = firebaseData.fecha
        calendar2.time = fecha

        val dia1 = calendar1.get(Calendar.DAY_OF_YEAR)
        val dia2 = calendar2.get(Calendar.DAY_OF_YEAR)

        if (dia2 > dia1) {
            firebaseData.paseo1="red"
            firebaseData.paseo3="red"
            firebaseData.paseo2="red"
            firestore.cambiarColor(firebaseData.email, "red", 1)
            firestore.cambiarColor(firebaseData.email, "red", 2)
            firestore.cambiarColor(firebaseData.email, "red", 3)
            firestore.cambiaFecha(firebaseData.email, fecha)
            Toast.makeText(this, "si", Toast.LENGTH_SHORT).show()
        }


        paseo1.setCircleColor(firebaseData.paseo1)
        paseo2.setCircleColor(firebaseData.paseo2)
        paseo3.setCircleColor(firebaseData.paseo3)
        // Paseos
        paseo1.setOnClickListener{
            firebaseData.paseo1 = "green"
            paseo1.setCircleColor(firebaseData.paseo1)
            firestore.cambiarColor(firebaseData.email, "green", 1)
        }
        paseo2.setOnClickListener{
            firebaseData.paseo2 = "green"
            paseo2.setCircleColor(firebaseData.paseo2)
            firestore.cambiarColor(firebaseData.email, "green", 2)
        }
        paseo3.setOnClickListener{
            firebaseData.paseo3 = "green"
            paseo3.setCircleColor(firebaseData.paseo3)
            firestore.cambiarColor(firebaseData.email, "green", 3)
        }







        btnVolver.setOnClickListener {
            val intent=Intent(this, PrincipalActivity::class.java)
            intent.putExtra("firebaseData", firebaseData)
            startActivity(intent)
        }


        //Peluqueria
        listViewPelu = findViewById(R.id.listViewPelu)
        val btnSaludPeluqueria = findViewById<Button>(R.id.btnSaludPeluqueria)
        listAdapterPeluqueria = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemListPelu)
        listViewPelu.adapter = listAdapterPeluqueria

        //Veterinario
        listViewVeterinario = findViewById(R.id.listViewVeterinario)
        val btnSaludVeterinario = findViewById<Button>(R.id.btnSaludVeterinario)
        listAdapterVeterinario = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemListVeterinario)
        listViewVeterinario.adapter = listAdapterVeterinario

        //Eventos deportivos
        listViewEventos = findViewById(R.id.listViewEventos)
        val btnSaludEventos = findViewById<Button>(R.id.btnSaludEventos)
        listAdapterEventos = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemListEventos)
        listViewEventos.adapter = listAdapterEventos



        // Funciones del proyecto
        btnSaludPeluqueria.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                addItemPelu(selectedDate, itemListPelu, firebaseData )
            }, year, month, day)

            datePickerDialog.show()
        }

        btnSaludVeterinario.setOnClickListener(){
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                addItemVeterinario(selectedDate, itemListVeterinario, firebaseData)
            }, year, month, day)

            datePickerDialog.show()
        }

        btnSaludEventos.setOnClickListener(){
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                addItemEventos(selectedDate, itemListEventos, firebaseData)
            }, year, month, day)

            datePickerDialog.show()
        }

    }

    private fun addItemPelu(date : String, itemListPelu : ArrayList<String>, firebaseData : FirebaseData){
        if (date.isNotEmpty()) {
            listAdapterPeluqueria.notifyDataSetChanged()
            listViewPelu.smoothScrollToPosition(listAdapterPeluqueria.count - 1)
            firestore.añadirPelu(firebaseData.email, date)
            firebaseData.fechaPelu.add(date)
        }
    }

    private fun addItemVeterinario(date : String, itemListVeterinario : ArrayList<String>, firebaseData : FirebaseData){
        if(date.isNotEmpty()){
            listAdapterVeterinario.notifyDataSetChanged()
            listViewVeterinario.smoothScrollToPosition(listAdapterVeterinario.count -1)
            firestore.añadirVeterinario(firebaseData.email, date)
            firebaseData.fechaVeterinario.add(date)
        }
    }

    private fun addItemEventos(date : String, itemListEventos : ArrayList<String>, firebaseData : FirebaseData){
        if (date.isNotEmpty()){
            listAdapterEventos.notifyDataSetChanged()
            listViewEventos.smoothScrollToPosition(listAdapterEventos.count - 1)
            firestore.añadirDeporte(firebaseData.email, date)
            firebaseData.fechaDeporte.add(date)
        }
    }
}