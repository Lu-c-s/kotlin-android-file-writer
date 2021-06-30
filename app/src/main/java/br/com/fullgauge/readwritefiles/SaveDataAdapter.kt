package br.com.fullgauge.readwritefiles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.fullgauge.readwritefiles.databinding.SaveDataListItemBinding
import kotlinx.serialization.json.*
import java.io.File
import java.io.FilenameFilter
import java.net.URLConnection

class SaveDataAdapter(val context: Context, val shareFile: (file: File) -> Unit) :
    ListAdapter<SaveFile, SaveDataAdapter.ViewHolder>(SaveDataDiffCallback()) {
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


        holder.itemView.findViewById<Button>(R.id.button).setOnClickListener {
            val path = context.filesDir.absolutePath
            val file = File("$path/${event.fileName}")

            Log.d("DataAdapter", "path = $path and file value $file")

            shareFile(file)
        }

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