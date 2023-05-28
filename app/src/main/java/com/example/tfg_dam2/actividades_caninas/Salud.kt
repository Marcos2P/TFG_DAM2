package com.example.tfg_dam2.actividades_caninas

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tfg_dam2.R
import java.util.Calendar


class Salud : AppCompatActivity() {
     lateinit var listAdapterPeluqueria: ArrayAdapter<String>
    lateinit var listAdapterVeterinario: ArrayAdapter<String>
    lateinit var listAdapterEventos: ArrayAdapter<String>
     val itemListVeterinario: ArrayList<String> = ArrayList()
    val itemListPelu: ArrayList<String> = ArrayList()
    val itemListEventos: ArrayList<String> = ArrayList()
    private lateinit var listViewPelu: ListView
    private lateinit var listViewVeterinario : ListView
    private lateinit var listViewEventos : ListView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salud)

        //Variables
        var currentDate = Calendar.getInstance()
        var year = currentDate.get(Calendar.YEAR)
        var month = currentDate.get(Calendar.MONTH)
        var day = currentDate.get(Calendar.DAY_OF_MONTH)

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

                addItemPelu(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        btnSaludVeterinario.setOnClickListener(){
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                addItemVeterinario(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        btnSaludEventos.setOnClickListener(){
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                addItemEventos(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

    }

    private fun addItemPelu(date : String){
        if (date.isNotEmpty()) {
            itemListPelu.add(date)
            listAdapterPeluqueria.notifyDataSetChanged()
            listViewPelu.smoothScrollToPosition(listAdapterPeluqueria.count - 1)
        }
    }

    private fun addItemVeterinario(date: String){
        if(date.isNotEmpty()){
            itemListVeterinario.add(date)
            listAdapterVeterinario.notifyDataSetChanged()
            listViewVeterinario.smoothScrollToPosition(listAdapterVeterinario.count -1)
        }
    }

    private fun addItemEventos(date : String){
        if (date.isNotEmpty()){
            itemListEventos.add(date)
            listAdapterEventos.notifyDataSetChanged()
            listViewEventos.smoothScrollToPosition(listAdapterEventos.count - 1)
        }
    }
}