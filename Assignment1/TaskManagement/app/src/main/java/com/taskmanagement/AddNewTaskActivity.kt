package com.taskmanagement

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddNewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)

        val goBackButton = findViewById<Button>(R.id.goBackBtn)

        goBackButton.setOnClickListener() {
            finish()
        }
    }
}