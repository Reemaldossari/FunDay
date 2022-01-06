package com.reemsd.day.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.reemsd.day.R
import com.reemsd.day.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var binding : FragmentRegisterBinding? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      val fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater,container,false)
        binding = fragmentRegisterBinding
        return fragmentRegisterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        auth = Firebase.auth

        binding?.register?.setOnClickListener {
            regstration()
        }
    }
    fun regstration() {
        val regEmail = binding?.email?.editableText.toString()
        val regPassword = binding?.password?.editableText.toString()

        if (regEmail.isNotEmpty() && regPassword.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(regEmail, regPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(this.requireContext(), "Logged in", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                    } else {
                        Toast.makeText(this.requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    println(it.message)
                }
        }


    }
}