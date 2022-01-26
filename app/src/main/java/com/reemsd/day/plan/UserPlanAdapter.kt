package com.reemsd.day.plan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.reemsd.day.R
import com.reemsd.day.databinding.PlanCardBinding
import com.reemsd.day.network.PlanData

class UserPlanAdapter(val context: Context, val onDeleteClickListener: (PlanData) -> Unit) :
    ListAdapter<PlanData, UserPlanAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(var binding: PlanCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(views: PlanData) {
            binding.result = views

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PlanData>() {
        override fun areItemsTheSame(oldItem: PlanData, newItem: PlanData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlanData, newItem: PlanData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PlanCardBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        item.id
        holder.binding.delete.setOnClickListener {
            MaterialAlertDialogBuilder(context, position)
                .setTitle(context.getString(R.string.delete))
                .setMessage(R.string.Are_you_Sure_to_delete)
                .setCancelable(true)
                .setNegativeButton(context.getString(R.string.no)) { _, _ -> }
                .setPositiveButton(context.getString(R.string.delete)) { _, _ ->
                    onDeleteClickListener(item)
                }
                .show()

        }
        holder.binding.edit.setOnClickListener {
            var action = PlanFragmentDirections.actionPlanFragmentToEditPlanFragment(id = item.id.toString())
            holder.itemView.findNavController().navigate(action)

        }

    }
}