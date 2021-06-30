package br.com.fullgauge.readwritefiles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.fullgauge.readwritefiles.databinding.ActivityListBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.net.URLConnection


class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SaveDataAdapter(applicationContext) {
            shareFile(it)
        }

        val listOfFiles: List<SaveFile>? = this.filesDir.list()?.filter {
            it.endsWith(".mic")
        }?.map { file ->
            val readedFile = File(applicationContext.filesDir, file).bufferedReader().use {
                it.readText();
            }

            val value = Json.decodeFromString(readedFile) as SaveFile

            value
        }

        binding.rvList.adapter = adapter
        adapter.submitList(
            listOfFiles
        )
    }

    private fun shareFile(file: File) {
        //Not ideal - dont use in production
        // Change to FileProvider approach
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())


        val intentShareFile = Intent(Intent.ACTION_SEND)
        intentShareFile.type = "text/plain"
        intentShareFile.putExtra(
            Intent.EXTRA_STREAM,
            Uri.parse("file://" + file.absolutePath)
        )

        startActivity(Intent.createChooser(intentShareFile, "Share File"))
    }
}

