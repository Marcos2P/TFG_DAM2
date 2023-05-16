package com.example.tfg_dam2.clases_alternativas

import android.annotation.SuppressLint
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
import com.example.tfg_dam2.actividades_principales.FirebaseViewModel
import com.google.firebase.firestore.FirebaseFirestore


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private val db = FirebaseFirestore.getInstance()
private val users = db.collection("users")

class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var firebaseViewModel: FirebaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        firebaseViewModel = ViewModelProvider(requireActivity()).get(FirebaseViewModel::class.java)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)


        val email = arguments?.getString("email")
        val query = users.whereEqualTo("email", email)
        query.get().addOnSuccessListener { queryS ->
            if (!queryS.isEmpty) {
                val documentSnapshot = queryS.documents[0]
                val nombreMascota = documentSnapshot.getString("nombre_mascota")
                val nombrePerro = view.findViewById<TextView>(R.id.nombrePerro)
                nombrePerro.text = nombreMascota

                val foto_mascota = documentSnapshot.getString("foto_Mascota")
                val imagenMascota = view.findViewById<ImageView>(R.id.petView)
                Glide.with(this).load(foto_mascota).into(imagenMascota)
            }
        }

        val sombreMascot = firebaseViewModel.firebaseData?.nombreMascota
        val footMascot = firebaseViewModel.firebaseData?.fotoUrl
        val imageMascot = view.findViewById<ImageView>(R.id.petView)
        val sombrePero = view.findViewById<TextView>(R.id.nombrePerro)
        sombrePero.text = sombreMascot
        Glide.with(this).load(footMascot).into(imageMascot)

        val btnPetComida = view.findViewById<Button>(R.id.btnPetComida)
        btnPetComida.setOnClickListener(){

        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}