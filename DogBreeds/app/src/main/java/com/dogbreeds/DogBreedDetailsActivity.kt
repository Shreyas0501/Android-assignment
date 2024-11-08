//package com.dogbreeds
//
//import android.os.Bundle
//import android.view.View
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import com.squareup.picasso.Picasso
//
//class DogBreedDetailsActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dog_breed_details)
//
//        val dogBreed = intent.getParcelableExtra<DogBreed>("dogBreed")
//
//        if (dogBreed != null) {
//            setDetails(dogBreed)
//        }
//    }
//
//    private fun setDetails(dogBreed: DogBreed) {
//
//        val imageView = findViewById<ImageView>(R.id.img_dog_avatar)
//        Picasso.get().load(dogBreed.url).into(imageView)
//
//        val namePrimary = findViewById<TextView>(R.id.tv_dog_name_primary)
//        namePrimary.text = "${dogBreed.name}"
//
//        val nameSecondary = findViewById<TextView>(R.id.tv_dog_name_secondary)
//        nameSecondary.text = "${dogBreed.name}"
//
//        if (dogBreed.origin.isNullOrEmpty()) {
//            val originLayout = findViewById<LinearLayout>(R.id.ll_origin)
//            originLayout.visibility = View.GONE
//        } else {
//            val origin = findViewById<TextView>(R.id.tv_origin)
//            origin.text = "${dogBreed.origin}"
//        }
//
//        if (dogBreed.breedGroup.isNullOrEmpty()) {
//            val breedGroupLayout = findViewById<LinearLayout>(R.id.ll_breed_group)
//            breedGroupLayout.visibility = View.GONE
//        } else {
//            val breedGroup = findViewById<TextView>(R.id.tv_breed_group)
//            breedGroup.text = "${dogBreed.breedGroup}"
//        }
//
//        if (dogBreed.bredFor.isNullOrEmpty()) {
//            val bredForLayout = findViewById<LinearLayout>(R.id.ll_bred_for)
//            bredForLayout.visibility = View.GONE
//        } else {
//            val bredFor = findViewById<TextView>(R.id.tv_bred_for)
//            bredFor.text = "${dogBreed.bredFor}"
//        }
//
//        if (dogBreed.lifeSpan.isNullOrEmpty()) {
//            val lifeSpanLayout = findViewById<LinearLayout>(R.id.ll_life_span)
//            lifeSpanLayout.visibility = View.GONE
//        } else {
//            val lifeSpan = findViewById<TextView>(R.id.tv_life_span)
//            lifeSpan.text = "${dogBreed.lifeSpan}"
//        }
//
//        if (dogBreed.temperament.isNullOrEmpty()) {
//            val temperamentLayout = findViewById<LinearLayout>(R.id.ll_temperament)
//            temperamentLayout.visibility = View.GONE
//        } else {
//            val temperament = findViewById<TextView>(R.id.tv_temperament)
//            temperament.text = "${dogBreed.temperament}"
//        }
//
//        val weight = findViewById<TextView>(R.id.tv_weight)
//        weight.text = "${dogBreed.weight.metric} kgs (${dogBreed.weight.imperial} lbs)"
//
//        val height = findViewById<TextView>(R.id.tv_height)
//        height.text = "${dogBreed.height.metric} cms (${dogBreed.height.imperial} in)"
//
//    }
//}