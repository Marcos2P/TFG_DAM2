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


class FirebaseViewModel : ViewModel() {
    var firebaseData: FirebaseData? = null
}
class PrincipalActivity : AppCompatActivity() {

    private lateinit var firebaseViewModel: FirebaseViewModel

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
        val email = intent.getStringExtra("email")
        val mainFragment = MainFragment()
        val args = Bundle()
        args.putString("email", email)
        mainFragment.arguments = args

        firebaseViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")
        usersCollection.document(email.toString()).get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val nombreMascota = documentSnapshot.getString("nombre_mascota")
                val foto_mascota = documentSnapshot.getString("foto_Mascota")
                firebaseViewModel.firebaseData = FirebaseData(email, nombreMascota, foto_mascota)

            } else {
                // el correo electrónico no fue encontrado
            }
        }

        //Colocar Fragments
        supportFragmentManager.commit {
            replace(R.id.frameContainer, mainFragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    }
}