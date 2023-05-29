package com.example.tfg_dam2.fragmentsEntrenamiento.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfg_dam2.R


class ExtincionFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_extincion, container, false)
    }

    companion object {


        @JvmStatic
        fun newInstance() =
            ExtincionFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}