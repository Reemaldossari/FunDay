package com.reemsd.day.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.reemsd.day.R
import com.reemsd.day.databinding.FragmentAddPlanBinding
import com.reemsd.day.home.HomeViewModel
import com.reemsd.day.network.PlanData

class AddPlanFragment : Fragment() {
    // this is val will take the current User login
    val auth = FirebaseAuth.getInstance().currentUser
    // this is databace
    val database = FirebaseDatabase.getInstance()
    //this is reference
    val reference = database.getReference("userPlan")

    private lateinit var binding: FragmentAddPlanBinding

    private val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddPlanBinding.inflate(inflater ,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }
     fun addNewPlan (){
        val title = binding.titleInput.text.toString()
        val place = binding.placeInput.text.toString()
        val cost = binding.costInput.text.toString()
        val invited = binding.invitedInput.text.toString()
        val details = binding.detailsInput.text.toString()
//         this is line mean every time user upload new data to the database it's will have unique id.
         val id = reference.push().key
//         this is line to take userid
         val userid = auth?.uid
         val newplan = PlanData(id,userid,title,place,cost,invited,details)
         database.getReference("userPlan/$id").setValue(newplan).addOnCompleteListener {
             findNavController().navigate(R.id.action_addPlanFragment_to_planFragment)
         }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveTodatabaceBtn.setOnClickListener {
            addNewPlan ()
        }
    }

    }
