package com.dogbreeds_mvp.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dogbreeds_mvp.R
import com.dogbreeds_mvp.model.DogBreed
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class RecyclerViewAdaptor(
    private val context: Activity,
    private val dogBreedsList: List<DogBreed>,
    private val listener: (DogBreed) -> Unit):
    RecyclerView.Adapter<RecyclerViewAdaptor.DogBreedViewHolder>() {

//    private lateinit var myListener: OnItemClickListener

//    interface OnItemClickListener {
//        fun onItemClicking(position: Int)
//    }

//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        myListener = listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogBreedViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return DogBreedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        val currentBreed = dogBreedsList[position]
        holder.bind(currentBreed)
    }

    override fun getItemCount(): Int = dogBreedsList.size

    inner class DogBreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val breedImageView: CircleImageView = itemView.findViewById(R.id.img_avatar)

        fun bind(dogBreed: DogBreed) {
            nameTextView.text = dogBreed.name
            Picasso.get().load(dogBreed.url).into(breedImageView)

            itemView.setOnClickListener {
                listener(dogBreed)
            }
        }
    }
}