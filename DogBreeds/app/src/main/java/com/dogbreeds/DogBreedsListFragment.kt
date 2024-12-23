package com.dogbreeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DogBreedsListFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dogBreedsFetcher: DogBreedsFetcher
    private lateinit var buttonGoBack: ImageView
    private lateinit var loadingAnimation: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dog_breeds_list, container, false)

        loadingAnimation = view.findViewById(R.id.progress_bar)
        setLoadingAnimation(true)

        recyclerView = view.findViewById(R.id.recycler_view)
        dogBreedsFetcher = DogBreedsFetcher
        recyclerView.layoutManager = LinearLayoutManager(context)

//        viewLifecycleOwner.lifecycleScope.launch {
//            delay(1000)
//        }
        fetchDogBreeds()

        buttonGoBack = view.findViewById(R.id.btn_go_back)
        buttonGoBack.setOnClickListener() {
            requireActivity().finish()
        }

        return view
    }

    private fun setLoadingAnimation(show: Boolean) {
        loadingAnimation.visibility = if (show) View.VISIBLE else View.GONE
    }


    private fun fetchDogBreeds() {
        dogBreedsFetcher.fetchDogBreeds(requireContext()) { dogBreedsList ->
            if (dogBreedsList != null) {
                setLoadingAnimation(false)
                val adaptor = RecyclerViewAdaptor(requireActivity(), dogBreedsList)
                recyclerView.adapter = adaptor
                adaptor.setOnItemClickListener(object : RecyclerViewAdaptor.OnItemClickListener {
                    override fun onItemClicking(position: Int) {
                        openDogBreedDetailsFragment(dogBreedsList[position])
                    }
                })
            } else {
                Toast.makeText(requireContext(), getString(R.string.failed_to_fetch), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openDogBreedDetailsFragment(dogBreed: DogBreed) {
        val detailsFragment = DogBreedDetailsFragment()
        val bundle = Bundle().apply {
            putParcelable(getString(R.string.dog_breeds), dogBreed)
        }

        detailsFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.frame_layout_fragment_container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
}