package com.example.tfg_dam2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class RegisterPetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pet)

        //Recuperamos Email
        var email = intent.getStringExtra("email")
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show()
    }
}