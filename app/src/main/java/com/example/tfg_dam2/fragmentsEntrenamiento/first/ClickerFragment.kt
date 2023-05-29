package com.example.tfg_dam2.fragmentsEntrenamiento.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tfg_dam2.R


class ClickerFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_clicker, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ClickerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}