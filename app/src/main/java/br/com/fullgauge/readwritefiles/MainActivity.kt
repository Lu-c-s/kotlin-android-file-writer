package br.com.fullgauge.readwritefiles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fullgauge.readwritefiles.databinding.ActivityMainBinding
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.net.URLConnection

@Serializable
data class SaveFile(
    val id: Int,
    val fileName: String = "",
    val fileData: String = "",
    val fileEmail: String = ""
)

var autoInc = 1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            autoInc++

            val saveFile = SaveFile(
                autoInc,
                binding.fileName.text.toString(),
                binding.fileData.text.toString(),
                binding.fileEmail.text.toString()
            )

            val json = Json.encodeToString(saveFile)

            applicationContext.openFileOutput("${saveFile.fileName}.mic", Context.MODE_PRIVATE).use {
                it.write(json.toByteArray())
            }

            Toast.makeText(
                applicationContext,
                "Added new save data: ${saveFile.fileName}",
                Toast.LENGTH_LONG
            ).show()
        }


        binding.listButton.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }


}