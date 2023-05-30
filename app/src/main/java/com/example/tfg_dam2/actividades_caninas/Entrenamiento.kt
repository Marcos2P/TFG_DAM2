package com.example.tfg_dam2.actividades_caninas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.tfg_dam2.R
import com.example.tfg_dam2.clases_alternativas.FirebaseData
import com.example.tfg_dam2.clases_alternativas.MainFragment
import com.example.tfg_dam2.fragmentsEntrenamiento.EntrenamientoFragment

class Entrenamiento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamiento)

        val firebaseData = intent.getSerializableExtra("firebaseData") as FirebaseData
        var fragmentContainerEntrenamiento = findViewById<View>(R.id.fragmentContainerEntrenamiento)
        val args = bundleOf("firebaseData" to firebaseData)

        var entrenamientoFragment = EntrenamientoFragment()
        entrenamientoFragment.arguments = args

        supportFragmentManager.commit {
            replace(R.id.fragmentContainerEntrenamiento, entrenamientoFragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}