package com.taskmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class TaskManagementMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task_management)

        setActionBar()

        val btnNewActivity = findViewById<Button>(R.id.btn_new_activity)
        val btnNewFragment = findViewById<Button>(R.id.btn_new_fragment)

        btnNewActivity.setOnClickListener() {
            intent = Intent( this, AddNewTaskActivity::class.java)
            startActivity(intent)
        }
        btnNewFragment.setOnClickListener() {
//            intent = Intent(this, TaskDetailsActivity::class.java)
//            startActivity(intent)
            replaceFrameWithFragment(TaskDetailsFragment())
        }
    }

    private fun setActionBar() {
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.application_title)
    }

    private fun replaceFrameWithFragment(curFrag: Fragment) {
        val fragTransaction = supportFragmentManager.beginTransaction()
        fragTransaction.replace(R.id.frame_layout, curFrag)
        fragTransaction.addToBackStack(null)
        fragTransaction.commit()
    }

}