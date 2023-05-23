package com.example.tfg_dam2.clases_alternativas

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
        headerLayout.setOnClickListener {
            if (headerLayout.contentDescription=="Expand") {
                arrowImageView.setImageResource(R.drawable.baseline_expand_less_24)
                contentLayout.visibility = View.VISIBLE
                headerLayout.contentDescription="NotExpand"
                Toast.makeText(context, firebaseData.email, Toast.LENGTH_SHORT).show()
            } else {
                arrowImageView.setImageResource(R.drawable.baseline_expand_more_24)
                headerLayout.contentDescription="Expand"
                contentLayout.visibility = View.GONE
            }
        }
        firstNovedad.setOnClickListener {
            val url = "https://www.google.com"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }


        //Segunda Seccion
        val headerLayout2 = view.findViewById<LinearLayout>(R.id.headerLayout2)
        val arrowImageView2 = view.findViewById<ImageView>(R.id.arrowImageView2)
        val contentLayout2 = view.findViewById<LinearLayout>(R.id.contentLayout2)



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

