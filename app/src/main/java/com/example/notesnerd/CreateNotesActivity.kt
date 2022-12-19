package com.example.notesnerd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.notenerd.viewmodel.NotesViewModel
import com.example.notesnerd.databinding.ActivityCreateNotesBinding
import com.example.notesnerd.databinding.ActivityMainBinding
import com.example.notesnerd.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CreateNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNotesBinding
    val viewModel: NotesViewModel by viewModels()
    var priority: String = "1"

    override fun onCreate(savedInstanceState: Bundle?) {


        binding = ActivityCreateNotesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.setTitle("Create Notes")

        binding.ivLow.setOnClickListener {
            priority = "3"
            binding.ivLow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivMedium.setImageResource(0)
            binding.ivHigh.setImageResource(0)
        }

        binding.ivMedium.setOnClickListener {
            priority = "2"
            binding.ivMedium.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivLow.setImageResource(0)
            binding.ivHigh.setImageResource(0)
        }

        binding.ivHigh.setOnClickListener {
            priority = "1"
            binding.ivHigh.setImageResource(R.drawable.ic_baseline_done_24)
            binding.ivMedium.setImageResource(0)
            binding.ivLow.setImageResource(0)
        }

        binding.btnSave.setOnClickListener {
            createNotes(it)
            startActivity(Intent(this@CreateNotesActivity, MainActivity::class.java))

        }

    }

    private fun createNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val subtitle = binding.etSubtitle.text.toString()
        val notes = binding.etNotesContent.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        val data = Notes(null, title, subtitle, notes, notesDate.toString(), priority)
        viewModel.insertNotes(data)
        Toast.makeText(this@CreateNotesActivity, "Notes added successfully", Toast.LENGTH_SHORT).show()
    }

}


