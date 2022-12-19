package com.example.notesnerd

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notenerd.viewmodel.NotesViewModel
import com.example.notesnerd.adapter.NotesAdapter
import com.example.notesnerd.adapter.OnItemClickListener
import com.example.notesnerd.databinding.ActivityMainBinding
import com.example.notesnerd.model.Notes

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: NotesViewModel by viewModels()
    lateinit var notesAdapter: NotesAdapter

    var oldNotes = arrayListOf<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.setTitle("Home")
        viewModel.getAllNotes().observe(this@MainActivity, Observer { notesList ->
            oldNotes = notesList as ArrayList<Notes>
            binding.rvAllNotes.layoutManager = GridLayoutManager(this, 2)
            notesAdapter = NotesAdapter(this, notesList)
            binding.rvAllNotes.adapter = notesAdapter

            notesAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(notes: Notes) {
                    val intent = Intent(this@MainActivity, EditNotesActivity::class.java)
                    intent.putExtra("item", notes)
                    this@MainActivity.startActivity(intent)

                }
            })
        })

        binding.btnAddNotes.setOnClickListener {
            startActivity(Intent(this, CreateNotesActivity::class.java))
        }

        binding.tvHighFilter.setOnClickListener {
            viewModel.getHighNotes().observe(this@MainActivity, Observer { notesList ->

                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldNotes = notesList as ArrayList<Notes>
                binding.rvAllNotes.layoutManager = GridLayoutManager(this, 2)
                notesAdapter = NotesAdapter(this, notesList)
                binding.rvAllNotes.adapter = notesAdapter

                notesAdapter.setOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(notes: Notes) {
                        val intent = Intent(this@MainActivity, EditNotesActivity::class.java)
                        intent.putExtra("item", notes)
                        this@MainActivity.startActivity(intent)
                    }

                })
            })
        }

        binding.tvMedFilter.setOnClickListener {
            viewModel.getMedNotes().observe(this@MainActivity, Observer { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldNotes = notesList as ArrayList<Notes>
                binding.rvAllNotes.layoutManager = GridLayoutManager(this, 2)
                notesAdapter = NotesAdapter(this, notesList)
                binding.rvAllNotes.adapter = notesAdapter

                notesAdapter.setOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(notes: Notes) {
                        val intent = Intent(this@MainActivity, EditNotesActivity::class.java)
                        intent.putExtra("item", notes)
                        this@MainActivity.startActivity(intent)
                    }
                })
            })
        }

        binding.tvLowFilter.setOnClickListener {
            viewModel.getLowNotes().observe(this@MainActivity, Observer { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldNotes = notesList as ArrayList<Notes>
                binding.rvAllNotes.layoutManager = GridLayoutManager(this, 2)
                notesAdapter = NotesAdapter(this, notesList)
                binding.rvAllNotes.adapter = notesAdapter

                notesAdapter.setOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(notes: Notes) {
                        val intent = Intent(this@MainActivity, EditNotesActivity::class.java)
                        intent.putExtra("item", notes)
                        this@MainActivity.startActivity(intent)
                    }
                })
            })
        }

        binding.ivAllFilter.setOnClickListener {
            viewModel.getAllNotes().observe(this@MainActivity, Observer { notesList ->
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldNotes = notesList as ArrayList<Notes>
                binding.rvAllNotes.layoutManager = GridLayoutManager(this, 2)
                notesAdapter = NotesAdapter(this, notesList)
                binding.rvAllNotes.adapter = notesAdapter

                notesAdapter.setOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(notes: Notes) {
                        val intent = Intent(this@MainActivity, EditNotesActivity::class.java)
                        intent.putExtra("item", notes)
                        this@MainActivity.startActivity(intent)
                    }
                })
            })
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val item = menu?.findItem(R.id.app_bar_search)

        val menuAction = item?.actionView
        val searchView= item?.actionView as SearchView

        searchView.queryHint="Enter Notes Here...."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean{
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFilter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun NotesFilter(p:String?){
        val newNotes = arrayListOf<Notes>()
        for(i in oldNotes){
            if(i.title.contains(p!!) || i.subtitle.contains(p)  ){
                newNotes.add(i)
            }
        }
        notesAdapter.filtering(newNotes)
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }




}

