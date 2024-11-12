package com.dogbreeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DogBreedDetailsFragment : Fragment() {

    private lateinit var buttonGoBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_dog_breed_details, container, false)
        val dogBreed: DogBreed? = arguments?.getParcelable(getString(R.string.dog_breeds))
        dogBreed?.let {
            setDogBreedProperties(view, it)
        }
        setupGoBackButton(view)
        return view
    }

    private fun setupGoBackButton(view: View) {
        buttonGoBack = view.findViewById(R.id.btn_go_back)
        buttonGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setDogBreedProperties(view: View, dogBreed: DogBreed) {
        val properties = mutableListOf<DogBreedProperty>()

        loadImage(view, dogBreed.url)
        setText(view, R.id.tv_dog_name_primary, dogBreed.name)

        properties.add(DogBreedProperty(getString(R.string.name), dogBreed.name.toString()))
        properties.add(DogBreedProperty(getString(R.string.origin), dogBreed.origin ?: getString(R.string.not_specified)))
        properties.add(DogBreedProperty(getString(R.string.breed_group), dogBreed.breedGroup ?: getString(R.string.not_specified)))
        properties.add(DogBreedProperty(getString(R.string.bred_for), dogBreed.bredFor ?: getString(R.string.not_specified)))
        properties.add(DogBreedProperty(getString(R.string.life_span), dogBreed.lifeSpan ?: getString(R.string.not_specified)))
        properties.add(DogBreedProperty(getString(R.string.temperament), dogBreed.temperament ?: getString(R.string.not_specified)))
        properties.add(DogBreedProperty(getString(R.string.weight), "${dogBreed.weight.metric} kgs (${dogBreed.weight.imperial} lbs)"))
        properties.add(DogBreedProperty(getString(R.string.height), "${dogBreed.height.metric} cms (${dogBreed.height.imperial} in)"))


        val listView = view.findViewById<ListView>(R.id.list_view_dogbreed_properties)
        listView.adapter = DogBreedPropertyAdapter(requireContext(), properties)
    }

    private fun loadImage(view: View, url: String) {
        val imageView = view.findViewById<ImageView>(R.id.img_dog_avatar)
        Picasso.get().load(url).into(imageView)
    }

    private fun setText(view: View, textViewId: Int, text: String?) {
        view.findViewById<TextView>(textViewId).text = text ?: ""
    }

}
