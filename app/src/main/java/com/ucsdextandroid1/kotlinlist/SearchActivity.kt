package com.ucsdextandroid1.kotlinlist

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by rjaylward on 2019-06-08
 */

class SearchActivity: AppCompatActivity() {

    private val searchAdapter: SearchAdapter = SearchAdapter()

    private var latestSearchTerm: String? = null

    /**
     * Debouncer keeps us from making a bunch of api calls if the text changes too quickly. We will
     * make at most one search every 200 milliseconds even if the text changes faster than that.
     */
    private val debouncer: Debouncer<String> = Debouncer.create(200) { item ->
        performSearch(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.am_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = searchAdapter

        val editText = findViewById<EditText>(R.id.am_toolbar_edit_text)

        editText.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                debouncer.onChange(s.toString())
            }
        })

        searchAdapter.setOnItemClickListener {
            Toast.makeText(this, "${it.trackName} by ${it.artistName}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performSearch(term: String) {
        val trimmedTerm = term.trim { it <= ' ' }

        if(!Objects.equals(trimmedTerm, latestSearchTerm)) {
            latestSearchTerm = trimmedTerm
            if(TextUtils.isEmpty(trimmedTerm)) {
                searchAdapter.submitList(Collections.emptyList())
            }
            else {
                DataSources.getInstance().search(trimmedTerm) { data ->
                    if(!TextUtils.isEmpty(latestSearchTerm))
                        searchAdapter.submitList(data)
                }
            }
        }
    }
}