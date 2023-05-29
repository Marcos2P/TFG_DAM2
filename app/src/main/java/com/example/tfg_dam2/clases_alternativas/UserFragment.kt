package com.example.tfg_dam2.clases_alternativas

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.bumptech.glide.Glide
import com.example.tfg_dam2.R
import com.example.tfg_dam2.registro_login.LoginActivity


class UserFragment : Fragment() {

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

        val view = inflater.inflate(R.layout.fragment_user, container, false)

        var imagenUserPet = view.findViewById<ImageView>(R.id.imagenUserPet)
        Glide.with(this).load(firebaseData.fotoUrl).into(imagenUserPet)

        var nameUser = view.findViewById<TextView>(R.id.nameUser)
        nameUser.text = firebaseData.nombreUser

        var userEmail = view.findViewById<TextView>(R.id.userEmail)
        userEmail.text = firebaseData.email

        var userPassword = view.findViewById<TextView>(R.id.userPassword)
        userPassword.text = firebaseData.passwordUser

        var userPetPeso = view.findViewById<TextView>(R.id.userPetPeso)
        userPetPeso.text = firebaseData.pesoMascota+" g"

        var userPetEdad = view.findViewById<TextView>(R.id.userPetEdad)
        userPetEdad.text = firebaseData.edadMascota+" años"

        var cambiarEmail = view.findViewById<TextView>(R.id.cambiarEmail)
        var cambiarPassword = view.findViewById<TextView>(R.id.cambiarPassword)
        var cambiarPeso= view.findViewById<TextView>(R.id.cambiarPeso)
        var cambiarEdad = view.findViewById<TextView>(R.id.cambiarEdad)

        var userBtnDesconectar = view.findViewById<Button>(R.id.userBtnDesconectar)

        userBtnDesconectar.setOnClickListener(){
            var intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        cambiarEmail.setOnClickListener {
            changeEmail(userEmail)
        }
        cambiarPassword.setOnClickListener {
            changePassword(userPassword)
        }
        cambiarPeso.setOnClickListener {
            changePeso(userPetPeso)
        }
        cambiarEdad.setOnClickListener {
            changeEdad(userPetEdad)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(firebaseData: FirebaseData) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("firebaseData", firebaseData)
                }
            }
    }

    private fun changeEmail(userEmail : TextView){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Cambiar Email")

        val editText = EditText(context)
        editText.hint = "Introduce email"
        builder.setView(editText)

        builder.setPositiveButton("Guardar"){ dialogInterface, _ ->
            if(Patterns.EMAIL_ADDRESS.matcher(editText.text.toString()).matches()){
                val newEmail = editText.text.toString()
                firestore.changeEmail(firebaseData.email, newEmail)
                firebaseData.email = newEmail
                userEmail.text = firebaseData.email
                dialogInterface.dismiss()
            }else{
                Toast.makeText(context, "Introduce email valido", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.create().show()

    }

    private fun changePassword(userPassword : TextView){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Cambiar Password")

        val editText = EditText(context)
        editText.hint = "Introduce Password"
        builder.setView(editText)

        builder.setPositiveButton("Guardar"){ dialogInterface, _ ->
            if(editText.text.length < 6){
                Toast.makeText(context, "Introduce password mas larga", Toast.LENGTH_SHORT).show()
            }else{
                val newPassword = editText.text.toString()
                firestore.changePassword(firebaseData.email, newPassword)
                firebaseData.passwordUser = newPassword
                userPassword.text = firebaseData.passwordUser
                dialogInterface.dismiss()
            }
        }

        builder.setNegativeButton("Cancelar"){dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.create().show()
    }

    private fun changePeso(userPetPeso : TextView){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Cambiar Peso")

        val editText = EditText(context)
        editText.hint = "Introduce Peso"

        builder.setView(editText)

        builder.setPositiveButton("Guardar"){ dialogInterface, _ ->
            if(editText.text.isDigitsOnly()){
                val newPeso = editText.text.toString()
                firestore.changePeso(firebaseData.email, newPeso)
                firebaseData.pesoMascota = newPeso
                userPetPeso.text = firebaseData.pesoMascota+" g"
                dialogInterface.dismiss()
            }else{
                Toast.makeText(context, "Introduce peso correcto", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.create().show()
    }

    private fun changeEdad(userPetEdad : TextView){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Cambiar Edad")

        val editText = EditText(context)
        editText.hint = "Introduce Edad"
        builder.setView(editText)

        builder.setPositiveButton("Guardar"){ dialogInterface, _ ->
            if(editText.text.isDigitsOnly()){
                val newEdad = editText.text.toString()
                firestore.changeEdad(firebaseData.email, newEdad)
                firebaseData.edadMascota = newEdad
                userPetEdad.text = firebaseData.edadMascota+" años"
                dialogInterface.dismiss()
            }else{
                Toast.makeText(context, "Introduce edad correcta", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar"){dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.create().show()
    }

}