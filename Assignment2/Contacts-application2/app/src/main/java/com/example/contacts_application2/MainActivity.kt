package com.example.contacts_application2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), FragmentActionListener {

    private lateinit var buttonListView: Button
    private lateinit var buttonGridView: Button
    private lateinit var fragment: Fragment
    private lateinit var buttonGoBack: ImageView

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askPermissionToReadAndWriteContacts()

        buttonListView = findViewById(R.id.btn_list_view)
        buttonGridView = findViewById(R.id.btn_grid_view)
        buttonListView.setOnClickListener() {
            fragment = ContactsListViewFragment()
            (fragment as ContactsListViewFragment).setFragmentActionListener(this)
            addFragment(fragment)
        }

        buttonGridView.setOnClickListener() {
            fragment = ContactsGridViewFragment()
            (fragment as ContactsGridViewFragment).setFragmentActionListener(this)
            addFragment(fragment)
        }

        buttonGoBack = findViewById(R.id.btn_go_back)
        buttonGoBack.setOnClickListener() {
            finish()
        }
    }

    private fun askPermissionToReadAndWriteContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                Activity(),
                arrayOf(android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_CONTACTS),
                PackageManager.PERMISSION_GRANTED
            )
        }
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right)
        fragmentTransaction.replace(R.id.frame_layout_fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onAddNewContactFragment() {
        addFragment(AddNewContactFragment())
    }

}
