package com.example.tfg_dam2.fragmentsEntrenamiento

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tfg_dam2.R
import com.example.tfg_dam2.fragmentsEntrenamiento.first.ClickerFragment
import com.example.tfg_dam2.fragmentsEntrenamiento.second.ExtincionFragment
import com.example.tfg_dam2.fragmentsEntrenamiento.third.LegFragment


class EntrenamientoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_entrenamiento, container, false)

        //Variables botones
        val btnInfoClicker = view.findViewById<Button>(R.id.infoClicker)
        val btnInfoAttack = view.findViewById<Button>(R.id.infoAtack)
        val btnInfoSit = view.findViewById<Button>(R.id.infoSit)

        //Cambio de fragments
        btnInfoClicker.setOnClickListener(){
            val nuevoFragmento = ClickerFragment()
            val fragmentManager = requireFragmentManager()
            val fragTransaction = fragmentManager.beginTransaction()
            fragTransaction.replace(R.id.fragmentContainerEntrenamiento, nuevoFragmento)
            fragTransaction.addToBackStack(null)
            fragTransaction.commit()
        }
        btnInfoAttack.setOnClickListener(){
            val nuevoFragmento = LegFragment()
            val fragmentManager = requireFragmentManager()
            val fragTransaction = fragmentManager.beginTransaction()
            fragTransaction.replace(R.id.fragmentContainerEntrenamiento, nuevoFragmento)
            fragTransaction.addToBackStack(null)
            fragTransaction.commit()
        }
        btnInfoSit.setOnClickListener(){
            val nuevoFragmento = ExtincionFragment()
            val fragmentManager = requireFragmentManager()
            val fragTransaction = fragmentManager.beginTransaction()
            fragTransaction.replace(R.id.fragmentContainerEntrenamiento, nuevoFragmento)
            fragTransaction.addToBackStack(null)
            fragTransaction.commit()
        }


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EntrenamientoFragment().apply {

            }
    }
}