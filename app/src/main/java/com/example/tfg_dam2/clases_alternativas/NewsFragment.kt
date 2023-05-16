package com.example.tfg_dam2.clases_alternativas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfg_dam2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isExpanded = false
    private var isExpanded2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_news, container, false)

        //Primera Seccion
        val headerLayout = view.findViewById<LinearLayout>(R.id.headerLayout)
        val arrowImageView = view.findViewById<ImageView>(R.id.arrowImageView)
        val contentLayout = view.findViewById<LinearLayout>(R.id.contentLayout)
        headerLayout.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                arrowImageView.setImageResource(R.drawable.baseline_expand_less_24)
                contentLayout.visibility = View.VISIBLE
            } else {
                arrowImageView.setImageResource(R.drawable.baseline_expand_more_24)
                contentLayout.visibility = View.GONE
            }
        }


        //Segunda Seccion
        val headerLayout2 = view.findViewById<LinearLayout>(R.id.headerLayout2)
        val arrowImageView2 = view.findViewById<ImageView>(R.id.arrowImageView2)
        val contentLayout2 = view.findViewById<LinearLayout>(R.id.contentLayout2)
        headerLayout2.setOnClickListener {
            isExpanded2 = !isExpanded2
            if (isExpanded2) {
                arrowImageView2.setImageResource(R.drawable.baseline_expand_less_24)
                contentLayout2.visibility = View.VISIBLE
            } else {
                arrowImageView2.setImageResource(R.drawable.baseline_expand_more_24)
                contentLayout2.visibility = View.GONE
            }
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
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

