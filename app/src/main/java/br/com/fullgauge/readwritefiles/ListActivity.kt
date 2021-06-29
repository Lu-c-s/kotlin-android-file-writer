package br.com.fullgauge.readwritefiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.fullgauge.readwritefiles.databinding.ActivityListBinding
import timber.log.Timber

class ListActivity : AppCompatActivity() {
    private val extra = "MESSAGE_LIST"
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listValue = intent.getParcelableArrayListExtra<SaveFile>(extra) as ArrayList<SaveFile>
        Timber.d("entered here")
        Log.d("ListActivity", "List value $listValue")
        val adapter = SaveDataAdapter()

        binding.rvList.adapter = adapter
        adapter.submitList(
            listOf(
                SaveFile(1, "A", "B", "c@mail.com"),
                SaveFile(2, "C", "D", "T@mail.com")
            )
        )
    }
}