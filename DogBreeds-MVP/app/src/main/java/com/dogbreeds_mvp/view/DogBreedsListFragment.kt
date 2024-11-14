package com.dogbreeds_mvp.view

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
import com.dogbreeds_mvp.R
import com.dogbreeds_mvp.contract.DogBreedsListContract
import com.dogbreeds_mvp.model.DogBreed
import com.dogbreeds_mvp.model.DogBreedsRepository
import com.dogbreeds_mvp.presenter.DogBreedsListPresenter

class DogBreedsListFragment: Fragment(), DogBreedsListContract.View {

    private lateinit var presenter: DogBreedsListPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingAnimation: ProgressBar
    private lateinit var buttonGoBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dogbreeds_list, container, false)

        presenter = DogBreedsListPresenter(this, DogBreedsRepository)

        loadingAnimation = view.findViewById(R.id.progress_bar)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        presenter.loadDogBreeds(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonGoBack = view.findViewById(R.id.btn_go_back)
        buttonGoBack.setOnClickListener() {
            requireActivity().finish()
        }
    }

    override fun showDogBreeds(dogBreeds: List<DogBreed>) {
        val adapter = RecyclerViewAdaptor(requireActivity() , dogBreeds) { dogBreed ->
            presenter.onDogBreedClicked(dogBreed)
        }
        recyclerView.adapter = adapter
    }

    override fun showLoading(isLoading: Boolean) {
        loadingAnimation.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToDogBreedDetails(dogBreed: DogBreed) {
        val fragment = DogBreedDetailsFragment().apply{
            arguments = Bundle().apply { putParcelable("dogBreed", dogBreed) }
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


}