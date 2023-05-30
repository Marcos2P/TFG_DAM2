package com.example.tfg_dam2.fragmentsEntrenamiento.second

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tfg_dam2.R
import com.example.tfg_dam2.actividades_principales.PrincipalActivity
import com.example.tfg_dam2.clases_alternativas.FirebaseData
import com.example.tfg_dam2.clases_alternativas.Firestore


class ExtincionFragment : Fragment() {

    var firestore = Firestore()
    lateinit var firebaseData: FirebaseData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firebaseData = arguments?.getSerializable("firebaseData") as FirebaseData
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_extincion, container, false)

        var btnConseguidoExtincion = view.findViewById<Button>(R.id.btnConseguidoExtincion)
        btnConseguidoExtincion.setOnClickListener{
            firestore.changeEstado(firebaseData.email, true, 2)
            firebaseData.train2 = true
            val intent = Intent(activity, PrincipalActivity::class.java)
            intent.putExtra("firebaseData", firebaseData)
            activity?.startActivity(intent)
        }

        return view
    }

    companion object {


        @JvmStatic
        fun newInstance(firebaseData: FirebaseData) =
            ExtincionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("firebaseData", firebaseData)
                }
            }
    }
}