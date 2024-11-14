package com.dogbreeds_mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dogbreeds_mvp.view.DogBreedsListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout_fragment_container, DogBreedsListFragment())
            .commit()

    }
}