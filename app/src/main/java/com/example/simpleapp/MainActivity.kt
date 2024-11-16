package com.example.simpleapp

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private var adapter: AnimalAdapter? = null

    private val viewModel: AnimalViewModel by lazy {
        AnimalViewModelFactory().create(AnimalViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AnimalAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        configureSearch()
        configureObserver()

        viewModel.fetchAnimalList()
    }

    private fun configureSearch() {
        binding.searchEditText.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH ||
                event?.action == android.view.KeyEvent.ACTION_DOWN) {
                val query = textView.text.toString()
                viewModel.searchAnimals(query)
                true
            } else {
                false
            }
        }
    }

    private fun configureObserver() {
        viewModel.animalListUI.observe(this) { state ->
            when (state) {
                is AnimalListUI.Success -> adapter?.submitList(state.animalList)
                is AnimalListUI.Error -> handleError(state.errorMessage)
                is AnimalListUI.Empty -> handleEmptyState()
                is AnimalListUI.Loading -> binding.progressBar.isVisible = state.isLoading
            }
        }
    }

    private fun handleEmptyState() {
        Toast.makeText(this, "Список животных пуст", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(@StringRes errorMessage: Int) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}