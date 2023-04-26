package com.example.tfg_dam2.actividades_principales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.tfg_dam2.R
import com.example.tfg_dam2.clases_alternativas.MainFragment

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        //Colocar Fragmetns
        supportFragmentManager.commit {
            replace<MainFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    }
}