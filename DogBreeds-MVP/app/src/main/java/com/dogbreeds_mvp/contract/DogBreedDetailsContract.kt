package com.dogbreeds_mvp.contract

import com.dogbreeds_mvp.model.DogBreed

interface DogBreedDetailsContract {
    interface View {
        fun showDogBreedDetails(dogBreed: DogBreed)
    }

    interface Presenter {
        fun loadDobBreedDetails(dogBreed: DogBreed)
    }
}