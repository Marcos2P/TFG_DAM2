package com.example.tfg_dam2.registro_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.view.get
import com.example.tfg_dam2.R
import com.google.android.material.snackbar.Snackbar

class RegisterPetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pet)

        //Recuperamos Email
        var email = intent.getStringExtra("email")

        //Variables
        var petImage = findViewById<ImageView>(R.id.fotoMascota)
        var petName = findViewById<EditText>(R.id.petName)
        var petRaza = findViewById<Spinner>(R.id.petRaza)
        var petPeso = findViewById<EditText>(R.id.petPeso)
        var petEdad = findViewById<EditText>(R.id.petEdad)
        var petContinuar = findViewById<Button>(R.id.petContinuar)


        //Linkear array-string con el Spinner.
        ArrayAdapter.createFromResource(
            this,
            R.array.razas,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            petRaza.adapter = adapter
        }

        petContinuar.setOnClickListener {
            if (petName.text.toString().isNotEmpty() && petPeso.text.toString().isNotEmpty() && petEdad.text.toString().isNotEmpty() && petRaza.selectedItem.toString().isNotEmpty()){
                showErrorPet("si")
            }else{
                showErrorPet("Rellene todos los campos")
            }
        }


    }





    fun showErrorPet(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.RegisterPet), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}