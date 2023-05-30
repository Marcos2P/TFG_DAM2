package com.example.tfg_dam2.fragmentsEntrenamiento.third

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


class LegFragment : Fragment() {

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

        var view = inflater.inflate(R.layout.fragment_leg, container, false)

        var btnConseguidoLeg = view.findViewById<Button>(R.id.btnConseguidoLeg)
        btnConseguidoLeg.setOnClickListener {
            firestore.changeEstado(firebaseData.email, true, 3)
            firebaseData.train3 = true
            val intent = Intent(activity, PrincipalActivity::class.java)
            intent.putExtra("firebaseData", firebaseData)
            activity?.startActivity(intent)
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(firebaseData: FirebaseData) =
            LegFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("firebaseData", firebaseData)
                }
            }
    }
}