package com.example.tfg_dam2.clases_alternativas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tfg_dam2.R
import com.example.tfg_dam2.actividades_caninas.Alimentacion
import com.example.tfg_dam2.actividades_caninas.Entrenamiento
import com.example.tfg_dam2.actividades_caninas.Salud
import com.google.firebase.firestore.FirebaseFirestore


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class MainFragment : Fragment() {

    lateinit var firebaseData: FirebaseData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firebaseData = arguments?.getSerializable("firebaseData") as FirebaseData
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)



        val imageMascota = view.findViewById<ImageView>(R.id.petView)
        Glide.with(this).load(firebaseData.fotoUrl).into(imageMascota)

        val nombrePero = view.findViewById<TextView>(R.id.nombrePerro)
        nombrePero.text = firebaseData.nombreMascota


        val btnPetComida = view.findViewById<Button>(R.id.btnPetComida)
        btnPetComida.setOnClickListener(){
            val intent = Intent(activity, Alimentacion::class.java)
            intent.putExtra("firebaseData", firebaseData)
            activity?.startActivity(intent)
        }
        val btnPetEntrenamiento = view.findViewById<Button>(R.id.btnPetEntrenamiento)
        btnPetEntrenamiento.setOnClickListener(){
            val intent = Intent(activity, Entrenamiento::class.java)
            activity?.startActivity(intent)
        }

        val btnPetSalud = view.findViewById<Button>(R.id.btnPetSalud)
        btnPetSalud.setOnClickListener(){
            val intent = Intent(activity, Salud::class.java)
            activity?.startActivity(intent)
        }


        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(firebaseData: FirebaseData) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("firebaseData", firebaseData)
                }
            }
    }
}