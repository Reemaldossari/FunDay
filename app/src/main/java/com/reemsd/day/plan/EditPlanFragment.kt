package com.reemsd.day.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.reemsd.day.databinding.FragmentEditPlanBinding
import com.reemsd.day.network.PlanData


class EditPlanFragment : Fragment() {
    val auth = FirebaseAuth.getInstance().currentUser
lateinit var binding : FragmentEditPlanBinding
lateinit var id :String
    val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
          id = it?.getString("id").toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentEditPlanBinding.inflate(inflater, container, false)
        binding.lifecycleOwner =this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetails(id)
        binding.editBtn.setOnClickListener {
            updatePlan(id)
        }


    }
    private fun getDetails(arg: String) {
        val mRef = database.getReference("userPlan").child(arg)
        mRef.get().addOnCompleteListener { DataSnapshot1 ->
            val details = DataSnapshot1.result.getValue(PlanData::class.java)
            binding.costInput.setText(details?.cost.toString())
            binding.titleInput.setText(details?.title.toString())
            binding.invitedInput.setText(details?.invited.toString())
            binding.placeInput.setText(details?.place.toString())
            binding.detailsInput.setText(details?.details.toString())

        }

    }
    fun updatePlan (editId :String){
        val title = binding.titleInput.text.toString()
        val place = binding.placeInput.text.toString()
        val cost = binding.costInput.text.toString()
        val invited = binding.invitedInput.text.toString()
        val details = binding.detailsInput.text.toString()

        val userid = auth?.uid
        val updateplan = PlanData(editId,userid,title,place,cost,invited,details)
        database.getReference("userPlan/$editId").setValue(updateplan).addOnCompleteListener {
            Toast.makeText(requireContext(), "updated it successfully", Toast.LENGTH_SHORT).show()
        }
    }
}