package com.dogbreeds_mvp.presenter

import android.util.Log
import com.dogbreeds_mvp.contract.DogBreedDetailsContract
import com.dogbreeds_mvp.model.DogBreed

class DogBreedDetailsPresenter(
    private val view: DogBreedDetailsContract.View
): DogBreedDetailsContract.Presenter {
    override fun loadDobBreedDetails(dogBreed: DogBreed) {
        Log.d("error", "loadDobBreedDetails presenter")
        view.showDogBreedDetails(dogBreed)
    }
}