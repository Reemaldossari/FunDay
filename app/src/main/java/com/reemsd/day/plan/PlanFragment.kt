package com.reemsd.day.plan


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.reemsd.day.R
import com.reemsd.day.databinding.FragmentPlanBinding
import com.reemsd.day.home.HomeViewModel


class PlanFragment : Fragment() {

    // this is val will take the current User login
    val auth = FirebaseAuth.getInstance().currentUser
    private lateinit var binding: FragmentPlanBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding?.planRv?.adapter = UserPlanAdapter(requireContext()) {
            delete(it.id!!)
        }
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addBtn.setOnClickListener {
            if (auth == null) {
                Navigation.findNavController(it).navigate(R.id.action_planFragment_to_mainFragment)
            } else {
                Navigation.findNavController(it)
                    .navigate(R.id.action_planFragment_to_addPlanFragment)
            }
        }
    }

    fun delete(id: String) {
        var db = FirebaseDatabase.getInstance().getReference("userPlan")
        db.child(id).removeValue().addOnSuccessListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.Deleted_Successfully),
                Toast.LENGTH_SHORT
            ).show()
            viewModel.getUserPlan()
        }.addOnFailureListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.An_error_occurred),
                Toast.LENGTH_SHORT
            ).show()

        }

    }
}


