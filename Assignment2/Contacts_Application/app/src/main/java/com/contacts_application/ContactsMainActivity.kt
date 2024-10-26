package com.contacts_application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ContactsMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolBar()

        val listViewButton = findViewById<Button>(R.id.btn_list_view)
        val gridViewButton = findViewById<Button>(R.id.btn_grid_view)

        listViewButton.setOnClickListener() {
            intent = Intent(this, ContactsListActivity::class.java)
            startActivity(intent)
        }

        gridViewButton.setOnClickListener() {
            intent = Intent(this, ContactsGridActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setToolBar() {
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.tool_bar_title)
    }
}