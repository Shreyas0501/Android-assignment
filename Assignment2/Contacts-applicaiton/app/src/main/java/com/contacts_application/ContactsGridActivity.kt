package com.contacts_application

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactsGridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_grid)
        setToolBar()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, ContactsGridView())
        fragmentTransaction.commit()
    }

    private fun setToolBar() {
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.grid_fragment_title)
    }
}