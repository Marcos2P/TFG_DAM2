package com.example.tfg_dam2.registro_login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

import com.example.tfg_dam2.R
import com.example.tfg_dam2.clases_alternativas.Firestore
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.core.View
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class RegisterPetActivity : AppCompatActivity() {
    val fire : Firestore = Firestore()
    var context: Context = this
    var mStorage : StorageReference = FirebaseStorage.getInstance().getReference()
    private val GALLERY_INTENT = 1
    private lateinit var fotoMascota : ImageView
    lateinit var urlMascota : Uri

    @SuppressLint("IntentReset")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pet)

        //Recuperamos Email
        var email = intent.getStringExtra("email")

        //Variables
        fotoMascota = findViewById<ImageView>(R.id.fotoMascota)
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
            if (petName.text.toString().isNotEmpty() && petPeso.text.toString().isNotEmpty() && petEdad.text.toString().isNotEmpty() && petRaza.selectedItem.toString().isNotEmpty() && urlMascota!=null){
                if (email != null) {
                    fire.loguearMascota(email, petName.text.toString(), petPeso.text.toString().toFloat(), petEdad.text.toString().toInt(), petRaza.selectedItem.toString(),urlMascota, context)
                }
            }else{
                showErrorPet("Rellene todos los campos")
            }
        }


        //Abrir Galeria
        fotoMascota.setOnClickListener{
            var intent : Intent = Intent(Intent.ACTION_PICK)
            intent.setType("image/*") //Abarca todas las extensione
            startActivityForResult(intent, GALLERY_INTENT)

        }



    }

    // Guardar la foto seleccionada de la galeria para mandarla al Storage
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            var uri : Uri? = data?.data

            var filePath = mStorage.child("images").child(uri?.lastPathSegment.toString())

            if (uri != null) {
                filePath.putFile(uri).addOnSuccessListener {
                    // Obtener URL de la foto
                    filePath.downloadUrl.addOnSuccessListener { downloadUri ->
                        Glide.with(this).load(downloadUri).into(fotoMascota)
                        urlMascota = downloadUri
                    }
                }
            }
            //

        }

    }


    fun showErrorPet(cadena : String){
        var snackbar : Snackbar = Snackbar.make(findViewById(R.id.RegisterPet), cadena, Snackbar.LENGTH_LONG)
        snackbar.show()
    }




}