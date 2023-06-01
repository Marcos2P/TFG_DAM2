package com.example.tfg_dam2.clases_alternativas

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tfg_dam2.R


class NewsFragment : Fragment() {


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
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_news, container, false)

        //Primera Seccion
        val firstNovedad = view.findViewById<Button>(R.id.firstNovedad)
        val headerLayout = view.findViewById<LinearLayout>(R.id.headerLayout)
        val arrowImageView = view.findViewById<ImageView>(R.id.arrowImageView)
        val contentLayout = view.findViewById<LinearLayout>(R.id.contentLayout)
        val secondNovedad = view.findViewById<Button>(R.id.Novedad2)

        headerLayout.setOnClickListener {
            if (headerLayout.contentDescription=="Expand") {
                arrowImageView.setImageResource(R.drawable.baseline_expand_less_24)
                contentLayout.visibility = View.VISIBLE
                headerLayout.contentDescription="NotExpand"
            } else {
                arrowImageView.setImageResource(R.drawable.baseline_expand_more_24)
                headerLayout.contentDescription="Expand"
                contentLayout.visibility = View.GONE
            }
        }
        firstNovedad.setOnClickListener {
            val url = "https://metropoliabierta.elespanol.com/el-pulso-de-la-ciudad/en-la-calle/promueven-adopcion-perros-potencialmente-peligrosos_20205_102.html#:~:text=Las%20razas%20que%20pertenecen%20a,terrier%2C%20tosa%20inu%20o%20japon%C3%A9s."
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        secondNovedad.setOnClickListener {
            val url = "https://www.ondacero.es/noticias/sociedad/estos-son-cambios-que-preve-ley-perros-potencialmente-peligrosos_2023021063e60aad54dfc0000123d02a.html"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }



        //Segunda Seccion
        val headerLayout2 = view.findViewById<LinearLayout>(R.id.headerLayout3)
        val arrowImageView2 = view.findViewById<ImageView>(R.id.arrowImageView3)
        val contentLayout2 = view.findViewById<LinearLayout>(R.id.contentLayout3)
        val novedad3 = view.findViewById<Button>(R.id.Novedad3)
        val novedad4 = view.findViewById<Button>(R.id.Novedad4)

        headerLayout2.setOnClickListener {
            if (headerLayout2.contentDescription=="Expand") {
                arrowImageView2.setImageResource(R.drawable.baseline_expand_less_24)
                contentLayout2.visibility = View.VISIBLE
                headerLayout2.contentDescription="NotExpand"
            } else {
                arrowImageView2.setImageResource(R.drawable.baseline_expand_more_24)
                headerLayout2.contentDescription="Expand"
                contentLayout2.visibility = View.GONE
            }
        }

        novedad3.setOnClickListener {
            val url = "https://www.informacion.es/vida-y-estilo/mascotas/raza-perro-moda-espanoles-dv-85277433.html"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        novedad4.setOnClickListener {
            val url = "https://pinkermoda.com/hilfiger-coleccion-mascotas/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


        return view

    }

    companion object {
        @JvmStatic
        fun newInstance(firebaseData: FirebaseData) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("firebaseData", firebaseData)
                }
            }
    }
}

