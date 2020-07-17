package com.sb.myrecords.presentation.myrecords

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sb.myrecords.data.entities.Record
import com.sb.myrecords.databinding.ItemRecordBinding

/**
 * Created by Sb on 14/07/2020
 * com.sb.myrecords.presentation.myrecords
 * My Records
 *
 * Adapter for the [RecyclerView] in [MyRecordsFragment].
 */
class RecordAdapter : ListAdapter<Record, RecordAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = getItem(position)

        holder.apply {
            bind(createOnClickListener(), record)
            itemView.tag = record
        }
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            Log.d("RecordAdapter", "Playing video!")
        }
    }

    class ViewHolder(
        private val binding: ItemRecordBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Record) {
            binding.apply {
                clickListener = listener
                record = item
                executePendingBindings()
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Record>() {

        override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem.recordVideo == newItem.recordVideo && oldItem.description == newItem.description && oldItem.previewImg == newItem.previewImg
        }

        override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
            return oldItem == newItem
        }

    }
}