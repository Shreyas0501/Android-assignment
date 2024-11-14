package com.dogbreeds_mvp.contract

import android.content.Context
import com.dogbreeds_mvp.model.DogBreed

interface DogBreedsListContract {
    interface View {
        fun showDogBreeds(dogBreeds: List<DogBreed>)
        fun showLoading(isLoading: Boolean)
        fun showError(message: String)
        fun navigateToDogBreedDetails(dogBreed: DogBreed)
    }

    interface Presenter {
        fun loadDogBreeds(context: Context)
        fun onDogBreedClicked(dogBreed: DogBreed)
    }
}