package com.example.tfg_dam2.actividades_principales

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tfg_dam2.R
import com.example.tfg_dam2.clases_alternativas.FirebaseData
import com.example.tfg_dam2.clases_alternativas.MainFragment
import com.example.tfg_dam2.clases_alternativas.NewsFragment
import com.example.tfg_dam2.clases_alternativas.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.collection.LLRBNode.Color
import com.google.firebase.firestore.FirebaseFirestore


class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        var navegation : BottomNavigationView


        val firebaseData = intent.getSerializableExtra("firebaseData") as FirebaseData

        val mainFragment = MainFragment()
        val newsFragment = NewsFragment()
        val userFragment = UserFragment()

        val args = bundleOf("firebaseData" to firebaseData)
        // args.putString("email", email)
        mainFragment.arguments = args
        newsFragment.arguments = args
        userFragment.arguments = args

        //Colocar Fragments
        supportFragmentManager.commit {
            replace(R.id.frameContainer, newsFragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

         val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.itemMainFragment -> {
                    supportFragmentManager.commit {
                        replace<MainFragment>(R.id.frameContainer, args = args)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                    return@OnNavigationItemSelectedListener true
                }

                R.id.itemUserFragment -> {
                    supportFragmentManager.commit {
                        replace<UserFragment>(R.id.frameContainer, args = args)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                    return@OnNavigationItemSelectedListener true
                }

                R.id.itemNewsFragment -> {
                    supportFragmentManager.commit {
                        replace<NewsFragment>(R.id.frameContainer, args = args)
                        setReorderingAllowed(true)
                        addToBackStack("replacement")
                    }
                    return@OnNavigationItemSelectedListener true
                }

            }

            false
        }
        navegation = findViewById(R.id.navMenu)
        navegation.setOnNavigationItemSelectedListener(mOnNavMenu)

    }
}