package com.dogbreeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
        val dogBreed: DogBreed? = arguments?.getParcelable("dogBreed")
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

        properties.add(DogBreedProperty("Name", dogBreed.name.toString()))
        properties.add(DogBreedProperty("Origin", dogBreed.origin ?: "Not Specified"))
        properties.add(DogBreedProperty("Breed Group", dogBreed.breedGroup ?: "Not Specified"))
        properties.add(DogBreedProperty("Bred For", dogBreed.bredFor ?: "Not Specified"))
        properties.add(DogBreedProperty("Life Span", dogBreed.lifeSpan ?: "Not Specified"))
        properties.add(DogBreedProperty("Temperament", dogBreed.temperament ?: "Not Specified"))
        properties.add(DogBreedProperty("Weight", "${dogBreed.weight.metric} kgs (${dogBreed.weight.imperial} lbs)"))
        properties.add(DogBreedProperty("Height", "${dogBreed.height.metric} cms (${dogBreed.height.imperial} in)"))

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

//    private fun setOptionalTextWithLayout(view: View, layoutId: Int, textViewId: Int, text: String?) {
//        val layout = view.findViewById<LinearLayout>(layoutId)
//        if (text.isNullOrEmpty()) {
//            layout.visibility = View.GONE
//        } else {
//            layout.visibility = View.VISIBLE
//            setText(view, textViewId, text)
//        }
//    }

}
