package br.com.fullgauge.readwritefiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import android.util.Log
import android.widget.Toast
import br.com.fullgauge.readwritefiles.databinding.ActivityMainBinding
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class SaveFile(
    val id: Int,
    val fileName: String = "",
    val fileData: String = "",
    val fileEmail: String = ""
) : Parcelable

var autoInc = 1

val saveDataList = mutableListOf<SaveFile>()



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val extra = "MESSAGE_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            autoInc++

            saveDataList.add(
                SaveFile(
                    autoInc,
                    binding.fileName.text.toString(),
                    binding.fileData.text.toString(),
                    binding.fileEmail.text.toString()
                )
            )

            Toast.makeText(
                applicationContext,
                "Added new save data: ${binding.fileName.text}",
                Toast.LENGTH_LONG
            ).show()
        }

        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra(extra, ArrayList(saveDataList) )
        }

        binding.listButton.setOnClickListener {
            startActivity(intent)
        }
    }
}