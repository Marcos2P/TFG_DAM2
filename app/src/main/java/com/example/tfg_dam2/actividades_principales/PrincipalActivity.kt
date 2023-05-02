package com.example.tfg_dam2.actividades_principales

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.tfg_dam2.R
import com.example.tfg_dam2.clases_alternativas.MainFragment
import com.example.tfg_dam2.clases_alternativas.NewsFragment
import com.example.tfg_dam2.clases_alternativas.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.collection.LLRBNode.Color

class PrincipalActivity : AppCompatActivity() {
    lateinit var navegation : BottomNavigationView
    private val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener{item ->
        when(item.itemId){
            R.id.itemMainFragment -> {
                supportFragmentManager.commit {
                    replace<MainFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.itemUserFragment -> {
                supportFragmentManager.commit {
                    replace<UserFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.itemNewsFragment -> {
                supportFragmentManager.commit {
                    replace<NewsFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

        }

        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        navegation = findViewById(R.id.navMenu)
        navegation.setOnNavigationItemSelectedListener(mOnNavMenu)


        //Colocar Fragments
        supportFragmentManager.commit {
            replace<MainFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    }
}