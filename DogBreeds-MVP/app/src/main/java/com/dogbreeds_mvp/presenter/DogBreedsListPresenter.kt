package com.dogbreeds_mvp.presenter

import android.content.Context
import com.dogbreeds_mvp.contract.DogBreedsListContract
import com.dogbreeds_mvp.model.DogBreed
import com.dogbreeds_mvp.model.DogBreedsRepository

class DogBreedsListPresenter(
    private val view: DogBreedsListContract.View,
    private val repository: DogBreedsRepository
) : DogBreedsListContract.Presenter {

    override fun loadDogBreeds(context: Context) {
        view.showLoading(true)
        repository.fetchDogBreeds(context) { dogBreedsList ->
            view.showLoading(false)
            if (dogBreedsList != null) {
                view.showDogBreeds(dogBreedsList)
            } else {
                view.showError("Failed to load dog breeds.")
            }
        }
    }

    override fun onDogBreedClicked(dogBreed: DogBreed) {
        view.navigateToDogBreedDetails(dogBreed)
    }
}