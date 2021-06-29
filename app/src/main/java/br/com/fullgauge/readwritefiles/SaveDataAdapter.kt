package br.com.fullgauge.readwritefiles

import android.app.TimePickerDialog
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.fullgauge.readwritefiles.databinding.SaveDataListItemBinding
import timber.log.Timber

class SaveDataAdapter: ListAdapter<SaveFile, SaveDataAdapter.ViewHolder>(SaveDataDiffCallback()) {
    class ViewHolder private constructor(private val binding: SaveDataListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SaveFile) {
            binding.saveFile = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SaveDataListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class SaveDataDiffCallback : DiffUtil.ItemCallback<SaveFile>() {
        override fun areItemsTheSame(oldItem: SaveFile, newItem: SaveFile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SaveFile,
            newItem: SaveFile
        ): Boolean {
            return oldItem == newItem
        }
    }
}