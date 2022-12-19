package com.example.notesnerd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.room.Room
import com.example.notenerd.viewmodel.NotesViewModel
import com.example.notesnerd.adapter.NotesAdapter
import com.example.notesnerd.database.NotesDatabase
import com.example.notesnerd.databinding.ActivityEditNotesBinding
import com.example.notesnerd.model.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNotesBinding
    private lateinit var database: NotesDatabase
    val viewModel: NotesViewModel by viewModels()
    var priority: String = "1"
    var uid: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditNotesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.setTitle("Update Notes")

        database = Room.databaseBuilder(applicationContext, NotesDatabase::class.java, "Notes").build()
        val notes = intent.getSerializableExtra("item") as Notes
        uid = notes.id!!
        binding.etTitle.setText(notes.title)
        binding.etSubtitle.setText(notes.subtitle)
        binding.etNotesContent.setText(notes.notes)

        when (notes.priority) {
            "1" -> {
                priority = "1"
                binding.ivHigh.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivMedium.setImageResource(0)
                binding.ivLow.setImageResource(0)

            }
            "2" -> {
                priority = "2"
                binding.ivMedium.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivLow.setImageResource(0)
                binding.ivHigh.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.ivLow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.ivMedium.setImageResource(0)
                binding.ivHigh.setImageResource(0)
            }

        }

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

        binding.btnUpdate.setOnClickListener {
            updateNotes(it)
            startActivity(Intent(this@EditNotesActivity, MainActivity::class.java))
        }
    }

    private fun updateNotes(it: View?) {

        val title = binding.etTitle.text.toString()
        val subtitle = binding.etSubtitle.text.toString()
        val notes = binding.etNotesContent.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        val data = Notes(uid, title, subtitle, notes, notesDate.toString(), priority)
        viewModel.updateNotes(data)
        Toast.makeText(this@EditNotesActivity, "Notes updated successfully", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle the menu item click event.
        when (item.itemId) {
            R.id.menu_delete -> {
                val bottomSheet: BottomSheetDialog =
                    BottomSheetDialog(this@EditNotesActivity, R.style.BottomSheetDialogTheme)
                bottomSheet.setContentView(R.layout.dialog_delete)

                val tvYes = bottomSheet.findViewById<TextView>(R.id.btnYes)
                val tvNo = bottomSheet.findViewById<TextView>(R.id.btnNo)

                tvYes?.setOnClickListener {
                    viewModel.deleteNotes(uid)
                    startActivity(Intent(this@EditNotesActivity, MainActivity::class.java))
                }
                tvNo?.setOnClickListener {
                    bottomSheet.dismiss()
                }
                bottomSheet.show()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}