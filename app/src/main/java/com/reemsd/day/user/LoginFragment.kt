package com.reemsd.day.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.reemsd.day.R
import com.reemsd.day.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var binding:FragmentLoginBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentLoginBinding
        return fragmentLoginBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  val email =findViewById<EditText>(R.id.login_email).text.toString()
        binding?.btnLogin?.setOnClickListener {
            logIn()
        }
    }

    fun logIn() {
        val email = binding?.emailLogin?.editableText.toString()

        val password = binding?.passLogin?.editableText.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(this.requireContext(), "Logged in", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().navigate(R.id.action_mainFragment_to_registerFragment)
                    } else {
                        Toast.makeText(this.requireContext(), "Error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .addOnFailureListener {
                    println(it.message)
                }
        }
    }
}